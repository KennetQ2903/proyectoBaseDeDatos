package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    Usuario selectedUser = null;
    Usuario usuario = null;
    DBConnection DB = new DBConnection();
    ObservableList<Departamento> departamentosList = FXCollections.observableArrayList();
    ObservableList<Municipio> municipiosList = FXCollections.observableArrayList();
    ObservableList<Rol> rolesList = FXCollections.observableArrayList();
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
    public TableColumn<Usuario, Integer> idColumn;
    public TableColumn<Usuario, String> nameColumn;
    public TableColumn<Usuario, String> nitColumn;
    public TableColumn<Usuario, String> dpiColumn;
    public TableColumn<Usuario, Integer> rolColumn;
    public Button editUserButton;
    public Button deleteUserButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Código para inicializar componentes o realizar acciones al cargar el FXML
        setDepartamentos();
        setRoles();
        setUsuarios();

        editUserButton.setDisable(true);
        deleteUserButton.setDisable(true);

        // Agrega un listener para capturar la selección de filas
        userTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Usuario>() {
            @Override
            public void changed(ObservableValue<? extends Usuario> observable, Usuario oldValue, Usuario newValue) {
                if (newValue != null) { //asignamos el usuario seleccionado
                    selectedUser = newValue;
                    editUserButton.setDisable(false);
                    deleteUserButton.setDisable(false);
                } else {
                    editUserButton.setDisable(true);
                    deleteUserButton.setDisable(true);
                }
            }
        });
    }

    private void unselectedUserAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Debe seleccionar un usuario");
        alert.showAndWait();
    }

    @FXML
    private void deleteUser() {
        if (selectedUser == null) {
            unselectedUserAlert();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar");
        alert.setHeaderText("¿Deseas eliminar al usuario " + selectedUser.getNombreUsuario() + "?");
        alert.setContentText("Seleccione una opción:");
        ButtonType buttonTypeSi = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);

        // Mostrar la alerta y esperar a que el usuario seleccione una opción
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeSi) {
                // El usuario seleccionó "Sí"
                try {
                    Connection connection = DB.getConnection();
                    String query = "DELETE FROM USUARIO WHERE ID_USUARIO = ?";
                    /* ASEGURATE DE NO ESTAR LOGUEADO EN SQL DEVELOPER, SI NO, NO TRAERA NADA DE INFORMACION YA QUE BLOQUEA LA QUERY */
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, selectedUser.getIdUsuario());
                    statement.executeQuery();
                } catch (SQLException e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("Error al tratar de eliminar el usuario");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
                setUsuarios();
            }
        });
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
     * Metodo utilizado para obtener el listado de usuarios y asignarlos
     * a la TableView de usuarios
     */
    private void setUsuarios() {
        try {
            //Configuramos las columnas para que correspondan a una propiedad de la clase Usuario
            idColumn.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
            nitColumn.setCellValueFactory(new PropertyValueFactory<>("nit"));
            dpiColumn.setCellValueFactory(new PropertyValueFactory<>("dpi"));
            rolColumn.setCellValueFactory(new PropertyValueFactory<>("idRol"));
            // TAMAÑOS DE LAS COLUMNAS
            idColumn.setMinWidth(80);
            nameColumn.setMinWidth(120);
            nitColumn.setMinWidth(120);
            dpiColumn.setMinWidth(120);
            rolColumn.setMinWidth(80);

            Connection connection = DB.getConnection();
            String query = "SELECT * FROM USUARIO";
            /* ASEGURATE DE NO ESTAR LOGUEADO EN SQL DEVELOPER, SI NO, NO TRAERA NADA DE INFORMACION YA QUE BLOQUEA LA QUERY */
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if (result != null) {
                ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
                while (result.next()) {
                    Usuario user = new Usuario(
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
                    );
                    listaUsuarios.add(user);
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
    @FXML
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
     * Esta función se utiliza para setear el formulario
     * una vez obtenido con exito el usuario
     */
    @FXML
    private void setUserForm() {
        if (selectedUser == null) {
            unselectedUserAlert();
        }

        // Buscar el departamento por ID
        Departamento departamentoEncontrado = departamentosList.filtered(depto -> depto.getID_DEPARTAMENTO() == selectedUser.getDepartamento()).stream().findFirst().orElse(null);

        Rol rolEncontrado = rolesList.filtered(rol -> rol.getID_ROL() == selectedUser.getIdRol()).stream().findFirst().orElse(null);

        username.setText(selectedUser.getNombreUsuario());
        firstname1.setText(selectedUser.getPrimerNombre());
        firstname2.setText(selectedUser.getSegundoNombre());
        lastname1.setText(selectedUser.getPrimerApellido());
        lastname2.setText(selectedUser.getSegundoApellido());
        lastname3.setText(selectedUser.getOtrosApellidos());
        password.setText(selectedUser.getPassword());
        calle.setText(selectedUser.getCalle());
        colonia.setText(selectedUser.getColonia());
        zona.setText(selectedUser.getZona());
        ciudad.setText(selectedUser.getCiudad());
        departamento.getSelectionModel().select(departamentoEncontrado);

        //una vez accionamos la selección del departamento este lo buscará y podremos luego buscarlo en el listado de municipios por departamento
        Municipio municipioEncontrado = municipiosList.filtered(mun -> mun.getID_MUNICIPIO() == selectedUser.getMunicipio()).stream().findFirst().orElse(null);

        municipio.getSelectionModel().select(municipioEncontrado);

        rol.getSelectionModel().select(rolEncontrado);
        postal.setText(selectedUser.getCodigoPostal());
        telefono.setText(selectedUser.getTelefono());
        nit.setText(selectedUser.getNit());
        dpi.setText(selectedUser.getDpi());
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
        if (selectedUser != null) {
            userId = selectedUser.getIdUsuario();
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
            setUsuarios();
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
