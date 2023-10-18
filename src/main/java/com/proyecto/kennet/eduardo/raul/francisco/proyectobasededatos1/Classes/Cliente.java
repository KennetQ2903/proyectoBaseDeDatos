package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

import java.util.Date;

import lombok.Data;

@Data
public class Cliente {
    private int id_cliente;
    private String primer_nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String otros_apellidos;
    private String calle;
    private String colonia;
    private String zona;
    private String ciudad;
    private int municipio;
    private int departamento;
    private String codigo_postal;
    private String telefono;
    private String email;
    private String nit;
    private String dpi;
    private int usuario_creacion;
    private int usuario_mod;
    private Date fecha_creacion;
    private Date fecha_mod;
    private int estado;

    public Cliente(int id_cliente, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido, String otros_apellidos, String calle, String colonia, String zona, String ciudad, int municipio, int departamento, String codigo_postal, String telefono, String email, String nit, String dpi, int usuario_creacion, int usuario_mod, Date fecha_creacion, Date fecha_mod, int estado) {
        this.id_cliente = id_cliente;
        this.primer_nombre = primer_nombre;
        this.segundo_nombre = segundo_nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.otros_apellidos = otros_apellidos;
        this.calle = calle;
        this.colonia = colonia;
        this.zona = zona;
        this.ciudad = ciudad;
        this.municipio = municipio;
        this.departamento = departamento;
        this.codigo_postal = codigo_postal;
        this.telefono = telefono;
        this.email = email;
        this.nit = nit;
        this.dpi = dpi;
        this.usuario_creacion = usuario_creacion;
        this.usuario_mod = usuario_mod;
        this.fecha_creacion = fecha_creacion;
        this.fecha_mod = fecha_mod;
        this.estado = estado;
    }
}
