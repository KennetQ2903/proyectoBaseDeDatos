package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.DBConnection;
import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.Rol;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RolesController implements Initializable {
    DBConnection DB = new DBConnection();
    Rol selectedRol = null;

    public TextField nameRol;
    public RadioButton checkColaboradores;
    public RadioButton checkUsuarios;
    public RadioButton checkRoles;
    public RadioButton checkClientes;
    public RadioButton checkProveedores;
    public RadioButton checkCompras;
    public RadioButton checkVentas;
    public RadioButton checkInventario;
    public RadioButton checkOrdenesCompras;
    public TableView<Rol> tableRol;
    public TableColumn<Rol, Integer> idColumn;
    public TableColumn<Rol, String> nameColumn;
    public Button editButton;
    public Button deleteButton;

    ArrayList<RadioButton> radioButtons = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setRolesTable();
        setRadioButtonsList();
        editButton.setDisable(true);
        deleteButton.setDisable(true);

        tableRol.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Rol>() {
            @Override
            public void changed(ObservableValue<? extends Rol> observable, Rol oldValue, Rol newValue) {
                if (newValue != null) { //asignamos el proveedor seleccionado
                    selectedRol = newValue;
                    editButton.setDisable(false);
                    deleteButton.setDisable(false);
                } else {
                    editButton.setDisable(true);
                    deleteButton.setDisable(true);
                }
            }
        });
    }

    private void setRadioButtonsList() {
        radioButtons.add(checkColaboradores);
        radioButtons.add(checkUsuarios);
        radioButtons.add(checkRoles);
        radioButtons.add(checkClientes);
        radioButtons.add(checkProveedores);
        radioButtons.add(checkCompras);
        radioButtons.add(checkVentas);
        radioButtons.add(checkInventario);
        radioButtons.add(checkOrdenesCompras);
    }

    private void setRolesTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID_ROL"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("NOMBRE"));
        try {
            Connection connection = DB.getConnection();
            String query = "SELECT * FROM ROL";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if (result != null) {
                ObservableList<Rol> listaRoles = FXCollections.observableArrayList();
                while (result.next()) {
                    Rol rol = new Rol(
                            result.getInt("ID_ROL"),
                            result.getString("NOMBRE")
                    );
                    listaRoles.add(rol);
                }
                tableRol.setItems(listaRoles);
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al tratar de cargar los roles");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void unselectedRolAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Debe seleccionar un rol");
        alert.showAndWait();
    }

    @FXML
    private void clearAllFields() {
        nameRol.clear();
        checkColaboradores.setSelected(false);
        checkUsuarios.setSelected(false);
        checkRoles.setSelected(false);
        checkClientes.setSelected(false);
        checkProveedores.setSelected(false);
        checkCompras.setSelected(false);
        checkVentas.setSelected(false);
        checkInventario.setSelected(false);
        checkOrdenesCompras.setSelected(false);
    }

    private boolean isAllFieldsValid() {
        boolean nameVal = nameRol.getText().isEmpty();
        boolean colaboradoresSelected = checkColaboradores.isSelected();
        boolean usuariosSelected = checkUsuarios.isSelected();
        boolean rolesSelected = checkRoles.isSelected();
        boolean clientesSelected = checkClientes.isSelected();
        boolean proveedoresSelected = checkProveedores.isSelected();
        boolean comprasSelected = checkCompras.isSelected();
        boolean ventasSelected = checkVentas.isSelected();
        boolean inventarioSelected = checkInventario.isSelected();
        boolean ordenesComprasSelected = checkOrdenesCompras.isSelected();
        return !nameVal && (colaboradoresSelected || usuariosSelected || rolesSelected ||
                clientesSelected || proveedoresSelected || comprasSelected || ventasSelected ||
                inventarioSelected || ordenesComprasSelected);
    }

    private void setAccesos() {
        int id = selectedRol.getID_ROL();
        //limpiar radio buttons
        for(int i = 0; i <= 8; i++){
            RadioButton radioButtonSelected = radioButtons.get(i);
            radioButtonSelected.setSelected(false);
        }
        //Buscamos los accesos por rol
        try {
            Connection connection = DB.getConnection();
            String query = "SELECT * FROM ROLACCESO WHERE ID_ROL = ? ORDER BY ID_ACCESO ASC";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    int idAcceso = result.getInt("ID_ACCESO") - 1; //index
                    RadioButton radioButtonSelected = radioButtons.get(idAcceso);
//                    boolean granted = result.getBoolean("GRANTED");
                    radioButtonSelected.setSelected(true);
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al tratar de cargar los accesos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void setRolForm() {
        if (selectedRol == null) {
            unselectedRolAlert();
        }
        nameRol.setText(selectedRol.getNOMBRE());
        setAccesos();
    }

    @FXML
    private void deleteRol() {
        if (selectedRol == null) {
            unselectedRolAlert();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar");
        alert.setHeaderText("¿Deseas eliminar el rol " + selectedRol.getNOMBRE() + "?");
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
                    String query = "DELETE FROM ROLACCESO WHERE ID_ROL = ?";
                    String query2 = "DELETE FROM ROL WHERE ID_ROL = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, selectedRol.getID_ROL());
                    statement.executeQuery();

                    PreparedStatement statement2 = connection.prepareStatement(query2);
                    statement2.setInt(1, selectedRol.getID_ROL());
                    statement2.executeQuery();
                } catch (SQLException e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("Error al tratar de eliminar el proveedor");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
                setRolesTable();
            }
        });
    }

    @FXML
    public void HandleSubmit() {
        if (!isAllFieldsValid()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor seleccione al menos un acceso y complete el nombre del rol");
            alert.showAndWait();
            return;
        }
        String rolName = nameRol.getText();

        int idRol = 0;
        if (selectedRol != null) {
            idRol = selectedRol.getID_ROL();
        }

        String query1 = "MERGE INTO ROL R USING(\n" +
                "    SELECT \n" +
                "    ? AS NOMBRE_S,\n" +
                "    ? AS ID_ROL_S\n" +
                "    FROM DUAL\n" +
                ") S ON (R.ID_ROL = S.ID_ROL_S)\n" +
                "WHEN MATCHED THEN\n" +
                "UPDATE SET\n" +
                "    R.NOMBRE = S.NOMBRE_S\n" +
                "WHEN NOT MATCHED THEN\n" +
                "INSERT (\n" +
                "    R.NOMBRE\n" +
                ")\n" +
                "VALUES (\n" +
                "    S.NOMBRE_S\n" +
                ")\n" +
                "    ";


        String query2 = "MERGE INTO ROLACCESO RA USING(\n" +
                "SELECT\n" +
                "? AS ID_ROL_RAS,\n" +
                "? AS GRANTED_RAS,\n" +
                "? AS ID_ACCESO_RAS\n" +
                "FROM DUAL\n" +
                ") RAS ON (RA.ID_ROL = RAS.ID_ROL_RAS AND RA.ID_ACCESO = RAS.ID_ACCESO_RAS)\n" +
                "WHEN MATCHED THEN\n" +
                "UPDATE SET RA.GRANTED = RAS.GRANTED_RAS WHERE RA.ID_ACCESO = RAS.ID_ACCESO_RAS\n" +
                "WHEN NOT MATCHED THEN\n" +
                "INSERT (\n" +
                "RA.ID_ROL,\n" +
                "RA.ID_ACCESO,\n" +
                "RA.GRANTED\n" +
                ") VALUES (\n" +
                "RAS.ID_ROL_RAS,\n" +
                "RAS.ID_ACCESO_RAS,\n" +
                "RAS.GRANTED_RAS\n" +
                ")";

        try {
            Connection connection = DB.getConnection();
            PreparedStatement statement = connection.prepareStatement(query1);
            statement.setString(1, rolName);
            statement.setInt(2, idRol);
            statement.executeQuery();

            // SI SE ESTA AGREGANDO UN NUEVO ROL OBTENDRÍA EL ID DEL ROL RECIÉN CREADO
            if (selectedRol == null) {
                String getLastRolAddedQuery = "SELECT ID_ROL FROM ROL ORDER BY ID_ROL DESC FETCH FIRST ROW ONLY";
                PreparedStatement statementLastRol = connection.prepareStatement(getLastRolAddedQuery);
                ResultSet resultLastRol = statementLastRol.executeQuery();
                while (resultLastRol.next()) {
                    idRol = resultLastRol.getInt("ID_ROL");
                }
            }

            PreparedStatement statement2 = connection.prepareStatement(query2);
            for (int i = 0; i < radioButtons.size(); i++) {
                RadioButton option = radioButtons.get(i);
                statement2.setInt(1, idRol);
                statement2.setBoolean(2, option.isSelected());
                statement2.setInt(3, i + 1);
                statement2.executeQuery();
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Operación exitosa");
            alert.setHeaderText(null);
            alert.setContentText("Rol guardado correctamente");
            alert.showAndWait();
            clearAllFields();
            setRolesTable();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al tratar de guardar el rol");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }
}
