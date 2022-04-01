/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.sql.*;

/**
 *
 * @author user
 */
public class Caso {
    
    public static final String ANALISTA1 = "Carlos Castillo";
    
    private int id;
    private String titulo;
    private String solicitante;
    private String fechaSolicitud;
    private String detalle;
    private int estadoAtendido;
    private String comentarioAtencion;
    private String atendidoPor;
    private String fechaAtencion;
    
    public Caso(int idC, String tituloC, String solicitanteC, String fechaSolicitudC, String detalleC, int estadoAtendidoC) {
        id = idC;
        titulo = tituloC;
        solicitante = solicitanteC;
        fechaSolicitud = fechaSolicitudC;
        detalle = detalleC;
        estadoAtendido = estadoAtendidoC;
    }
    
    public int darId() {
        return id;
    }
    
    public String darTitulo(){
        return titulo;
    }
    
    public String darSolicitante(){
        return solicitante;
    }
    
    public String darDetalle(){
        return detalle;
    }
    
    public String darFechaSolicitud(){
        return fechaSolicitud;
    }
        
    public int darEstado(){
        return estadoAtendido;
    }
    
    public String toString(){
        return id + " - " + titulo;
    }
}
