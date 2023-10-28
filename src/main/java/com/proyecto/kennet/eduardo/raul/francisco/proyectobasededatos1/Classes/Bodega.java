package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Bodega {

    public Bodega(int idBodega, String nombreBodega, String calle, String colonia, String zona, String ciudad, int municipio, int departamento, String codigoPostal, String telefono, int usuarioCreacion, int usuarioMod, String fechaCreacion, String fechaMod, int estado){
        this.idBodega = new SimpleIntegerProperty(idBodega);
        this.nombreBodega = new SimpleStringProperty(nombreBodega);
        this.calle = new SimpleStringProperty(calle);
        this.colonia = new SimpleStringProperty(colonia);
        this.zona = new SimpleStringProperty(zona);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.municipio = new SimpleIntegerProperty(municipio);
        this.departamento = new SimpleIntegerProperty(departamento);
        this.codigoPostal = new SimpleStringProperty(codigoPostal);
        this.telefono = new SimpleStringProperty(telefono);
        this.usuarioCreacion = new SimpleIntegerProperty(usuarioCreacion);
        this.usuarioMod = new SimpleIntegerProperty(usuarioMod);
        this.fechaCreacion = new SimpleStringProperty(fechaCreacion);
        this.fechaMod = new SimpleStringProperty(fechaMod);
        this.estado = new SimpleIntegerProperty(estado);
    }

    public Bodega(int idBodega, String nombre, String calle, String colonia, String zona, String ciudad, int municipio, int departamento, String codigoPostal, String telefono, int usuarioMod, String fechaMod) {
        this.idBodega = new SimpleIntegerProperty(0);
        this.nombreBodega = new SimpleStringProperty("");
        this.calle = new SimpleStringProperty("");
        this.colonia = new SimpleStringProperty("");
        this.zona = new SimpleStringProperty("");
        this.ciudad = new SimpleStringProperty("");
        this.municipio = new SimpleIntegerProperty(0);
        this.departamento = new SimpleIntegerProperty(0);
        this.codigoPostal = new SimpleStringProperty("");
        this.telefono = new SimpleStringProperty("");
        this.usuarioCreacion = new SimpleIntegerProperty(0);
        this.usuarioMod = new SimpleIntegerProperty(0);
        this.fechaCreacion = new SimpleStringProperty("");
        this.fechaMod = new SimpleStringProperty("");
        this.estado = new SimpleIntegerProperty(0);
    }

    public int getIdBodega() {
        return idBodega.get();
    }

    public SimpleIntegerProperty idBodegaProperty() {
        return idBodega;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega.set(idBodega);
    }

    public String getNombreBodega() {
        return nombreBodega.get();
    }

    public SimpleStringProperty nombreBodegaProperty() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega.set(nombreBodega);
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

    private final SimpleIntegerProperty idBodega;
    private final SimpleStringProperty nombreBodega;
    private final SimpleStringProperty calle;
    private final SimpleStringProperty colonia;
    private final SimpleStringProperty zona;
    private final SimpleStringProperty ciudad;
    private final SimpleIntegerProperty municipio;
    private final SimpleIntegerProperty departamento;
    private final SimpleStringProperty codigoPostal;
    private final SimpleStringProperty telefono;
    private final SimpleIntegerProperty usuarioCreacion;
    private final SimpleIntegerProperty usuarioMod;
    private final SimpleStringProperty fechaCreacion;
    private final SimpleStringProperty fechaMod;
    private final SimpleIntegerProperty estado;

}
