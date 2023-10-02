package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {
    Usuario usuario = null;
    DBConnection DB = new DBConnection();
    public TextField username;
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
    public Button saveButton;
    public TableView<Usuario> userTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Código para inicializar componentes o realizar acciones al cargar el FXML
        traerDepartamentos();
        setRoles();
//        setUsuarios();
    }

    private void traerDepartamentos() {
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
                    departamento.getItems().add(new Departamento(
                            result.getInt("ID_DEPARTAMENTO"),
                            result.getString("CODIGO"),
                            result.getString("NOMBRE")
                    ));
                }
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
                municipio.getItems().add(new Municipio(
                        result.getInt("ID_MUNICIPIO"),
                        result.getInt("DEPARTAMENTO"),
                        result.getString("CODIGO"),
                        result.getString("NOMBRE")
                ));
            }
        }
    }

    private void setRoles() {
        try {
            Connection connection = DB.getConnection();
            String query = "SELECT * FROM ROL";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                rol.getItems().add(new Rol(
                        result.getInt("ID_ROL"),
                        result.getString("NOMBRE")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setUsuarios() {
        TableColumn<Usuario, Integer> columnaId = new TableColumn<>("ID");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("ID_USUARIO"));

        TableColumn<Usuario, String> columnaNombre = new TableColumn<>("Nombre de usuario");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("NOMBRE_USUARIO"));

        TableColumn<Usuario, String> columnaNIT = new TableColumn<>("NIT");
        columnaNIT.setCellValueFactory(new PropertyValueFactory<>("NIT"));

        TableColumn<Usuario, String> columnaDPI = new TableColumn<>("DPI");
        columnaDPI.setCellValueFactory(new PropertyValueFactory<>("DPI"));

        TableColumn<Usuario, Integer> columnaROL = new TableColumn<>("ROL");
        columnaROL.setCellValueFactory(new PropertyValueFactory<>("ID_ROL"));

        userTable.getColumns().addAll(columnaNombre);

        try {
            Connection connection = DB.getConnection();
            String query = "SELECT * FROM USUARIO";
            /* ASEGURATE DE NO ESTAR LOGUEADO EN SQL DEVELOPER, SI NO, NO TRAERA NADA DE INFORMACION YA QUE BLOQUEA LA QUERY */
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if (result != null) {
                ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
                while (result.next()) {
                    listaUsuarios.add(
                            new Usuario(
                                    result.getInt("ID_USUARIO"),
                                    result.getString("NOMBRE_USUARIO"),
                                    result.getString("PRIMER_NOMBRE"),
                                    result.getString("SEGUNDO_NOMBRE"),
                                    result.getString("PRIMER_APELLIDO"),
                                    result.getString("SEGUNDO_APELLIDO"),
                                    result.getString("OTROS_APELLIDOS"),
                                    result.getString("PASSWORD"),
                                    result.getString("CALLE"),
                                    result.getString("COLONIA"),
                                    result.getString("ZONA"),
                                    result.getString("CIUDAD"),
                                    result.getInt("MUNICIPIO"),
                                    result.getInt("DEPARTAMENTO"),
                                    result.getString("CODIGO_POSTAL"),
                                    result.getString("TELEFONO"),
                                    result.getString("NIT"),
                                    result.getString("DPI"),
                                    result.getInt("ID_ROL")
                            )
                    );
                }
                userTable.setItems(listaUsuarios);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta función válida que ningún campo para crear o editar un usuario se encuentre vació
     * si alguno esta vació retorna {@code false}, si no retorna {@code true}.
     */
    private boolean isAllFieldsValid() {
        boolean usernameValue = username.getText().isEmpty();
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
                usernameValue ||
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
     * una vez guardado con exito un usuario
     */
    private void clearAllFields() {
        username.setText("");
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

    /**
     * Método utilizado para obtener los valores del formulario usuarios
     * para posteriormente guardarlos en base de datos
     */
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
        // Obtenemos los valores una vez validamos que no están vacíos
        String usernameValue = username.getText();
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

        String query = "MERGE INTO USUARIO U\n" +
                "USING (\n" +
                "    SELECT\n" +
                "    ? AS ID_USUARIO_S,\n" +
                "    ? AS NOMBRE_USUARIO_S,\n" +
                "    ? AS PRIMER_NOMBRE_S,\n" +
                "    ? AS SEGUNDO_NOMBRE_S,\n" +
                "    ? AS PRIMER_APELLIDO_S,\n" +
                "    ? AS SEGUNDO_APELLIDO_S,\n" +
                "    ? AS OTROS_APELLIDOS_S,\n" +
                "    ? AS PASSWORD_S,\n" +
                "    ? AS CALLE_S,\n" +
                "    ? AS COLONIA_S,\n" +
                "    ? AS ZONA_S,\n" +
                "    ? AS CIUDAD_S,\n" +
                "    ? AS MUNICIPIO_S,\n" +
                "    ? AS DEPARTAMENTO_S,\n" +
                "    ? AS CODIGO_POSTAL_S,\n" +
                "    ? AS TELEFONO_S,\n" +
                "    ? AS NIT_S,\n" +
                "    ? AS DPI_S,\n" +
                "    ? AS ID_ROL_S\n" +
                "    FROM DUAL\n" +
                "    ) S\n" +
                "ON (U.ID_USUARIO = S.ID_USUARIO_S)\n" +
                "WHEN MATCHED THEN\n" +
                "UPDATE SET\n" +
                "    U.NOMBRE_USUARIO = S.NOMBRE_USUARIO_S,\n" +
                "    U.PRIMER_NOMBRE = S.PRIMER_NOMBRE_S,\n" +
                "    U.SEGUNDO_NOMBRE = S.SEGUNDO_NOMBRE_S,\n" +
                "    U.PRIMER_APELLIDO = S.PRIMER_APELLIDO_S,\n" +
                "    U.SEGUNDO_APELLIDO = S.SEGUNDO_APELLIDO_S,\n" +
                "    U.OTROS_APELLIDOS = S.OTROS_APELLIDOS_S,\n" +
                "    U.PASSWORD = S.PASSWORD_S,\n" +
                "    U.CALLE = S.CALLE_S,\n" +
                "    U.COLONIA = S.COLONIA_S,\n" +
                "    U.ZONA = S.ZONA_S,\n" +
                "    U.CIUDAD = S.CIUDAD_S,\n" +
                "    U.MUNICIPIO = S.MUNICIPIO_S,\n" +
                "    U.DEPARTAMENTO = S.DEPARTAMENTO_S,\n" +
                "    U.CODIGO_POSTAL = S.CODIGO_POSTAL_S,\n" +
                "    U.TELEFONO = S.TELEFONO_S,\n" +
                "    U.NIT = S.NIT_S,\n" +
                "    U.DPI = S.DPI_S,\n" +
                "    U.ID_ROL = S.ID_ROL_S\n" +
                "WHEN NOT MATCHED THEN\n" +
                "INSERT (\n" +
                "        U.NOMBRE_USUARIO,\n" +
                "        U.PRIMER_NOMBRE,\n" +
                "        U.SEGUNDO_NOMBRE,\n" +
                "        U.PRIMER_APELLIDO,\n" +
                "        U.SEGUNDO_APELLIDO,\n" +
                "        U.OTROS_APELLIDOS,\n" +
                "        U.PASSWORD,\n" +
                "        U.CALLE,\n" +
                "        U.COLONIA,\n" +
                "        U.ZONA,\n" +
                "        U.CIUDAD,\n" +
                "        U.MUNICIPIO,\n" +
                "        U.DEPARTAMENTO,\n" +
                "        U.CODIGO_POSTAL,\n" +
                "        U.TELEFONO,\n" +
                "        U.NIT,\n" +
                "        U.DPI,\n" +
                "        U.ID_ROL\n" +
                ") VALUES (\n" +
                "        S.NOMBRE_USUARIO_S,\n" +
                "        S.PRIMER_NOMBRE_S,\n" +
                "        S.SEGUNDO_NOMBRE_S,\n" +
                "        S.PRIMER_APELLIDO_S,\n" +
                "        S.SEGUNDO_APELLIDO_S,\n" +
                "        S.OTROS_APELLIDOS_S,\n" +
                "        S.PASSWORD_S,\n" +
                "        S.CALLE_S,\n" +
                "        S.COLONIA_S,\n" +
                "        S.ZONA_S,\n" +
                "        S.CIUDAD_S,\n" +
                "        S.MUNICIPIO_S,\n" +
                "        S.DEPARTAMENTO_S,\n" +
                "        S.CODIGO_POSTAL_S,\n" +
                "        S.TELEFONO_S,\n" +
                "        S.NIT_S,\n" +
                "        S.DPI_S,\n" +
                "        S.ID_ROL_S" +
                ")";
        int userId = 0;
        //PONEMOS UN ID POR DEFECTO QUE SABEMOS QUE NO EXISTE, SI HEMOS SETEADO UN USUARIO OBTENDRA SU ID
        if (usuario != null) {
            userId = usuario.getID_USUARIO();
        }
        try {
            Connection connection = DB.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, usernameValue);
            statement.setString(3, firstName1Value);
            statement.setString(4, firstName2Value);
            statement.setString(5, lastName1Value);
            statement.setString(6, lastName2Value);
            statement.setString(7, lastName3Value);
            statement.setString(8, passValue);
            statement.setString(9, calleValue);
            statement.setString(10, coloniaValue);
            statement.setString(11, zonaValue);
            statement.setString(12, ciudadVal);
            statement.setInt(13, municipioVal.getID_MUNICIPIO());
            statement.setInt(14, departamentoVal.getID_DEPARTAMENTO());
            statement.setString(15, postalVal);
            statement.setString(16, telefonoVal);
            statement.setString(17, nitVal);
            statement.setString(18, dpiVal);
            statement.setInt(19, rolVal.getID_ROL());
            statement.executeQuery();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Operación exitosa");
            alert.setHeaderText(null);
            alert.setContentText("Usuario guardado correctamente");
            alert.showAndWait();
            clearAllFields();
            return;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al tratar de guardar el usuario");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
