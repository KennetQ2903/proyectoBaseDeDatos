package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.DBConnection;
import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.Departamento;
import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.Municipio;
import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.Rol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProveedoresController implements Initializable {
    DBConnection DB = new DBConnection();
    ObservableList<Departamento> departamentosList = FXCollections.observableArrayList();
    ObservableList<Municipio> municipiosList = FXCollections.observableArrayList();
    ObservableList<Rol> rolesList = FXCollections.observableArrayList();
    public TextField firstname1;
    public TextField firstname2;
    public TextField lastname1;
    public TextField lastname2;
    public TextField lastname3;
    public PasswordField password;
    public TextField calle;
    public TextField colonia;
    public TextField zona;
    public TextField ciudad;
    public ComboBox<Departamento> departamento;
    public ComboBox<Municipio> municipio;
    public TextField postal;
    public TextField telefono;
    public TextField nit;
    public TextField dpi;
    public ComboBox<Rol> rol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDepartamentos();
        setRoles();
    }

    private void setDepartamentos() {
        try {
            Connection connection = DB.getConnection();
            String query = "SELECT * FROM DEPARTAMENTO";
            /* ASEGURATE DE NO ESTAR LOGUEADO EN SQL DEVELOPER, SI NO, NO TRAERA NADA DE INFORMACION YA QUE BLOQUEA LA QUERY */
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if (result != null) {
                /**
                 *AGREGAR CADA DEPARTAMENTO AL COMBOBOX DE DEPARTAMENTOS,
                 * EL COMBOBOX SABE COMO MOSTRAR EL VALOR (NOMBRE) YA QUE IMPLEMENTAMOS
                 * EL METODO TOSTRING EN NUESTRA CLASE DEPARTAMENTO
                 * */
                while (result.next()) {
                    Departamento dep = new Departamento(
                            result.getInt("ID_DEPARTAMENTO"),
                            result.getString("CODIGO"),
                            result.getString("NOMBRE")
                    );
                    departamentosList.add(dep);
                }
                departamento.setItems(departamentosList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método se llama cada que seleccionamos un nuevo valor
     * en el combo box de departamentos, este método está asignado en el fxml de usuarios
     * en el combo box departamento bajo el método onAction
     */
    @FXML
    private void setMunicipios() throws SQLException {
        Departamento value = departamento.getValue();
        if (value != null) {
            Connection connection = DB.getConnection();
            //el símbolo de interrogación indica donde reemplazaremos el valor de una variable para no concatenar
            String query = "SELECT * FROM MUNICIPIO WHERE DEPARTAMENTO = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            // Asignamos un valor al primer"?", que está en la query, en este caso él, id del departamento para obtener los municipios de ese departamento
            statement.setInt(1, value.getID_DEPARTAMENTO());
            ResultSet result = statement.executeQuery();
            //Limpiamos todos los elementos para que no se dupliquen ni se agreguen infinitamente
            municipio.getItems().removeAll(municipio.getItems());
            while (result.next()) {
                Municipio mun = new Municipio(
                        result.getInt("ID_MUNICIPIO"),
                        result.getInt("DEPARTAMENTO"),
                        result.getString("CODIGO"),
                        result.getString("NOMBRE")
                );
                municipiosList.add(mun);
            }
            municipio.setItems(municipiosList);
        }
    }

    private void setRoles() {
        try {
            Connection connection = DB.getConnection();
            String query = "SELECT * FROM ROL";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Rol r = new Rol(
                        result.getInt("ID_ROL"),
                        result.getString("NOMBRE")
                );
                rolesList.add(r);
            }
            rol.setItems(rolesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta función se utiliza para limpiar el formulario
     * una vez guardado con exito un proveedor
     */
    @FXML
    private void clearAllFields() {
        firstname1.setText("");
        firstname2.setText("");
        lastname1.setText("");
        lastname2.setText("");
        lastname3.setText("");
        password.setText("");
        calle.setText("");
        colonia.setText("");
        zona.setText("");
        ciudad.setText("");
        departamento.getSelectionModel().clearSelection();
        municipio.getSelectionModel().clearSelection();
        rol.getSelectionModel().clearSelection();
        postal.setText("");
        telefono.setText("");
        nit.setText("");
        dpi.setText("");
    }

    @FXML
    private void setProveedorForm() {}

    @FXML
    private  void deleteProveedor(){}

    @FXML
    private void handleSubmit(){}
}
