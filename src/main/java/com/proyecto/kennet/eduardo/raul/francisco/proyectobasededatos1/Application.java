package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("App.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 920);
        stage.setTitle("Proyecto Base de Datos 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}