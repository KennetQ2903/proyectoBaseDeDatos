package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

import java.util.Date;
import lombok.Data;

@Data
public class Cliente {
    private int id_cliente;
   private String primer_nombre ;
   private String segundo_nombre ;
   private String primer_apellido;
    private String segundo_apellido;
    private String otros_apellidos ;
    private String calle;
    private String colonia;
    private String zona ;
    private String ciudad;
  private int  municipio;
    private int departamento;
    private String codigo_postal ;
    private String telefono ;
    private String email ;
    private String nit;
    private String dpi ;

    private int usuario_creacion;

    private int usuario_mod ;

    private Date fecha_creacion;
    private Date fecha_mod ;
    private int estado ;
}
