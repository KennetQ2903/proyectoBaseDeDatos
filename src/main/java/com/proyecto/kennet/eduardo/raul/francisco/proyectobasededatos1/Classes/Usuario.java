package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

public class Usuario {
    public Usuario(int ID_USUARIO, String NOMBRE_USUARIO, String PRIMER_NOMBRE, String SEGUNDO_NOMBRE, String PRIMER_APELLIDO, String SEGUNDO_APELLIDO, String OTROS_APELLIDOS, String PASSWORD, String CALLE, String COLONIA, String ZONA, String CIUDAD, int MUNICIPIO, int DEPARTAMENTO, String CODIGO_POSTAL, String TELEFONO, String NIT, String DPI, int ID_ROL) {
        this.ID_USUARIO = ID_USUARIO;
        this.NOMBRE_USUARIO = NOMBRE_USUARIO;
        this.PRIMER_NOMBRE = PRIMER_NOMBRE;
        this.SEGUNDO_NOMBRE = SEGUNDO_NOMBRE;
        this.PRIMER_APELLIDO = PRIMER_APELLIDO;
        this.SEGUNDO_APELLIDO = SEGUNDO_APELLIDO;
        this.OTROS_APELLIDOS = OTROS_APELLIDOS;
        this.PASSWORD = PASSWORD;
        this.CALLE = CALLE;
        this.COLONIA = COLONIA;
        this.ZONA = ZONA;
        this.CIUDAD = CIUDAD;
        this.MUNICIPIO = MUNICIPIO;
        this.DEPARTAMENTO = DEPARTAMENTO;
        this.CODIGO_POSTAL = CODIGO_POSTAL;
        this.TELEFONO = TELEFONO;
        this.NIT = NIT;
        this.DPI = DPI;
        this.ID_ROL = ID_ROL;
    }

    public int getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(int ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }


    public String getNOMBRE_USUARIO() {
        return NOMBRE_USUARIO;
    }

    public void setNOMBRE_USUARIO(String NOMBRE_USUARIO) {
        this.NOMBRE_USUARIO = NOMBRE_USUARIO;
    }

    public String getPRIMER_NOMBRE() {
        return PRIMER_NOMBRE;
    }

    public void setPRIMER_NOMBRE(String PRIMER_NOMBRE) {
        this.PRIMER_NOMBRE = PRIMER_NOMBRE;
    }

    public String getSEGUNDO_NOMBRE() {
        return SEGUNDO_NOMBRE;
    }

    public void setSEGUNDO_NOMBRE(String SEGUNDO_NOMBRE) {
        this.SEGUNDO_NOMBRE = SEGUNDO_NOMBRE;
    }

    public String getPRIMER_APELLIDO() {
        return PRIMER_APELLIDO;
    }

    public void setPRIMER_APELLIDO(String PRIMER_APELLIDO) {
        this.PRIMER_APELLIDO = PRIMER_APELLIDO;
    }

    public String getSEGUNDO_APELLIDO() {
        return SEGUNDO_APELLIDO;
    }

    public void setSEGUNDO_APELLIDO(String SEGUNDO_APELLIDO) {
        this.SEGUNDO_APELLIDO = SEGUNDO_APELLIDO;
    }

    public String getOTROS_APELLIDOS() {
        return OTROS_APELLIDOS;
    }

    public void setOTROS_APELLIDOS(String OTROS_APELLIDOS) {
        this.OTROS_APELLIDOS = OTROS_APELLIDOS;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getCALLE() {
        return CALLE;
    }

    public void setCALLE(String CALLE) {
        this.CALLE = CALLE;
    }

    public String getCOLONIA() {
        return COLONIA;
    }

    public void setCOLONIA(String COLONIA) {
        this.COLONIA = COLONIA;
    }

    public String getZONA() {
        return ZONA;
    }

    public void setZONA(String ZONA) {
        this.ZONA = ZONA;
    }

    public String getCIUDAD() {
        return CIUDAD;
    }

    public void setCIUDAD(String CIUDAD) {
        this.CIUDAD = CIUDAD;
    }

    public int getMUNICIPIO() {
        return MUNICIPIO;
    }

    public void setMUNICIPIO(int MUNICIPIO) {
        this.MUNICIPIO = MUNICIPIO;
    }

    public int getDEPARTAMENTO() {
        return DEPARTAMENTO;
    }

    public void setDEPARTAMENTO(int DEPARTAMENTO) {
        this.DEPARTAMENTO = DEPARTAMENTO;
    }

    public String getCODIGO_POSTAL() {
        return CODIGO_POSTAL;
    }

    public void setCODIGO_POSTAL(String CODIGO_POSTAL) {
        this.CODIGO_POSTAL = CODIGO_POSTAL;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public int getID_ROL() {
        return ID_ROL;
    }

    public void setID_ROL(int ID_ROL) {
        this.ID_ROL = ID_ROL;
    }

    private int ID_USUARIO;
    private String NOMBRE_USUARIO;
    private String PRIMER_NOMBRE;
    private String SEGUNDO_NOMBRE;
    private String PRIMER_APELLIDO;
    private String SEGUNDO_APELLIDO;
    private String OTROS_APELLIDOS;
    private String PASSWORD;
    private String CALLE;
    private String COLONIA;
    private String ZONA;
    private String CIUDAD;
    private int MUNICIPIO;
    private int DEPARTAMENTO;
    private String CODIGO_POSTAL;
    private String TELEFONO;
    private String NIT;
    private String DPI;
    private int ID_ROL;
}
