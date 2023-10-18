package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;


public class ClientesController implements Initializable {
Cliente selectCliente=null;
    Cliente cliente=null;
    @FXML
    private ComboBox<Departamento> BoxDepartamento;
    @FXML
    private ComboBox<Municipio> BoxMunicipio;
    private TextField TextANombre;
    private TextField TextCalle;
    private TextField TextCiuda;
    private TextField TextCodigo_post;
    private TextField TextColonia;
    private TextField TextDPI;
    private TextField TextEmail;
    private TextField TextNit;
    private Label TextOApellido;
    private TextField TextPApellido;
    private TextField TextSApellido;
    private TextField TextTelefono;
    private TextField TextZona;
    private TextField TextoApellido;
    @FXML
    private Button ButtonEditar;
    @FXML
    private Button ButtonEliminar;
    @FXML
    private TableView<Cliente> TablaClientes;


    @FXML
    private TableColumn<Cliente, Integer> TableID;
    @FXML
    private TableColumn<Cliente, String> TableNombre;
    @FXML
    private TableColumn<Cliente, String> TableDPI;
    @FXML
    private TableColumn<Cliente, String> TableEmail;
    @FXML
    private TableColumn<Cliente, String > TableNit;
    @FXML
    private TableColumn<Cliente, String> TableTelefono;

    ObservableList<Departamento> departamentosList = FXCollections.observableArrayList();
    ObservableList<Municipio> municipiosList = FXCollections.observableArrayList();

    /**por problemas con la base de datos Dejo aqui la Consultas SQL para que puedan ser modificadas con mayor
     * facilidad ya que pueden no necesitar el SYS.
    **/
   public String SQLDepartamentos="SELECT * FROM SYS.DEPARTAMENTO";;
    public String SQLMunicipios= "SELECT * FROM SYS.MUNICIPIO WHERE DEPARTAMENTO = ?";;
    public String SQLInsert;
    public String SQLDelete;
    public String SQLUpdate;
    public String SQLSetClientes="SELECT * FROM SYS.CLIENTE";
    // Variable de conexión a bd
   DBConnection conn=new DBConnection();
   //Crear el Objeto Alerta
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    //Creo los Botones para las Alertas
    ButtonType buttonTypeYes = new ButtonType("Sí");
    ButtonType buttonTypeNo = new ButtonType("No");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //Captura la seleccion de la filas de las tablas


        try {
            setDepartamentos();
            setClientes();
            ButtonEditar.setDisable(true);
            ButtonEliminar.setDisable(true);
            TablaClientes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cliente>() {
                @Override
                public void changed(ObservableValue<? extends Cliente> observableValue, Cliente oldcliente, Cliente newcliente) {

                    if (newcliente != null) {
                        selectCliente = newcliente;
                        ButtonEditar.setDisable(false);
                        ButtonEliminar.setDisable(false);

                    } else {
                        ButtonEditar.setDisable(true);
                        ButtonEliminar.setDisable(true);
                    }

                }
            });
        } catch (Exception e) {
            Mensaje("No se puedo Obtener Lista", String.valueOf(e));
            System.out.println(e);


        }
    }
    //Metodos setDepartamentos para llenar los Combox
    private void setDepartamentos(){
        try {

            PreparedStatement statement = conn.getConnection().prepareStatement(SQLDepartamentos);
            ResultSet rs = statement.executeQuery();
            System.out.println(rs);
            if (rs != null) {
                /**
                 ** Recicle el codigo de SetDepartamentos y SetMunicipios para esta vista y llenar los Combox
                 ** no pude realizar las consultas de forma correcta pero encontre la forma de consultar la BD
                 ** Colocar el esquema primero en la consulta que en este caso es SYS.Nombre_Tabla
                 * */
                while (rs.next()) {
                    Departamento dep = new Departamento(
                            rs.getInt("ID_DEPARTAMENTO"),
                            rs.getString("CODIGO"),
                            rs.getString("NOMBRE")
                    );
                    departamentosList.add(dep);
                    System.out.println("Departamento "+dep);
                }
                BoxDepartamento.setItems(departamentosList);
            }


        } catch (SQLException e) {
            Mensaje("Error BD", String.valueOf(e));
            System.out.println(e);
        }
    }


    //Metodos SetMunicipios para llenar los Combox
    @FXML
    private void setMunicipios() {
        try{
        Departamento value = BoxDepartamento.getValue();
        if (value != null) {
            //el símbolo de interrogación indica donde reemplazaremos el valor de una variable para no concatenar

            PreparedStatement statement = conn.getConnection().prepareStatement(SQLMunicipios);
            // Asignamos un valor al primer"?", que está en la query, en este caso él, id del departamento para obtener los municipios de ese departamento
            statement.setInt(1, value.getID_DEPARTAMENTO());
            System.out.println(value.getID_DEPARTAMENTO());
            ResultSet rs = statement.executeQuery();
            //Limpiamos todos los elementos para que no se dupliquen
            BoxMunicipio.getItems().removeAll(BoxMunicipio.getItems());
            while (rs.next()) {
                Municipio mun = new Municipio(
                        rs.getInt("ID_MUNICIPIO"),
                        rs.getInt("DEPARTAMENTO"),
                        rs.getString("CODIGO"),
                        rs.getString("NOMBRE")
                );
                municipiosList.add(mun);
                System.out.println(mun);

            }
            BoxMunicipio.setItems(municipiosList);
        }
        } catch (SQLException e) {
            Mensaje("Error BD", String.valueOf(e));
            System.out.println(e);
        }
    }
    /**
     * Metodo utilizado para obtener el listado de Clientes y asignarlos
     * a la TableView de usuarios
     */
private void setClientes(){
        try {
           TableID.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
           TableNombre.setCellValueFactory(new PropertyValueFactory<>("primer_nombre"));
            TableEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
           TableNit.setCellValueFactory(new PropertyValueFactory<>("nit"));
           TableTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
           TableDPI.setCellValueFactory(new PropertyValueFactory<>("dpi"));
           PreparedStatement statement =conn.getConnection().prepareStatement(SQLSetClientes);
           ResultSet rs= statement.executeQuery();
           if(rs != null){
               ObservableList<Cliente> ListaClientes= FXCollections.observableArrayList();
               while (rs.next()){
                   Cliente client= new Cliente(
                   rs.getInt("id_cliente"),
                   rs.getString("primer_nombre"),
                   rs.getString("segundo_nombre"),
                   rs.getString("primer_apellido"),
                   rs.getString("segundo_apellido"),
                   rs.getString("otros_apellidos"),
                   rs.getString("calle"),
                   rs.getString("colonia"),
                   rs.getString("zona"),
                   rs.getString("ciudad"),
                   rs.getInt("municipio"),
                   rs.getInt("departamento"),
                   rs.getString("codigo_postal"),
                   rs.getString("telefono"),
                   rs.getString("email"),
                   rs.getString("nit"),
                   rs.getString("dpi"),
                   rs.getInt("usuario_creacion"),
                   rs.getInt("usuario_mod"),
                   rs.getDate("fecha_creacion"),
                   rs.getDate("fecha_mod"),
                   rs.getInt("estado")
                   );
                   ListaClientes.add(client);
               }
               TablaClientes.setItems(ListaClientes);
           }



        }catch (SQLException e){
            Mensaje("Falla BD Lista Clientes Tabla", String.valueOf(e));

        }


}


    @FXML
    void GuardarDB(ActionEvent event) {


        System.out.println("Hola");
        try {
            int a=0;
            conn.getConnection();
            System.out.println(conn.getConnection());
            conn.getConnection().close();

            if(a==1){
                PreparedStatement insert= conn.getConnection().prepareStatement("insert into ");

            }else{

         Mensaje("Error Campos Vacios", "Campos Vacios al ingreso de datos");
            }

        }catch (SQLException ex){
            System.out.println(ex);
            Mensaje("Error en Base de Datos", String.valueOf(ex));
        }

    }

    @FXML
    void LimpiarLabel(ActionEvent event) {
        System.out.println("Hola 2");
        Alerta("Limpiar Campos", "Se limpirar todo los campos","¿Desea Continuar?");
        alert.showAndWait().ifPresent(result ->{
            if(result == buttonTypeYes ){
              Te
            }else if(result == buttonTypeNo){
                System.out.println("Eligio no");

            }

        });
        
    }

    @FXML
    void ButtonEditar(ActionEvent event) {

    }
    @FXML
    void ButtonEliminar(ActionEvent event) {
alert.showAndWait().ifPresent(result ->{
    Alerta("Eliminar Usuario", "Usuario sera Eliminado de BD","¿Desea Continuar?");
    if(result == buttonTypeYes ){
        System.out.println("Eligio Si");
    }else if(result == buttonTypeNo){
        System.out.println("Eligio no");

    }

});
    }


    /**
    **Podemos enviar una notificacion flotante a la pantalla del computador para informar de cualquier tipo de error
     ** Recibe dos parametro el titulo de la notificacion y la informacion
    **/
public void Mensaje(String titulo, String mensaje){
    Notifications.create()
            .title(titulo)
            .text(mensaje)
            .hideAfter(Duration.seconds(3))
            .show();

}
public void Alerta(String Titulo, String Header, String Contenido ){
    alert.setTitle(Titulo);
    alert.setHeaderText(Header);
    alert.setContentText(Contenido);
    // Configura los botones
    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
}


}
