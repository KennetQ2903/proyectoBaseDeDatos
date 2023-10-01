package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

public class Rol {
    public Rol(int ID_ROL, String NOMBRE) {
        this.ID_ROL = ID_ROL;
        this.NOMBRE = NOMBRE;
    }

    public int getID_ROL() {
        return ID_ROL;
    }

    public void setID_ROL(int ID_ROL) {
        this.ID_ROL = ID_ROL;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    @Override
    public String toString() {
        return getNOMBRE();
    }

    private int ID_ROL;
    private String NOMBRE;

}
