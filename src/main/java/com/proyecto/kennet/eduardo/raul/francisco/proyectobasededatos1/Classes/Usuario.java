package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Usuario {

    public Usuario(int idUsuario, String nombreUsuario, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String otrosApellidos, String password, String calle, String colonia, String zona, String ciudad, int municipio, int departamento, String codigoPostal, String telefono, String nit, String dpi, int idRol) {
        this.idUsuario = new SimpleIntegerProperty(idUsuario);
        this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
        this.primerNombre = new SimpleStringProperty(primerNombre);
        this.segundoNombre = new SimpleStringProperty(segundoNombre);
        this.primerApellido = new SimpleStringProperty(primerApellido);
        this.segundoApellido = new SimpleStringProperty(segundoApellido);
        this.otrosApellidos = new SimpleStringProperty(otrosApellidos);
        this.password = new SimpleStringProperty(password);
        this.calle = new SimpleStringProperty(calle);
        this.colonia = new SimpleStringProperty(colonia);
        this.zona = new SimpleStringProperty(zona);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.municipio = new SimpleIntegerProperty(municipio);
        this.departamento = new SimpleIntegerProperty(departamento);
        this.codigoPostal = new SimpleStringProperty(codigoPostal);
        this.telefono = new SimpleStringProperty(telefono);
        this.nit = new SimpleStringProperty(nit);
        this.dpi = new SimpleStringProperty(dpi);
        this.idRol = new SimpleIntegerProperty(idRol);
    }

    public Usuario() {
        // Constructor predeterminado sin argumentos
        this.idUsuario = new SimpleIntegerProperty(0);
        this.nombreUsuario = new SimpleStringProperty("");
        this.primerNombre = new SimpleStringProperty("");
        this.segundoNombre = new SimpleStringProperty("");
        this.primerApellido = new SimpleStringProperty("");
        this.segundoApellido = new SimpleStringProperty("");
        this.otrosApellidos = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.calle = new SimpleStringProperty("");
        this.colonia = new SimpleStringProperty("");
        this.zona = new SimpleStringProperty("");
        this.ciudad = new SimpleStringProperty("");
        this.municipio = new SimpleIntegerProperty(0);
        this.departamento = new SimpleIntegerProperty(0);
        this.codigoPostal = new SimpleStringProperty("");
        this.telefono = new SimpleStringProperty("");
        this.nit = new SimpleStringProperty("");
        this.dpi = new SimpleStringProperty("");
        this.idRol = new SimpleIntegerProperty(0);
    }

    public int getIdUsuario() {
        return idUsuario.get();
    }

    public SimpleIntegerProperty idUsuarioProperty() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario.set(idUsuario);
    }

    public String getNombreUsuario() {
        return nombreUsuario.get();
    }

    public SimpleStringProperty nombreUsuarioProperty() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario.set(nombreUsuario);
    }

    public String getPrimerNombre() {
        return primerNombre.get();
    }

    public SimpleStringProperty primerNombreProperty() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre.set(primerNombre);
    }

    public String getSegundoNombre() {
        return segundoNombre.get();
    }

    public SimpleStringProperty segundoNombreProperty() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre.set(segundoNombre);
    }

    public String getPrimerApellido() {
        return primerApellido.get();
    }

    public SimpleStringProperty primerApellidoProperty() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido.set(primerApellido);
    }

    public String getSegundoApellido() {
        return segundoApellido.get();
    }

    public SimpleStringProperty segundoApellidoProperty() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido.set(segundoApellido);
    }

    public String getOtrosApellidos() {
        return otrosApellidos.get();
    }

    public SimpleStringProperty otrosApellidosProperty() {
        return otrosApellidos;
    }

    public void setOtrosApellidos(String otrosApellidos) {
        this.otrosApellidos.set(otrosApellidos);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getCalle() {
        return calle.get();
    }

    public SimpleStringProperty calleProperty() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle.set(calle);
    }

    public String getColonia() {
        return colonia.get();
    }

    public SimpleStringProperty coloniaProperty() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia.set(colonia);
    }

    public String getZona() {
        return zona.get();
    }

    public SimpleStringProperty zonaProperty() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona.set(zona);
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public SimpleStringProperty ciudadProperty() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public int getMunicipio() {
        return municipio.get();
    }

    public SimpleIntegerProperty municipioProperty() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio.set(municipio);
    }

    public int getDepartamento() {
        return departamento.get();
    }

    public SimpleIntegerProperty departamentoProperty() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento.set(departamento);
    }

    public String getCodigoPostal() {
        return codigoPostal.get();
    }

    public SimpleStringProperty codigoPostalProperty() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal.set(codigoPostal);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public SimpleStringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getNit() {
        return nit.get();
    }

    public SimpleStringProperty nitProperty() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit.set(nit);
    }

    public String getDpi() {
        return dpi.get();
    }

    public SimpleStringProperty dpiProperty() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi.set(dpi);
    }

    public int getIdRol() {
        return idRol.get();
    }

    public SimpleIntegerProperty idRolProperty() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol.set(idRol);
    }

    private final SimpleIntegerProperty idUsuario;

    private final SimpleStringProperty nombreUsuario;

    private final SimpleStringProperty primerNombre;
    private final SimpleStringProperty segundoNombre;
    private final SimpleStringProperty primerApellido;
    private final SimpleStringProperty segundoApellido;
    private final SimpleStringProperty otrosApellidos;
    private final SimpleStringProperty password;
    private final SimpleStringProperty calle;
    private final SimpleStringProperty colonia;
    private final SimpleStringProperty zona;
    private final SimpleStringProperty ciudad;
    private final SimpleIntegerProperty municipio;
    private final SimpleIntegerProperty departamento;
    private final SimpleStringProperty codigoPostal;
    private final SimpleStringProperty telefono;

    private final SimpleStringProperty nit;
    private final SimpleStringProperty dpi;
    private final SimpleIntegerProperty idRol;
}

