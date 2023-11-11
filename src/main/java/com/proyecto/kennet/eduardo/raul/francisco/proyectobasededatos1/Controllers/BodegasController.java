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

public class BodegasController implements Initializable {
    Bodega selectedBodega=null;
    Usuario loggedInUser = null;
    DBConnection DB = new DBConnection();
    ObservableList<Departamento> departamentosList = FXCollections.observableArrayList();
    ObservableList<Municipio> municipiosList = FXCollections.observableArrayList();
    ObservableList<Estado> estadoList = FXCollections.observableArrayList();
    public TextField name;
    public TextField calle;
    public TextField colonia;
    public TextField zona;
    public TextField ciudad;

    public ComboBox<Departamento> departamento;

    public ComboBox<Municipio> municipio;
    public TextField postal;
    public TextField telefono;
    public ComboBox<Estado> state;
    public Button saveButton;
    public TableView<Bodega> bodegaTable;
    public TableColumn<Bodega, Integer> idColumn;
    public TableColumn<Bodega, String> nameColumn;
    public TableColumn<Bodega, String> cityColumn;
    public TableColumn<Bodega, String> districtColumn;
    public TableColumn<Bodega, Integer> phoneColumn;
    public Button editBodegaButton;
    public Button deleteBodegaButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedInUser = AppData.getLoggedInUsername();
        setDepartamentos();
        setEstados();
        setBodegasTable();
        editBodegaButton.setDisable(true);
        deleteBodegaButton.setDisable(true);

        // Agrega un listener para capturar la selección de filas
        bodegaTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Bodega>() {
            @Override
            public void changed(ObservableValue<? extends Bodega> observable, Bodega oldValue, Bodega newValue) {
                if (newValue != null) { //asignamos la bodega seleccionada
                    selectedBodega = newValue;
                    editBodegaButton.setDisable(false);
                    deleteBodegaButton.setDisable(false);
                } else {
                    editBodegaButton.setDisable(true);
                    deleteBodegaButton.setDisable(true);
                }
            }
        });
    }

    private void unselectedBodegaAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Debe seleccionar una bodega");
        alert.showAndWait();
    }
    public void setEstados() {
        Estado state1 = new Estado("HABILITADO", 1);
        Estado state0 = new Estado("DESHABILITADO", 0);
        estadoList.add(state1);
        estadoList.add(state0);
//        state.setItems(estadoList);
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
    private boolean isAllFieldsValid() {
        boolean namebodegaValue = name.getText().isEmpty();
        boolean calleValue = calle.getText().isEmpty();
        boolean coloniaValue = colonia.getText().isEmpty();
        boolean zonaValue = zona.getText().isEmpty();
        boolean ciudadValue = ciudad.getText().isEmpty();
        boolean departamentoVal = departamento.getSelectionModel().getSelectedItem() == null;
        boolean municipioVal = municipio.getSelectionModel().getSelectedItem() == null;
        boolean postalVal = postal.getText().isEmpty();
        boolean telefonoVal = telefono.getText().isEmpty();

        return !(namebodegaValue || calleValue || coloniaValue || zonaValue || ciudadValue || departamentoVal || municipioVal || postalVal || telefonoVal);
    }
    @FXML
    private void clearAllFields() {
        name.setText("");
        calle.setText("");
        colonia.setText("");
        zona.setText("");
        ciudad.setText("");
        departamento.getSelectionModel().clearSelection();
        municipio.getSelectionModel().clearSelection();
        postal.setText("");
        telefono.setText("");
    }
    public void setBodegasTable() {
        bodegaTable.setMinWidth(530);
        // Configuramos las columnas para que correspondan a una propiedad de la clase Bodega
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idBodega"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));


        // TAMAÑOS DE LAS COLUMNAS
        idColumn.setMinWidth(10);
        nameColumn.setMinWidth(100);
        cityColumn.setMinWidth(100);
        districtColumn.setMinWidth(100);
        phoneColumn.setMinWidth(100);
        try {
            Connection connection = DB.getConnection();
            String query = "SELECT * FROM BODEGA";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if (result != null) {
                ObservableList<Bodega> listaBodegas = FXCollections.observableArrayList();
                while (result.next()) {
                    Bodega bodega = new Bodega(
                            result.getInt("id_bodega"),
                            result.getString("nombre"),
                            result.getString("calle"),
                            result.getString("colonia"),
                            result.getString("zona"),
                            result.getString("ciudad"),
                            result.getInt("municipio"),
                            result.getInt("departamento"),
                            result.getString("codigo_postal"),
                            result.getString("telefono"),
                            result.getInt("usuario_mod"),
                            result.getString("fecha_mod")
                    );
                    listaBodegas.add(bodega);
                }
                bodegaTable.setItems(listaBodegas);
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al tratar de cargar las bodegas");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
    private void setBodegaForm() {
        if (selectedBodega == null) {
            unselectedBodegaAlert();
            return;
        }

        // Buscar el departamento por ID
        Departamento departamentoEncontrado = departamentosList.filtered(depto -> depto.getID_DEPARTAMENTO() == selectedBodega.getDepartamento()).stream().findFirst().orElse(null);

        // No hay roles en la clase Bodega, así que esta parte se omite

        name.setText(selectedBodega.getNombreBodega());
        calle.setText(selectedBodega.getCalle());
        colonia.setText(selectedBodega.getColonia());
        zona.setText(selectedBodega.getZona());
        ciudad.setText(selectedBodega.getCiudad());
        departamento.getSelectionModel().select(departamentoEncontrado);

        // Buscar el municipio por ID
        Municipio municipioEncontrado = municipiosList.filtered(mun -> mun.getID_MUNICIPIO() == selectedBodega.getMunicipio()).stream().findFirst().orElse(null);

        municipio.getSelectionModel().select(municipioEncontrado);

        postal.setText(selectedBodega.getCodigoPostal());
        telefono.setText(selectedBodega.getTelefono());
    }
    @FXML
    private void deleteBodega() {
        if (selectedBodega == null) {
            unselectedBodegaAlert();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar");
        alert.setHeaderText("¿Deseas eliminar a la bodega " + selectedBodega.getNombreBodega() + "?");
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
                    String query = "DELETE FROM BODEGA WHERE ID_BODEGA = ?";
                    /* ASEGURATE DE NO ESTAR LOGUEADO EN SQL DEVELOPER, SI NO, NO TRAERA NADA DE INFORMACION YA QUE BLOQUEA LA QUERY */
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, selectedBodega.getIdBodega());
                    statement.executeQuery();
                } catch (SQLException e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("Error al tratar de eliminar la bodega");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
                setBodegasTable();
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

        if (loggedInUser == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Parece que algo ha salido mal al obtener la informacion de la cuenta");
            alert.showAndWait();
            return;
        }

        String nombreValue = name.getText();
        String calleValue = calle.getText();
        String coloniaValue = colonia.getText();
        String zonaValue = zona.getText();
        String ciudadVal = ciudad.getText();
        Municipio municipioVal = municipio.getSelectionModel().getSelectedItem();
        Departamento departamentoVal = departamento.getSelectionModel().getSelectedItem();
        String postalVal = postal.getText();
        String telefonoVal = telefono.getText();

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date fechaMod = new java.sql.Date(utilDate.getTime());

        int usuarioCreaMod = loggedInUser.getIdUsuario();

        int bodegaId = 0;
        if (selectedBodega != null) {
            bodegaId = selectedBodega.getIdBodega();
        }

        String query = """
                MERGE INTO BODEGA B USING(
                    SELECT\s
                        ? AS id_bodega_s,
                        ? AS nombre_s,
                        ? AS calle_s,
                        ? AS colonia_s,
                        ? AS zona_s,
                        ? AS ciudad_s,
                        ? AS municipio_s,
                        ? AS departamento_s,
                        ? AS codigo_postal_s,
                        ? AS telefono_s,
                        ? AS usuario_mod_s,
                        ? AS fecha_mod_s
                    FROM DUAL\s
                ) S ON (B.id_bodega = S.id_bodega_s)
                WHEN MATCHED THEN
                UPDATE SET
                    B.nombre = S.nombre_s,
                    B.calle = S.calle_s,
                    B.colonia = S.colonia_s,
                    B.zona = S.zona_s,
                    B.ciudad = S.ciudad_s,
                    B.municipio = S.municipio_s,
                    B.departamento = S.departamento_s,
                    B.codigo_postal = S.codigo_postal_s,
                    B.telefono = S.telefono_s,
                    B.usuario_mod = S.usuario_mod_s,
                    B.fecha_mod = S.fecha_mod_s
                WHEN NOT MATCHED THEN
                    INSERT (
                        B.nombre,
                        B.calle,
                        B.colonia,
                        B.zona,
                        B.ciudad,
                        B.municipio,
                        B.departamento,
                        B.codigo_postal,
                        B.telefono,
                        B.usuario_mod,
                        B.fecha_mod
                    )
                    VALUES (
                        S.nombre_s,
                        S.calle_s,
                        S.colonia_s,
                        S.zona_s,
                        S.ciudad_s,
                        S.municipio_s,
                        S.departamento_s,
                        S.codigo_postal_s,
                        S.telefono_s,
                        S.usuario_mod_s,
                        S.fecha_mod_s
                    )
                """;

        try {
            Connection connection = DB.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bodegaId);
            statement.setString(2, nombreValue);
            statement.setString(3, calleValue);
            statement.setString(4, coloniaValue);
            statement.setString(5, zonaValue);
            statement.setString(6, ciudadVal);
            statement.setInt(7, municipioVal.getID_MUNICIPIO());
            statement.setInt(8, departamentoVal.getID_DEPARTAMENTO());
            statement.setString(9, postalVal);
            statement.setString(10, telefonoVal);
            statement.setInt(11, usuarioCreaMod);
            statement.setInt(12, usuarioCreaMod);
            statement.setDate(13, fechaMod);

            statement.executeQuery();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Operación exitosa");
            alert.setHeaderText(null);
            alert.setContentText("Bodega guardada correctamente");
            alert.showAndWait();
            clearAllFields();
            setBodegasTable();
            return;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al tratar de guardar la bodega");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
