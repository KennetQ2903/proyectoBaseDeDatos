package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

public class Municipio {

    public Municipio(int id, int dep, String codigo, String nombre){
        this.ID_MUNICIPIO = id;
        this.DEPARTAMENTO = dep;
        this.CODIGO = codigo;
        this.NOMBRE = nombre;
    }
    public int getID_MUNICIPIO() {
        return ID_MUNICIPIO;
    }

    public void setID_MUNICIPIO(int ID_MUNICIPIO) {
        this.ID_MUNICIPIO = ID_MUNICIPIO;
    }

    public int getDEPARTAMENTO() {
        return DEPARTAMENTO;
    }

    public void setDEPARTAMENTO(int DEPARTAMENTO) {
        this.DEPARTAMENTO = DEPARTAMENTO;
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
        return getNOMBRE();
    }

    private int ID_MUNICIPIO;
    private int DEPARTAMENTO;
    private String CODIGO;
    private String NOMBRE;
}
