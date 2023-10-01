package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

public class Departamento {
    public Departamento(int id, String codigo, String nombre){
        this.ID_DEPARTAMENTO = id;
        this.CODIGO = codigo;
        this.NOMBRE = nombre;
    }
    public int getID_DEPARTAMENTO() {
        return ID_DEPARTAMENTO;
    }

    public void setID_DEPARTAMENTO(int ID_DEPARTAMENTO) {
        this.ID_DEPARTAMENTO = ID_DEPARTAMENTO;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    @Override
    public String toString() {
        // Define cómo se mostrará el elemento en el ComboBox (solo el nombre)
        return getNOMBRE();
    }

    private int ID_DEPARTAMENTO;
    private String CODIGO;
    private String NOMBRE;
}
