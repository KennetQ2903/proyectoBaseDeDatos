package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;

public class Proveedor {
    public Proveedor(int idProveedor, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String otrosApellidos, String calle, String colonia, String zona, String ciudad, int municipio, int departamento, String codigoPostal, String telefono, String nit, String dpi, String email, int usuarioCreacion, int usuarioMod, String fechaCreacion, String fechaMod, int estado) {
        this.idProveedor = new SimpleIntegerProperty(idProveedor);
        this.primerNombre = new SimpleStringProperty(primerNombre);
        this.segundoNombre = new SimpleStringProperty(segundoNombre);
        this.primerApellido = new SimpleStringProperty(primerApellido);
        this.segundoApellido = new SimpleStringProperty(segundoApellido);
        this.otrosApellidos = new SimpleStringProperty(otrosApellidos);
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
        this.email = new SimpleStringProperty(email);
        this.usuarioCreacion = new SimpleIntegerProperty(usuarioCreacion);
        this.usuarioMod = new SimpleIntegerProperty(usuarioMod);
        this.fechaCreacion = new SimpleStringProperty(fechaCreacion);
        this.fechaMod = new SimpleStringProperty(fechaMod);
        this.estado = new SimpleIntegerProperty(estado);
    }

    public Proveedor() {
        this.idProveedor = new SimpleIntegerProperty(0);
        this.primerNombre = new SimpleStringProperty("");
        this.segundoNombre = new SimpleStringProperty("");
        this.primerApellido = new SimpleStringProperty("");
        this.segundoApellido = new SimpleStringProperty("");
        this.otrosApellidos = new SimpleStringProperty("");
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
        this.email = new SimpleStringProperty("");
        this.usuarioCreacion = new SimpleIntegerProperty(0);
        this.usuarioMod = new SimpleIntegerProperty(0);
        this.fechaCreacion = new SimpleStringProperty("");
        this.fechaMod = new SimpleStringProperty("");
        this.estado = new SimpleIntegerProperty(0);
    }

    public int getIdProveedor() {
        return idProveedor.get();
    }

    public SimpleIntegerProperty idProveedorProperty() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor.set(idProveedor);
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

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getUsuarioCreacion() {
        return usuarioCreacion.get();
    }

    public SimpleIntegerProperty usuarioCreacionProperty() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        this.usuarioCreacion.set(usuarioCreacion);
    }

    public int getUsuarioMod() {
        return usuarioMod.get();
    }

    public SimpleIntegerProperty usuarioModProperty() {
        return usuarioMod;
    }

    public void setUsuarioMod(int usuarioMod) {
        this.usuarioMod.set(usuarioMod);
    }

    public String getFechaCreacion() {
        return fechaCreacion.get();
    }

    public SimpleStringProperty fechaCreacionProperty() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion.set(fechaCreacion);
    }

    public String getFechaMod() {
        return fechaMod.get();
    }

    public SimpleStringProperty fechaModProperty() {
        return fechaMod;
    }

    public void setFechaMod(String fechaMod) {
        this.fechaMod.set(fechaMod);
    }

    public int getEstado() {
        return estado.get();
    }

    public SimpleIntegerProperty estadoProperty() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado.set(estado);
    }

    private final SimpleIntegerProperty idProveedor;
    private final SimpleStringProperty primerNombre;
    private final SimpleStringProperty segundoNombre;
    private final SimpleStringProperty primerApellido;
    private final SimpleStringProperty segundoApellido;
    private final SimpleStringProperty otrosApellidos;
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
    private final SimpleStringProperty email;
    private final SimpleIntegerProperty usuarioCreacion;
    private final SimpleIntegerProperty usuarioMod;
    private final SimpleStringProperty fechaCreacion;
    private final SimpleStringProperty fechaMod;
    private final SimpleIntegerProperty estado;

}
