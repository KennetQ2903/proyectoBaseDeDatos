package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ClientesController implements Initializable {

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
    ObservableList<Departamento> departamentosList = FXCollections.observableArrayList();
    ObservableList<Municipio> municipiosList = FXCollections.observableArrayList();
   public String SQL;
   DBConnection conn=new DBConnection();

    Cliente cliente=new Cliente();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       setDepartamentos();

    }
    //Metodos setDepartamentos para llenar los Combox
    private void setDepartamentos(){
        try {
            SQL="SELECT * FROM SYS.DEPARTAMENTO";
            PreparedStatement statement = conn.getConnection().prepareStatement(SQL);
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
            SQL= "SELECT * FROM SYS.MUNICIPIO WHERE DEPARTAMENTO = ?";
            PreparedStatement statement = conn.getConnection().prepareStatement(SQL);
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



}
