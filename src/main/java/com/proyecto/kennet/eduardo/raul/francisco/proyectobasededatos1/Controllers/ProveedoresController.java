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

public class ProveedoresController implements Initializable {
    Proveedor selectedProvider = null;
    Usuario loggedInUser = null;
    DBConnection DB = new DBConnection();
    ObservableList<Departamento> departamentosList = FXCollections.observableArrayList();
    ObservableList<Municipio> municipiosList = FXCollections.observableArrayList();
    ObservableList<Estado> estadoList = FXCollections.observableArrayList();
    ObservableList<Rol> rolesList = FXCollections.observableArrayList();
    public TextField firstname1;
    public TextField firstname2;
    public TextField lastname1;
    public TextField lastname2;
    public TextField lastname3;
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
    public TextField email;
    public ComboBox<Estado> state;
    public Button editProveedorButton;
    public Button deleteProveedorButton;
    public TableView<Proveedor> proveedoresTable;
    public TableColumn<Proveedor, Integer> idColumn;
    public TableColumn<Proveedor, String> nameColumn;
    public TableColumn<Proveedor, String> nitColumn;
    public TableColumn<Proveedor, String> dpiColumn;
    public TableColumn<Proveedor, Integer> stateColumn;
    public TableColumn<Proveedor, String> fechaCreaColumn;
    public TableColumn<Proveedor, String> fechaModColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedInUser = AppData.getLoggedInUsername();
        setDepartamentos();
        setEstados();
        setProveedoresTable();
        editProveedorButton.setDisable(true);
        deleteProveedorButton.setDisable(true);

        proveedoresTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Proveedor>() {
            @Override
            public void changed(ObservableValue<? extends Proveedor> observable, Proveedor oldValue, Proveedor newValue) {
                if (newValue != null) { //asignamos el proveedor seleccionado
                    selectedProvider = newValue;
                    editProveedorButton.setDisable(false);
                    deleteProveedorButton.setDisable(false);
                } else {
                    editProveedorButton.setDisable(true);
                    deleteProveedorButton.setDisable(true);
                }
            }
        });
    }

    private void unselectedProviderAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Debe seleccionar un proveedor");
        alert.showAndWait();
    }

    public void setEstados() {
        Estado state1 = new Estado("HABILITADO", 1);
        Estado state0 = new Estado("DESHABILITADO", 0);
        estadoList.add(state1);
        estadoList.add(state0);
        state.setItems(estadoList);
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
                    Departamento dep = new Departamento(result.getInt("ID_DEPARTAMENTO"), result.getString("CODIGO"), result.getString("NOMBRE"));
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
                Municipio mun = new Municipio(result.getInt("ID_MUNICIPIO"), result.getInt("DEPARTAMENTO"), result.getString("CODIGO"), result.getString("NOMBRE"));
                municipiosList.add(mun);
            }
            municipio.setItems(municipiosList);
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
//        boolean calleValue = calle.getText().isEmpty();
//        boolean coloniaValue = colonia.getText().isEmpty();
//        boolean zonaValue = zona.getText().isEmpty();
//        boolean ciudadVal = ciudad.getText().isEmpty();
        boolean departamentoVal = departamento.getSelectionModel().getSelectedItem() == null;
        boolean municipioVal = municipio.getSelectionModel().getSelectedItem() == null;
        boolean stateVal = state.getSelectionModel().getSelectedItem() == null;
        boolean postalVal = postal.getText().isEmpty();
        boolean telefonoVal = telefono.getText().isEmpty();
        boolean nitVal = nit.getText().isEmpty();
        boolean dpiVal = dpi.getText().isEmpty();
        boolean emailVal = email.getText().isEmpty();
        return !(firstName1Value || firstName2Value || lastName1Value || lastName2Value || departamentoVal || municipioVal || postalVal || telefonoVal || nitVal || dpiVal || emailVal || stateVal);
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
        calle.setText("");
        colonia.setText("");
        zona.setText("");
        ciudad.setText("");
        departamento.getSelectionModel().clearSelection();
        municipio.getSelectionModel().clearSelection();
        state.getSelectionModel().clearSelection();
        postal.setText("");
        telefono.setText("");
        nit.setText("");
        dpi.setText("");
        email.setText("");
    }

    public void setProveedoresTable() {
        proveedoresTable.setMinWidth(530);
        //Configuramos las columnas para que correspondan a una propiedad de la clase Proveedor
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("primerNombre"));
        nitColumn.setCellValueFactory(new PropertyValueFactory<>("nit"));
        dpiColumn.setCellValueFactory(new PropertyValueFactory<>("dpi"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        fechaCreaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        fechaModColumn.setCellValueFactory(new PropertyValueFactory<>("fechaMod"));

        // TAMAÑOS DE LAS COLUMNAS
        idColumn.setMinWidth(10);
        nameColumn.setMinWidth(100);
        nitColumn.setMinWidth(100);
        dpiColumn.setMinWidth(100);
        stateColumn.setMinWidth(10);
        fechaCreaColumn.setMinWidth(100);
        fechaModColumn.setMinWidth(100);

        try{
            Connection connection = DB.getConnection();
            String query = "SELECT * FROM PROVEEDOR";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if(result != null){
                ObservableList<Proveedor> listaProveedores = FXCollections.observableArrayList();
                while(result.next()){
                    Proveedor proveedor = new Proveedor(
                            result.getInt("id_proveedor"),
                            result.getString("primer_nombre"),
                            result.getString("segundo_nombre"),
                            result.getString("primer_apellido"),
                            result.getString("segundo_apellido"),
                            result.getString("otros_apellidos"),
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
                            result.getString("email"),
                            result.getInt("usuario_creacion"),
                            result.getInt("usuario_mod"),
                            result.getDate("fecha_creacion").toString(),
                            result.getDate("fecha_mod").toString(),
                            result.getInt("estado")
                    );
                    listaProveedores.add(proveedor);
                }
                proveedoresTable.setItems(listaProveedores);
            }
        } catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al tratar de cargar los proveedores");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void setProveedorForm() {
        if (selectedProvider == null) {
            unselectedProviderAlert();
        }
        // Buscar el departamento por ID
        Departamento departamentoEncontrado = departamentosList.filtered(depto -> depto.getID_DEPARTAMENTO() == selectedProvider.getDepartamento()).stream().findFirst().orElse(null);
        Estado estadoEncontrado = estadoList.filtered(stat -> stat.getState() == selectedProvider.getEstado()).stream().findFirst().orElse(null);
        firstname1.setText(selectedProvider.getPrimerNombre());
        firstname2.setText(selectedProvider.getSegundoNombre());
        lastname1.setText(selectedProvider.getPrimerApellido());
        lastname2.setText(selectedProvider.getSegundoApellido());
        lastname3.setText(selectedProvider.getOtrosApellidos());
        calle.setText(selectedProvider.getCalle());
        colonia.setText(selectedProvider.getColonia());
        zona.setText(selectedProvider.getZona());
        ciudad.setText(selectedProvider.getCiudad());
        departamento.getSelectionModel().select(departamentoEncontrado);
        state.getSelectionModel().select(estadoEncontrado);
        //una vez accionamos la selección del departamento este lo buscará y podremos luego buscarlo en el listado de municipios por departamento
        Municipio municipioEncontrado = municipiosList.filtered(mun -> mun.getID_MUNICIPIO() == selectedProvider.getMunicipio()).stream().findFirst().orElse(null);

        municipio.getSelectionModel().select(municipioEncontrado);
        postal.setText(selectedProvider.getCodigoPostal());
        telefono.setText(selectedProvider.getTelefono());
        nit.setText(selectedProvider.getNit());
        dpi.setText(selectedProvider.getDpi());
        email.setText(selectedProvider.getEmail());
    }

    @FXML
    private void deleteProveedor() {
        if (selectedProvider == null) {
            unselectedProviderAlert();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar");
        alert.setHeaderText("¿Deseas eliminar al proveedor " + selectedProvider.getPrimerNombre() + "?");
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
                    String query = "DELETE FROM PROVEEDOR WHERE id_proveedor = ?";
                    /* ASEGURATE DE NO ESTAR LOGUEADO EN SQL DEVELOPER, SI NO, NO TRAERA NADA DE INFORMACION YA QUE BLOQUEA LA QUERY */
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, selectedProvider.getIdProveedor());
                    statement.executeQuery();
                } catch (SQLException e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("Error al tratar de eliminar el proveedor");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
                setProveedoresTable();
            }
        });
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

        if(loggedInUser == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Parece que algo ha salido mal al obtener la informacion de la cuenta");
            alert.showAndWait();
            return;
        }

        String firstName1Value = firstname1.getText();
        String firstName2Value = firstname2.getText();
        String lastName1Value = lastname1.getText();
        String lastName2Value = lastname2.getText();
        String lastName3Value = lastname3.getText();
        String calleValue = calle.getText();
        String coloniaValue = colonia.getText();
        String zonaValue = zona.getText();
        String ciudadVal = ciudad.getText();
        Departamento departamentoVal = departamento.getSelectionModel().getSelectedItem();
        Municipio municipioVal = municipio.getSelectionModel().getSelectedItem();
        Estado stateVal = state.getSelectionModel().getSelectedItem();
        String postalVal = postal.getText();
        String telefonoVal = telefono.getText();
        String nitVal = nit.getText();
        String dpiVal = dpi.getText();
        String emailVal = email.getText();

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date fechaMod = new java.sql.Date(utilDate.getTime());

        int usuarioCreaMod = loggedInUser.getIdUsuario();

        int providerId = 0;
        if (selectedProvider != null) {
            providerId = selectedProvider.getIdProveedor();
        }

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
//                "        ? AS fecha_creacion_s,\n" +
                "        ? AS fecha_mod_s,\n" +
                "        ? AS estado_s\n" +
                "    FROM DUAL \n" +
                ") S ON (P.id_proveedor = S.id_proveedor_s)\n" +
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
//                "        P.fecha_creacion,\n" +
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
//                "        S.fecha_creacion_s,\n" +
                "        S.fecha_mod_s,\n" +
                "        S.estado_s\n" +
                "    )\n";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, providerId);
            statement.setString(2, firstName1Value);
            statement.setString(3, firstName2Value);
            statement.setString(4, lastName1Value);
            statement.setString(5, lastName2Value);
            statement.setString(6, lastName3Value);
            statement.setString(7, calleValue);
            statement.setString(8, coloniaValue);
            statement.setString(9, zonaValue);
            statement.setString(10, ciudadVal);
            statement.setInt(11, municipioVal.getID_MUNICIPIO());
            statement.setInt(12, departamentoVal.getID_DEPARTAMENTO());
            statement.setString(13, postalVal);
            statement.setString(14, telefonoVal);
            statement.setString(15, nitVal);
            statement.setString(16, dpiVal);
            statement.setString(17, emailVal);
            statement.setInt(18, usuarioCreaMod);
            statement.setInt(19, usuarioCreaMod);
            statement.setDate(20, fechaMod);
            statement.setInt(21, stateVal.getState());

            statement.executeQuery();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Operación exitosa");
            alert.setHeaderText(null);
            alert.setContentText("Proveedor guardado correctamente");
            alert.showAndWait();
            clearAllFields();
            setProveedoresTable();
            return;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al tratar de guardar el proveedor");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
