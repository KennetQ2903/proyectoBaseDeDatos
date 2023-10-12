package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

import javafx.scene.control.Alert;

import java.sql.*;

public class DBConnection {

    public DBConnection() {
    }

    public Connection getConnection() throws SQLException {
        // Cambia la URL según tu configuración
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "SYSTEM";
        String password = "xv34";
        try {
            // Establece la conexión
            return DriverManager.getConnection(jdbcUrl, user, password);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al conectar con la base de datos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return null;
    }
}
