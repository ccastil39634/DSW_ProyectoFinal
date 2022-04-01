/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import BaseDatos.ConexionBD;
import Objetos.Caso;
import Objetos.MesaAyuda;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author user
 */
public class InterfazGrafica extends JFrame{
    
    private MesaAyuda mesaAyuda;
    
    private PanelCasosAbiertos panelCasos;
    
    private PanelDatos panelDatos;
    
    public InterfazGrafica() throws SQLException{
        mesaAyuda = new MesaAyuda();
        cargarCasos();
        setTitle("Mesa de ayuda EAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        setSize(new Dimension(900, 500));
        setResizable(false);
        
        PanelEncabezado panelEncabezado = new PanelEncabezado();
        add(panelEncabezado, BorderLayout.NORTH);
        
        panelCasos = new PanelCasosAbiertos(this);
        add(panelCasos, BorderLayout.WEST);
        
        panelDatos = new PanelDatos();
        add(panelDatos, BorderLayout.CENTER);
        
        actualizarLista();

    }
    
    
    private void cargarCasos () throws SQLException {
        Connection conexion = ConexionBD.getConnection();
        
        if (conexion != null){
            System.out.println("Conectado a la BD");
        }
        
        Statement st = conexion.createStatement();
        String queryConsulta = "SELECT * FROM casosabiertos.casos WHERE EstadoAtendido = 0;";
        
        ResultSet rs = st.executeQuery(queryConsulta);
        
        while(rs.next()){
            int id = rs.getInt("id");
            String titulo = rs.getString("Titulo");
            String Solicitante = rs.getString("NombreSolicitante");
            String fechaSolicitud = rs.getString("FechaSolicitud");
            String detalle = rs.getString("Detalle");
            int estadoAtendido = 0;
            mesaAyuda.agregarCaso(id, titulo, Solicitante, fechaSolicitud, detalle, estadoAtendido);
        }
        
    }
    
    private void actualizarLista() {
        panelCasos.actualizarLista(mesaAyuda.darCasos());
    }

    public void verDatos(Caso caso) {
        panelDatos.mostrarDatos(caso);
    }
    
    public static void main(String[] args) throws SQLException {
        
        InterfazGrafica interfaz = new InterfazGrafica();
        interfaz.setVisible(true);
        
        //conexion.close();
        
    }
}
