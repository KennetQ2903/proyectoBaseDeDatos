package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

public class AppData {
    private static Usuario loggedInUsername; // Variable global

    public static Usuario getLoggedInUsername() {
        return loggedInUsername;
    }

    public static void setLoggedInUsername(Usuario username) {
        loggedInUsername = username;
    }
}
