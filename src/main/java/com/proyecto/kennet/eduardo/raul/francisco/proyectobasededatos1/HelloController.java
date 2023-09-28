package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onHelloButtonClick2() {
        welcomeText.setText("Second function!");
    }
}