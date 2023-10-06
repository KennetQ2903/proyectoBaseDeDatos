package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProveedoresController implements Initializable {
    Usuario loggedInUser = AppData.getLoggedInUsername();
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
     * Esta función válida que ningún campo para crear o editar un usuario se encuentre vació
     * si alguno esta vació retorna {@code false}, si no retorna {@code true}.
     */
    private boolean isAllFieldsValid() {
        boolean firstName1Value = firstname1.getText().isEmpty();
        boolean firstName2Value = firstname2.getText().isEmpty();
        boolean lastName1Value = lastname1.getText().isEmpty();
        boolean lastName2Value = lastname2.getText().isEmpty();
//        boolean lastName3Value = lastname3.getText().isEmpty();
        boolean passValue = password.getText().isEmpty();
//        boolean calleValue = calle.getText().isEmpty();
//        boolean coloniaValue = colonia.getText().isEmpty();
//        boolean zonaValue = zona.getText().isEmpty();
//        boolean ciudadVal = ciudad.getText().isEmpty();
        boolean departamentoVal = departamento.getSelectionModel().getSelectedItem() == null;
        boolean municipioVal = municipio.getSelectionModel().getSelectedItem() == null;
        boolean rolVal = rol.getSelectionModel().getSelectedItem() == null;
        boolean postalVal = postal.getText().isEmpty();
        boolean telefonoVal = telefono.getText().isEmpty();
        boolean nitVal = nit.getText().isEmpty();
        boolean dpiVal = dpi.getText().isEmpty();
        return !(
                firstName1Value ||
                        firstName2Value ||
                        lastName1Value ||
                        lastName2Value ||
                        passValue ||
                        departamentoVal ||
                        municipioVal ||
                        rolVal ||
                        postalVal ||
                        telefonoVal ||
                        nitVal ||
                        dpiVal
        );
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
    private void setProveedorForm() {
    }

    @FXML
    private void deleteProveedor() {
    }

    @FXML
    private void handleSubmit() {
        if (!isAllFieldsValid()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor rellene todos los campos");
            alert.showAndWait();
            return;
        }

        String firstName1Value = firstname1.getText();
        String firstName2Value = firstname2.getText();
        String lastName1Value = lastname1.getText();
        String lastName2Value = lastname2.getText();
        String lastName3Value = lastname3.getText();
        String passValue = password.getText();
        String calleValue = calle.getText();
        String coloniaValue = colonia.getText();
        String zonaValue = zona.getText();
        String ciudadVal = ciudad.getText();
        Departamento departamentoVal = departamento.getSelectionModel().getSelectedItem();
        Municipio municipioVal = municipio.getSelectionModel().getSelectedItem();
        Rol rolVal = rol.getSelectionModel().getSelectedItem();
        String postalVal = postal.getText();
        String telefonoVal = telefono.getText();
        String nitVal = nit.getText();
        String dpiVal = dpi.getText();

        String query = "MERGE INTO PROVEEDOR P USING(\n" +
                "    SELECT \n" +
                "        ? AS id_proveedor_s,\n" +
                "        ? AS primer_nombre_s,\n" +
                "        ? AS segundo_nombre_s,\n" +
                "        ? AS primer_apellido_s,\n" +
                "        ? AS segundo_apellido_s,\n" +
                "        ? AS otros_apellidos_s,\n" +
                "        ? AS calle_s,\n" +
                "        ? AS colonia_s,\n" +
                "        ? AS zona_s,\n" +
                "        ? AS ciudad_s,\n" +
                "        ? AS municipio_s,\n" +
                "        ? AS departamento_s,\n" +
                "        ? AS codigo_postal_s,\n" +
                "        ? AS telefono_s,\n" +
                "        ? AS nit_s,\n" +
                "        ? AS dpi_s,\n" +
                "        ? AS email_s,\n" +
                "        ? AS usuario_creacion_s,\n" +
                "        ? AS usuario_mod_s,\n" +
                "        ? AS fecha_creacion_s,\n" +
                "        ? AS fecha_mod_s,\n" +
                "        ? AS estado_s\n" +
                "    FROM DUAL \n" +
                ") S ON (P.id_proveedor = S.id_proveedor)\n" +
                "WHEN MATCHED THEN\n" +
                "UPDATE SET\n" +
                "    P.primer_nombre = S.primer_nombre_s,\n" +
                "    P.segundo_nombre = S.segundo_nombre_s,\n" +
                "    P.primer_apellido = S.primer_apellido_s,\n" +
                "    P.segundo_apellido = S.segundo_apellido_s,\n" +
                "    P.otros_apellidos = S.otros_apellidos_s,\n" +
                "    P.calle = S.calle_s,\n" +
                "    P.colonia = S.colonia_s,\n" +
                "    P.zona = S.zona_s,\n" +
                "    P.ciudad = S.ciudad_s,\n" +
                "    P.municipio = S.municipio_s,\n" +
                "    P.departamento = S.departamento_s,\n" +
                "    P.codigo_postal = S.codigo_postal_s,\n" +
                "    P.telefono = S.telefono_s,\n" +
                "    P.nit = S.nit_s,\n" +
                "    P.dpi = S.dpi_s,\n" +
                "    P.email = S.email_s,\n" +
                "    P.usuario_mod = S.usuario_mod_s,\n" +
                "    P.fecha_mod = S.fecha_mod_s,\n" +
                "    P.estado = S.estado_s\n" +
                "WHEN NOT MATCHED THEN\n" +
                "    INSERT (\n" +
                "        P.primer_nombre,\n" +
                "        P.segundo_nombre,\n" +
                "        P.primer_apellido,\n" +
                "        P.segundo_apellido,\n" +
                "        P.otros_apellidos,\n" +
                "        P.calle,\n" +
                "        P.colonia,\n" +
                "        P.zona,\n" +
                "        P.ciudad,\n" +
                "        P.municipio,\n" +
                "        P.departamento,\n" +
                "        P.codigo_postal,\n" +
                "        P.telefono,\n" +
                "        P.nit,\n" +
                "        P.dpi,\n" +
                "        P.email,\n" +
                "        P.usuario_creacion,\n" +
                "        P.usuario_mod,\n" +
                "        P.fecha_creacion,\n" +
                "        P.fecha_mod,\n" +
                "        P.estado\n" +
                "    )\n" +
                "    VALUES (\n" +
                "        S.primer_nombre_s,\n" +
                "        S.segundo_nombre_s,\n" +
                "        S.primer_apellido_s,\n" +
                "        S.segundo_apellido_s,\n" +
                "        S.otros_apellidos_s,\n" +
                "        S.calle_s,\n" +
                "        S.colonia_s,\n" +
                "        S.zona_s,\n" +
                "        S.ciudad_s,\n" +
                "        S.municipio_s,\n" +
                "        S.departamento_s,\n" +
                "        S.codigo_postal_s,\n" +
                "        S.telefono_s,\n" +
                "        S.nit_s,\n" +
                "        S.dpi_s,\n" +
                "        S.email_s,\n" +
                "        S.usuario_creacion_s,\n" +
                "        S.usuario_mod_s,\n" +
                "        S.fecha_creacion_s,\n" +
                "        S.fecha_mod_s,\n" +
                "        S.estado_s\n" +
                "    )\n";
    }
}
