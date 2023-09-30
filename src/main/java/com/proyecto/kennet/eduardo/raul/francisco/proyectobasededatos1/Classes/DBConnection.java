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
        String password = "1234";
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

    public ResultSet ExecuteQuery(Connection connection , String query) {
        try {
            // si no fue posible conectar con la base de datos se valida aca y hacemos un return seguro
            if (connection == null) {
                return null;
            }
            // Prepara la consulta
            Statement statement = connection.createStatement();
            // Ejecuta la consulta
            return statement.executeQuery(query);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al tratar de ejecutar una consulta");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return null;
    }
}
