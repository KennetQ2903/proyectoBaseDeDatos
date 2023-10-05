package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField username;
    public PasswordField password;
    public Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicialización del controlador
    }

    @FXML
    public void handleSubmit(ActionEvent event) {
        if (isValidLogin(username.getText(), password.getText())) {
            try {
                FXMLLoader loader = new FXMLLoader(Application.class.getResource("App.fxml"));
                Parent root = loader.load();

                // Obtener el controlador de la pantalla principal
                AppController appController = loader.getController();

                // Configurar cualquier información necesaria en el controlador de la pantalla principal

                Scene scene = new Scene(root, 1280, 920);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Autenticación fallida, mostrar un mensaje de error
            showMessage("Inicio de Sesión Fallido", "Credenciales incorrectas.");
        }
    }

    private boolean isValidLogin(String username, String password) {
        // En este ejemplo, simplemente verificamos si el nombre de usuario y la contraseña son iguales 1.
        return "1".equals(username) && "1".equals(password);
    }

    // Método para mostrar un mensaje
    private void showMessage(String title, String body) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Mensaje");
        alert.setHeaderText(title);
        alert.setContentText(body);
        alert.showAndWait();
    }

}
