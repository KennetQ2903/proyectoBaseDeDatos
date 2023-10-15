package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Rol {
    public Rol(int ID_ROL, String NOMBRE) {
        this.ID_ROL = new SimpleIntegerProperty(ID_ROL);
        this.NOMBRE = new SimpleStringProperty(NOMBRE);
    }

    public Rol() {
        this.ID_ROL = new SimpleIntegerProperty(0);
        this.NOMBRE = new SimpleStringProperty("");
    }

    public int getID_ROL() {
        return ID_ROL.get();
    }

    public SimpleIntegerProperty ID_ROLProperty() {
        return ID_ROL;
    }

    public void setID_ROL(int ID_ROL) {
        this.ID_ROL.set(ID_ROL);
    }

    public String getNOMBRE() {
        return NOMBRE.get();
    }

    public SimpleStringProperty NOMBREProperty() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE.set(NOMBRE);
    }

    @Override
    public String toString() {
        return getNOMBRE();
    }

    private SimpleIntegerProperty ID_ROL;
    private SimpleStringProperty NOMBRE;

}
