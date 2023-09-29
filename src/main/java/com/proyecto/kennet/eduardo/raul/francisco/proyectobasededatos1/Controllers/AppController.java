package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import java.util.Objects;

public class AppController {
    @FXML
    private AnchorPane scenesContainer; // este componente se encuentra en el FXML APP bajo el id de scenesContainer

    @FXML
    private void navigateToClientes() {
        try {
            AnchorPane clientesView = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("Scenes/Clientes.fxml")));
            scenesContainer.getChildren().setAll(clientesView);
        } catch (Exception e) {
            // Manejo de errores si no se puede cargar "Clientes.fxml"
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar la vista de Clientes");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void navigateToProductos() {
        try {
            AnchorPane clientesView = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("Scenes/Productos.fxml")));
            scenesContainer.getChildren().setAll(clientesView);
        } catch (Exception e) {
            // Manejo de errores si no se puede cargar "Productos.fxml"
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar la vista de Productos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
