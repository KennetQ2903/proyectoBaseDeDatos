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
