/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author user
 */
public class PanelBotones extends JPanel implements ActionListener {
    private final String opcion1 = "BuscarCaso";
    private final String opcion2 = "Atendido";
    private final String opcion3 = "NuevoCaso";
    private final String opcion4 = "salirApp";
    
    private InterfazGrafica principal;
    
    private JButton botonBuscar;
    private JButton botonAtendido;
    private JButton botonNuevo;
    private JButton botonSalir;
    
    public PanelBotones(InterfazGrafica interfaz){
        principal = interfaz;
        inicializar();
    }
    
    private void inicializar(){
        setBorder(new TitledBorder("Opciones"));
        setLayout(new FlowLayout());
        
        botonBuscar = new JButton("Buscar caso");
        botonBuscar.setActionCommand(opcion1);
        botonBuscar.addActionListener(this);
        add(botonBuscar);
        
        botonAtendido = new JButton("Atendido");
        botonAtendido.setActionCommand(opcion2);
        botonAtendido.addActionListener(this);
        add(botonAtendido);
        
        botonNuevo = new JButton("Nuevo caso");
        botonNuevo.setActionCommand(opcion3);
        botonNuevo.addActionListener(this);
        add(botonNuevo);
        
        botonSalir = new JButton("Salir");
        botonSalir.setActionCommand(opcion4);
        botonSalir.addActionListener(this);
        add(botonSalir);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento){
        String comando = evento.getActionCommand();
        if (opcion1.equals(comando)) {
            principal.buscarCaso();
            //JOptionPane.showMessageDialog(this, "Buscar caso");
        }
        else if(opcion2.equals(comando)) {
            try {
                principal.atenderCaso();
                //JOptionPane.showMessageDialog(this, "Atender caso");
            } catch (SQLException ex) {
                Logger.getLogger(PanelBotones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(opcion3.equals(comando)) {
            try {
                principal.crearCaso();
                //JOptionPane.showMessageDialog(this, "Crear caso");
            } catch (SQLException ex) {
                Logger.getLogger(PanelBotones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(opcion4.equals(comando)) {
            principal.salirApp();
        }
        
    }
}
