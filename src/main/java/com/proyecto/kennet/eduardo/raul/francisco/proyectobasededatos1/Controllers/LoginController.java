package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Application;
import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.AppData;
import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.DBConnection;
import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.Usuario;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    DBConnection DB = new DBConnection();
    public TextField username;
    public PasswordField password;
    public Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicialización del controlador
    }

    @FXML
    public void handleSubmit(ActionEvent event) throws SQLException {
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

    private boolean isValidLogin(String username, String password)  {
        try {
            Connection connection = DB.getConnection();
            String query = "SELECT JSON_OBJECT(\n" +
                    "    'idUsuario' VALUE ID_USUARIO,\n" +
                    "    'nombreUsuario' VALUE NOMBRE_USUARIO,\n" +
                    "    'primerNombre' VALUE PRIMER_NOMBRE,\n" +
                    "    'segundoNombre' VALUE SEGUNDO_NOMBRE,\n" +
                    "    'primerApellido' VALUE PRIMER_APELLIDO,\n" +
                    "    'segundoApellido' VALUE SEGUNDO_APELLIDO,\n" +
                    "    'otrosApellidos' VALUE OTROS_APELLIDOS,\n" +
                    "    'password' VALUE PASSWORD,\n" +
                    "    'calle' VALUE CALLE,\n" +
                    "    'colonia' VALUE COLONIA,\n" +
                    "    'zona' VALUE ZONA,\n" +
                    "    'ciudad' VALUE CIUDAD,\n" +
                    "    'municipio' VALUE MUNICIPIO,\n" +
                    "    'departamento' VALUE DEPARTAMENTO,\n" +
                    "    'codigoPostal' VALUE CODIGO_POSTAL,\n" +
                    "    'telefono' VALUE TELEFONO,\n" +
                    "    'nit' VALUE NIT,\n" +
                    "    'dpi' VALUE DPI,\n" +
                    "    'idRol' VALUE ID_ROL\n" +
                    ") AS JSON_RESULT\n" +
                    "FROM USUARIO\n" +
                    "WHERE NOMBRE_USUARIO = ? AND PASSWORD = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Recupera el resultado como una cadena JSON
                String jsonString = resultSet.getString("JSON_RESULT");
                // Inicializa el ObjectMapper de Jackson
                ObjectMapper objectMapper = new ObjectMapper();
                // Deserializa el JSON en un objeto Usuario
                Usuario usuario = objectMapper.readValue(jsonString, Usuario.class);
                // Ahora, usuario contiene los datos deserializados del JSON y se le asigna a la variable global
                AppData.setLoggedInUsername(usuario);
                return true;
            } else {
                return false;
            }
        } catch (JsonMappingException e) {
            showMessage("Algo salio mal", e.getMessage());
            return false;
        } catch (SQLException | JsonProcessingException e) {
            showMessage("Algo salio mal", e.getMessage());
            return false;
        }
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
