/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import BaseDatos.ConexionBD;
import Objetos.Caso;
import Objetos.MesaAyuda;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class InterfazGrafica extends JFrame {
    
    private MesaAyuda mesaAyuda;
    
    private PanelEncabezado panelEncabezado;
    private PanelCasosAbiertos panelCasos;
    private PanelDatos panelDatos;
    private PanelBotones panelBotones;
    

    
    public InterfazGrafica() throws SQLException{
        mesaAyuda = new MesaAyuda();
        cargarCasos();
        setTitle("Mesa de ayuda EAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());
        setSize(new Dimension(750, 350));
        setResizable(false);
        GridBagConstraints gbc = new GridBagConstraints();
        
        panelEncabezado = new PanelEncabezado();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(panelEncabezado, gbc);
        gbc.weighty = 0.0;
        
        panelCasos = new PanelCasosAbiertos(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        add(panelCasos, gbc);
        gbc.weightx = 0.0;
        
        panelDatos = new PanelDatos();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelDatos, gbc);
        
        panelBotones = new PanelBotones(this);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        //gbc.gridwidth = 2;
        gbc.gridheight = 1;
        //gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(panelBotones, gbc);
        
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
        conexion.close();
    }
    
    public void actualizarLista() {
        panelCasos.actualizarLista(mesaAyuda.darCasos());
    }

    public void verDatos(Caso caso) {
        panelDatos.mostrarDatos(caso);
    }
    
    public void buscarCaso() {
        String idBuscar = JOptionPane.showInputDialog(this, "Numero de caso");
        if (idBuscar != null) {
            int posicion = mesaAyuda.buscarCaso(parseInt(idBuscar));

            actualizarLista();
            if (posicion != -1) {
                panelCasos.seleccionar(posicion);
                Caso p = mesaAyuda.darCasos().get(posicion);
                verDatos(p);
            }
            else {
                JOptionPane.showMessageDialog(this, "No se encontró el caso buscado");
            }
        }
    }
    
    
    public void crearCaso() throws SQLException{
        JFrame ventanaCrear = new JFrame();
        ventanaCrear.setTitle("Crear caso");
        JDialog jd = new JDialog(ventanaCrear);
        
        jd.setLayout(new FlowLayout());        
        jd.setBounds(500, 210, 400, 210);
        
        JLabel labelTitulo = new JLabel("Titulo solicitud: ");
        JTextField cTitulo = new JTextField(25);
        JLabel labelSolicitante = new JLabel("Nombre solicitante: ");
        JTextField cSolicitante = new JTextField(23);
        JLabel labelDetalle = new JLabel("Detalle: ");
        JTextArea cDetalle = new JTextArea(5,29);
        jd.add(labelTitulo);
        jd.add(cTitulo);
        jd.add(labelSolicitante);
        jd.add(cSolicitante);
        jd.add(labelDetalle);
        jd.add(cDetalle);
        jd.setVisible(true);       
        
        JButton botonCrearCaso = new JButton ("Crear");
        botonCrearCaso.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String bTitulo = cTitulo.getText();
                String bSolicitante = cSolicitante.getText();
                String bDetalle = cDetalle.getText();
                String fechaActual = LocalDateTime.now().toString();
                String bfecha = fechaActual.substring(0,10);
                if (bSolicitante.isEmpty() || bTitulo.isEmpty() || bDetalle.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Aun hay campos vacíos.");
                }
                else{
                    jd.setVisible(false);
                    try {
                        nuevoCaso(bTitulo, bfecha, bSolicitante, bDetalle);
                    } catch (SQLException ex) {
                        Logger.getLogger(InterfazGrafica.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        });
        jd.add(botonCrearCaso);
    }
    
    private void nuevoCaso(String bdTitulo, String bdFecha, String bdNombre, String bdDetalle) throws SQLException {
        Connection conexion = ConexionBD.getConnection();
        
        if (conexion != null){
            System.out.println("Conectado a la BD");
        }
        
        String insert = "INSERT INTO casosabiertos.casos (Titulo, NombreSolicitante, FechaSolicitud, Detalle, EstadoAtendido) VALUES (?, ?, ?, ?, 0)";
        
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1,bdTitulo);
        ps.setString(2,bdNombre);
        ps.setString(3,bdFecha);
        ps.setString(4,bdDetalle);
        
        if(ps.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Registro creado exitosamente");
        }
        
        panelDatos.limpiarDatos();
        actualizarLista();
        conexion.close();
    }
    
    public void atenderCaso() throws SQLException{
        JFrame ventanaAtender = new JFrame();
        ventanaAtender.setTitle("Atender caso");
        JDialog jd = new JDialog(ventanaAtender);
        
        jd.setLayout(new FlowLayout());        
        jd.setBounds(500, 210, 400, 210);
        
        String elTitulo = panelCasos.darCasoSeleccionado() + "\n";
        int idCaso = panelCasos.darIdSeleccionado();
        
        JLabel labelTitulo = new JLabel("Titulo del caso: ");
        JLabel cTitulo = new JLabel(elTitulo + "                    \n");
        JLabel labelAtiende = new JLabel("Asesor que atiende: ");
        JTextField cAtiende = new JTextField(23);
        JLabel labelComentario = new JLabel("Comentario: ");
        JTextArea cComentario = new JTextArea(5,27);
        jd.add(labelTitulo);
        jd.add(cTitulo);
        jd.add(labelAtiende);
        jd.add(cAtiende);
        jd.add(labelComentario);
        jd.add(cComentario);
        jd.setVisible(true);       
        
        JButton botonAtenderCaso = new JButton ("Atender");
        botonAtenderCaso.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String bAtiende = cAtiende.getText();
                String bComentario = cComentario.getText();
                String fechaActual = LocalDateTime.now().toString();
                String bfecha = fechaActual.substring(0,10);
                if (bAtiende.isEmpty() || bComentario.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Aun hay campos vacíos.");
                }
                else{
                    jd.setVisible(false);
                    try {
                        actualizarCaso(idCaso, bfecha, bAtiende, bComentario);
                    } catch (SQLException ex) {
                        Logger.getLogger(InterfazGrafica.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        });
        jd.add(botonAtenderCaso);
    }
    
    private void actualizarCaso(int bdId, String bdFecha, String bdAtiende, String bdComentario) throws SQLException {
        try (Connection conexion = ConexionBD.getConnection()) {
            if (conexion != null){
                System.out.println("Conectado a la BD");
            }
            
            String update = "UPDATE casosabiertos.casos SET EstadoAtendido = 1, ComentarioAtencion = ?, AtendidoPor = ?, FechaAtencion = ? WHERE id = ?";
            
            PreparedStatement ps = conexion.prepareStatement(update);
            ps.setString(1,bdComentario);
            ps.setString(2,bdAtiende);
            ps.setString(3,bdFecha);
            ps.setInt(4, bdId);
            ps.executeUpdate();
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Su caso ha sido atendido exitosamente");
            }
            
            panelDatos.limpiarDatos();
            actualizarLista();
        }
    }
    
    public void salirApp() {
        JOptionPane.showMessageDialog(this, "Fue un gusto ayudarte", "Mesa de ayuda", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
    
    public static void main(String[] args) throws SQLException {
        
        InterfazGrafica interfaz = new InterfazGrafica();
        interfaz.setVisible(true);
       
    }

}

