package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;
public class AppController {
    @FXML
    private AnchorPane scenesContainer; // este componente se encuentra en el FXML APP bajo el id de scenesContainer


    private AnchorPane getScene(String sceneName) throws IOException {
        String ruta = "Scenes/%s.fxml"; //el caracter %s indica donde se reemplazara el valor en la cadena
        String url = ruta.formatted(sceneName); //le decimos que valor reemplazara donde se encuentre el %s
        return FXMLLoader.load(Objects.requireNonNull(Application.class.getResource(url)));
    }
    @FXML
    private void navigateToClientes() {
        try {
            AnchorPane scene = getScene("Clientes");
            scenesContainer.getChildren().setAll(scene);
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
            AnchorPane scene = getScene("Productos");
            scenesContainer.getChildren().setAll(scene);
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores si no se puede cargar "Productos.fxml"
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar la vista de Productos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void navigateToUsuarios() {
        try {
            AnchorPane scene = getScene("Usuarios");
            scenesContainer.getChildren().setAll(scene);
        } catch (Exception e) {
            // Manejo de errores si no se puede cargar "Usuarios.fxml"
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar la vista de Usuarios");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void navigateToEmpresas() {
        try {
            AnchorPane scene = getScene("Empresas");
            scenesContainer.getChildren().setAll(scene);
        } catch (Exception e) {
            // Manejo de errores si no se puede cargar "Empresas.fxml"
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar la vista de Empresas");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void navigateToVendedores() {
        try {
            AnchorPane scene = getScene("Vendedores");
            scenesContainer.getChildren().setAll(scene);
        } catch (Exception e) {
            // Manejo de errores si no se puede cargar "Vendedores.fxml"
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar la vista de Vendedores");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void navigateToProveedores() {
        try {
            AnchorPane scene = getScene("Proveedores");
            scenesContainer.getChildren().setAll(scene);
        } catch (Exception e) {
            // Manejo de errores si no se puede cargar "Proveedores.fxml"
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar la vista de Proveedores");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    private void navigateToColaboradores() {
        try {
            AnchorPane scene = getScene("Colaboradores");
            scenesContainer.getChildren().setAll(scene);
        } catch (Exception e) {
            // Manejo de errores si no se puede cargar "Colaboradores.fxml"
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar la vista de Colaboradores");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    private void navigateToBodegas() {
        try {
            AnchorPane scene = getScene("Bodegas");
            scenesContainer.getChildren().setAll(scene);
        } catch (Exception e) {
            // Manejo de errores si no se puede cargar "Bodegas.fxml"
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar la vista de Bodegas");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void navigateToRoles() {
        try {
            AnchorPane scene = getScene("Roles");
            scenesContainer.getChildren().setAll(scene);
        } catch (Exception e) {
            // Manejo de errores si no se puede cargar "Roles.fxml"
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar la vista de Roles");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}