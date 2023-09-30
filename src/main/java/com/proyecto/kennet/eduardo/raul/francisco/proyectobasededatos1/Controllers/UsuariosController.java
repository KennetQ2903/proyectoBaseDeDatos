package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;

import com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes.DBConnection;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosController {
    DBConnection DB = new DBConnection();
    @FXML
    private void traerDepartamentos(){
        try {
            Connection connection = DB.getConnection();
            String query = "SELECT * FROM DEPARTAMENTO";
            //ASEGURATE DE NO ESTAR LOGUEADO EN SQL DEVELOPER, SI NO, NO TRAERA NADA DE INFORMACION YA QUE BLOQUEA LA QUERY
            ResultSet result = DB.ExecuteQuery(connection, query);
            if (result != null) {
                while (result.next()) {
                    System.out.println(result.getString("NOMBRE"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
