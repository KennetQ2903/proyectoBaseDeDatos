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
    @FXML
    private TextField TextANombre;

    @FXML
    private TextField TextCalle;

    @FXML
    private TextField TextCiuda;

    @FXML
    private TextField TextCodigo_post;

    @FXML
    private TextField TextColonia;

    @FXML
    private TextField TextDPI;

    @FXML
    private TextField TextEmail;

    @FXML
    private TextField TextNit;

    @FXML
    private TextField TextPApellido;

    @FXML
    private TextField TextSApellido;

    @FXML
    private TextField TextSnombre;

    @FXML
    private TextField TextTelefono;

    @FXML
    private TextField TextZona;

    @FXML
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
    public String SQLDelete;
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
            //Si existe un error enviamos un mensaje
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
        try {
            Departamento depa = BoxDepartamento.getSelectionModel().getSelectedItem();
            Municipio muni=BoxMunicipio.getSelectionModel().getSelectedItem();
            String municipioValue = BoxMunicipio.getValue().toString();
            String aNombreValue = TextANombre.getText();
            String calleValue = TextCalle.getText();
            String ciudadValue = TextCiuda.getText();
            String codigoPostValue = TextCodigo_post.getText();
            String coloniaValue = TextColonia.getText();
            String dpiValue = TextDPI.getText();
            String emailValue = TextEmail.getText();
            String nitValue = TextNit.getText();
            String pApellidoValue = TextPApellido.getText();
            String sApellidoValue = TextSApellido.getText();
            String sNombreValue = TextSnombre.getText();
            String telefonoValue = TextTelefono.getText();
            String zonaValue = TextZona.getText();
            String oApellidoValue = TextoApellido.getText();

            String query = "MERGE INTO CLIENTE C\n" +
                    "USING (\n" +
                    "    SELECT\n" +
                    "    ? AS ID_CLIENTE_S,\n" +
                    "    ? AS PRIMER_NOMBRE_S,\n" +
                    "    ? AS SEGUNDO_NOMBRE_S,\n" +
                    "    ? AS PRIMER_APELLIDO_S,\n" +
                    "    ? AS SEGUNDO_APELLIDO_S,\n" +
                    "    ? AS OTROS_APELLIDOS_S,\n" +
                    "    ? AS CALLE_S,\n" +
                    "    ? AS COLONIA_S,\n" +
                    "    ? AS ZONA_S,\n" +
                    "    ? AS CIUDAD_S,\n" +
                    "    ? AS MUNICIPIO_S,\n" +
                    "    ? AS DEPARTAMENTO_S,\n" +
                    "    ? AS CODIGO_POSTAL_S,\n" +
                    "    ? AS TELEFONO_S,\n" +
                    "    ? AS EMAIL_S,\n" +
                    "    ? AS NIT_S,\n" +
                    "    ? AS DPI_S,\n" +
                    "    ? AS USUARIO_CREACION_S,\n" +
                    "    ? AS USUARIO_MOD_S,\n" +
                    "    ? AS FECHA_CREACION_S,\n" +
                    "    ? AS FECHA_MOD_S,\n" +
                    "    ? AS ESTADO_S\n" +
                    "    FROM DUAL\n" +
                    ") S\n" +
                    "ON (C.ID_CLIENTE = S.ID_CLIENTE_S)\n" +
                    "WHEN MATCHED THEN\n" +
                    "UPDATE SET\n" +
                    "    C.PRIMER_NOMBRE = S.PRIMER_NOMBRE_S,\n" +
                    "    C.SEGUNDO_NOMBRE = S.SEGUNDO_NOMBRE_S,\n" +
                    "    C.PRIMER_APELLIDO = S.PRIMER_APELLIDO_S,\n" +
                    "    C.SEGUNDO_APELLIDO = S.SEGUNDO_APELLIDO_S,\n" +
                    "    C.OTROS_APELLIDOS = S.OTROS_APELLIDOS_S,\n" +
                    "    C.CALLE = S.CALLE_S,\n" +
                    "    C.COLONIA = S.COLONIA_S,\n" +
                    "    C.ZONA = S.ZONA_S,\n" +
                    "    C.CIUDAD = S.CIUDAD_S,\n" +
                    "    C.MUNICIPIO = S.MUNICIPIO_S,\n" +
                    "    C.DEPARTAMENTO = S.DEPARTAMENTO_S,\n" +
                    "    C.CODIGO_POSTAL = S.CODIGO_POSTAL_S,\n" +
                    "    C.TELEFONO = S.TELEFONO_S,\n" +
                    "    C.EMAIL = S.EMAIL_S,\n" +
                    "    C.NIT = S.NIT_S,\n" +
                    "    C.DPI = S.DPI_S,\n" +
                    "    C.USUARIO_CREACION = S.USUARIO_CREACION_S,\n" +
                    "    C.USUARIO_MOD = S.USUARIO_MOD_S,\n" +
                    "    C.FECHA_CREACION = S.FECHA_CREACION_S,\n" +
                    "    C.FECHA_MOD = S.FECHA_MOD_S,\n" +
                    "    C.ESTADO = S.ESTADO_S\n" +
                    "WHEN NOT MATCHED THEN\n" +
                    "INSERT (\n" +
                    "        C.PRIMER_NOMBRE,\n" +
                    "        C.SEGUNDO_NOMBRE,\n" +
                    "        C.PRIMER_APELLIDO,\n" +
                    "        C.SEGUNDO_APELLIDO,\n" +
                    "        C.OTROS_APELLIDOS,\n" +
                    "        C.CALLE,\n" +
                    "        C.COLONIA,\n" +
                    "        C.ZONA,\n" +
                    "        C.CIUDAD,\n" +
                    "        C.MUNICIPIO,\n" +
                    "        C.DEPARTAMENTO,\n" +
                    "        C.CODIGO_POSTAL,\n" +
                    "        C.TELEFONO,\n" +
                    "        C.EMAIL,\n" +
                    "        C.NIT,\n" +
                    "        C.DPI,\n" +
                    "        C.USUARIO_CREACION,\n" +
                    "        C.USUARIO_MOD,\n" +
                    "        C.FECHA_CREACION,\n" +
                    "        C.FECHA_MOD,\n" +
                    "        C.ESTADO\n" +
                    ") VALUES (\n" +
                    "        S.PRIMER_NOMBRE_S,\n" +
                    "        S.SEGUNDO_NOMBRE_S,\n" +
                    "        S.PRIMER_APELLIDO_S,\n" +
                    "        S.SEGUNDO_APELLIDO_S,\n" +
                    "        S.OTROS_APELLIDOS_S,\n" +
                    "        S.CALLE_S,\n" +
                    "        S.COLONIA_S,\n" +
                    "        S.ZONA_S,\n" +
                    "        S.CIUDAD_S,\n" +
                    "        S.MUNICIPIO_S,\n" +
                    "        S.DEPARTAMENTO_S,\n" +
                    "        S.CODIGO_POSTAL_S,\n" +
                    "        S.TELEFONO_S,\n" +
                    "        S.EMAIL_S,\n" +
                    "        S.NIT_S,\n" +
                    "        S.DPI_S,\n" +
                    "        S.USUARIO_CREACION_S,\n" +
                    "        S.USUARIO_MOD_S,\n" +
                    "        S.FECHA_CREACION_S,\n" +
                    "        S.FECHA_MOD_S,\n" +
                    "        S.ESTADO_S" +
                    ")";

int id_Cliente=0;
if(selectCliente != null){
id_Cliente = selectCliente.getId_cliente();
}
PreparedStatement statement = conn.getConnection().prepareStatement(query);
            statement.setInt(1, depa.getID_DEPARTAMENTO());
            statement.setInt(2, muni.getID_MUNICIPIO());
            statement.setString(3, aNombreValue);
            statement.setString(4, calleValue);
            statement.setString(5, ciudadValue);
            statement.setString(6, codigoPostValue);
            statement.setString(7, coloniaValue);
            statement.setString(8, dpiValue);
            statement.setString(9, emailValue);
            statement.setString(10, nitValue);
            statement.setString(11, pApellidoValue);
            statement.setString(12, sApellidoValue);
            statement.setString(13, sNombreValue);
            statement.setString(14, telefonoValue);
            statement.setString(15, zonaValue);
            statement.setString(16, oApellidoValue);


            statement.executeQuery();
            Mensaje("Ingreso a Base de datos", "Los datos Fueron ingresados correctamente");
setClientes();
        }catch (SQLException ex){
            System.out.println(ex);
            Mensaje("Error en Base de Datos", String.valueOf(ex));
        }

    }
//metodo para limpiar los campos
    @FXML
    void LimpiarLabel(ActionEvent event) {
        System.out.println("Hola 2");
        Alerta("Limpiar Campos", "Se limpirar todo los campos","¿Desea Continuar?");
        alert.showAndWait().ifPresent(result ->{
            if(result == buttonTypeYes ){
              TextANombre.setText("");
              TextSnombre.setText("");
              TextPApellido.setText("");
              TextSApellido.setText("");
              TextoApellido.setText("");
              TextCalle.setText("");
              TextColonia.setText("");
              TextZona.setText("");
              TextCiuda.setText("");
              TextCodigo_post.setText("");
              TextTelefono.setText("");
              TextEmail.setText("");
              TextNit.setText("");
                TextDPI.setText("");
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
            .hideAfter(Duration.seconds(6))
            .show();

}
//Metodo llena los campos para crear una alerta generica
public void Alerta(String Titulo, String Header, String Contenido ){
    alert.setTitle(Titulo);
    alert.setHeaderText(Header);
    alert.setContentText(Contenido);
    // Configura los botones
    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
}
    /**
     * Método para validar si los campos no están vacíos.
     * @return true si todos los campos están llenos, false si al menos uno está vacío.
     */
    private boolean validarCampos() {
        if (BoxDepartamento.getValue() == null ||
                BoxMunicipio.getValue() == null ||
                TextANombre.getText().isEmpty() ||
                TextCalle.getText().isEmpty() ||
                TextCiuda.getText().isEmpty() ||
                TextCodigo_post.getText().isEmpty() ||
                TextColonia.getText().isEmpty() ||
                TextDPI.getText().isEmpty() ||
                TextEmail.getText().isEmpty() ||
                TextNit.getText().isEmpty() ||
                TextPApellido.getText().isEmpty() ||
                TextSApellido.getText().isEmpty() ||
                TextSnombre.getText().isEmpty() ||
                TextTelefono.getText().isEmpty() ||
                TextZona.getText().isEmpty() ||
                TextoApellido.getText().isEmpty()) {
            // Uno o más campos están vacíos
            return false;
        }
        return true;
    }



}
