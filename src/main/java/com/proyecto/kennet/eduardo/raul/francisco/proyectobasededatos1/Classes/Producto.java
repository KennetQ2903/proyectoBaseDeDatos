package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Producto {
    private final SimpleIntegerProperty idProducto;
    private final SimpleIntegerProperty idProveedor;
    private final SimpleStringProperty codigo;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty descripcion;
    private final SimpleIntegerProperty precio;
    private final SimpleIntegerProperty stock;
    private final SimpleIntegerProperty usuarioCreacion;
    private final SimpleIntegerProperty usuarioMod;
    private final SimpleStringProperty fechaCreacion;
    private final SimpleStringProperty fechaMod;
    private final SimpleIntegerProperty estado;

    public Producto(int idProducto, String nombre, String descripcion, double precio, Object o) {
        this.idProducto = new SimpleIntegerProperty();
        this.idProveedor = new SimpleIntegerProperty();
        this.codigo = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.descripcion = new SimpleStringProperty();
        this.precio = new SimpleIntegerProperty();
        this.stock = new SimpleIntegerProperty();
        this.usuarioCreacion = new SimpleIntegerProperty();
        this.usuarioMod = new SimpleIntegerProperty();
        this.fechaCreacion = new SimpleStringProperty();
        this.fechaMod = new SimpleStringProperty();
        this.estado = new SimpleIntegerProperty();
    }

    public Producto(int idProducto, int idProveedor, String codigo, String nombre, String descripcion, int precio, int stock, int usuarioCreacion, int usuarioMod, String fechaCreacion, String fechaMod, int estado) {
        this.idProducto = new SimpleIntegerProperty(idProducto);
        this.idProveedor = new SimpleIntegerProperty(idProveedor);
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleIntegerProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.usuarioCreacion = new SimpleIntegerProperty(usuarioCreacion);
        this.usuarioMod = new SimpleIntegerProperty(usuarioMod);
        this.fechaCreacion = new SimpleStringProperty(fechaCreacion);
        this.fechaMod = new SimpleStringProperty(fechaMod);
        this.estado = new SimpleIntegerProperty(estado);
    }

    // Getters
    public int getIdProducto() {
        return idProducto.get();
    }

    public int getIdProveedor() {
        return idProveedor.get();
    }

    public String getCodigo() {
        return codigo.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public int getPrecio() {
        return precio.get();
    }

    public int getStock() {
        return stock.get();
    }

    public int getUsuarioCreacion() {
        return usuarioCreacion.get();
    }

    public int getUsuarioMod() {
        return usuarioMod.get();
    }

    public String getFechaCreacion() {
        return fechaCreacion.get();
    }

    public String getFechaMod() {
        return fechaMod.get();
    }

    public int getEstado() {
        return estado.get();
    }

    // Setters
    public void setIdProducto(int idProducto) {
        this.idProducto.set(idProducto);
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor.set(idProveedor);
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public void setPrecio(int precio) {
        this.precio.set(precio);
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        this.usuarioCreacion.set(usuarioCreacion);
    }

    public void setUsuarioMod(int usuarioMod) {
        this.usuarioMod.set(usuarioMod);
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion.set(fechaCreacion);
    }

    public void setFechaMod(String fechaMod) {
        this.fechaMod.set(fechaMod);
    }

    public void setEstado(int estado) {
        this.estado.set(estado);
    }

    // Property methods
    public SimpleIntegerProperty idProductoProperty() {
        return idProducto;
    }

    public SimpleIntegerProperty idProveedorProperty() {
        return idProveedor;
    }

    public SimpleStringProperty codigoProperty() {
        return codigo;
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public SimpleStringProperty descripcionProperty() {
        return descripcion;
    }

    public SimpleIntegerProperty precioProperty() {
        return precio;
    }

    public SimpleIntegerProperty stockProperty() {
        return stock;
    }

    public SimpleIntegerProperty usuarioCreacionProperty() {
        return usuarioCreacion;
    }

    public SimpleIntegerProperty usuarioModProperty() {
        return usuarioMod;
    }

    public SimpleStringProperty fechaCreacionProperty() {
        return fechaCreacion;
    }

    public SimpleStringProperty fechaModProperty() {
        return fechaMod;
    }

    public SimpleIntegerProperty estadoProperty() {
        return estado;
    }
}
