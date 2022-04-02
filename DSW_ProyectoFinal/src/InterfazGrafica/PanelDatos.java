/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import Objetos.Caso;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

/**
 *
 * @author user
 */
public class PanelDatos extends JPanel{
    
    private JLabel nTitulo;
    private JLabel nConsecutivo;
    private JLabel nSolicitante;
    private JLabel nFecha;
    private JLabel nDetalle;
    private JLabel etiquetaTitulo;
    private JLabel etiquetaConsecutivo;
    private JLabel etiquetaSolicitante;
    private JLabel etiquetaFecha;
    private JLabel etiquetaDetalle;
    
    public PanelDatos(){
        setLayout(new GridBagLayout());
        setBorder(new TitledBorder("Datos del caso"));
        //setPreferredSize(new Dimension(500, 150));        
        GridBagConstraints gbc = new GridBagConstraints();
        
        etiquetaConsecutivo = new JLabel("Consecutivo: ");
        gbc.gridx = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        etiquetaConsecutivo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        add(etiquetaConsecutivo, gbc);

        nConsecutivo = new JLabel();
        nConsecutivo.setPreferredSize(new Dimension(140, 20));
        gbc.gridx = 2;
        add(nConsecutivo, gbc);

        etiquetaTitulo = new JLabel("Título: ");
        gbc.gridx = 1;
        gbc.gridy = 1;
        etiquetaTitulo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        add(etiquetaTitulo, gbc);

        nTitulo = new JLabel();
        nTitulo.setPreferredSize(new Dimension(140, 20));
        gbc.gridx = 2;
        add(nTitulo, gbc);

        etiquetaSolicitante = new JLabel("Solicitante: ");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(etiquetaSolicitante, gbc);

        nSolicitante = new JLabel();
        nSolicitante.setPreferredSize(new Dimension(140, 20));
        gbc.gridx = 2;
        add(nSolicitante, gbc);

        etiquetaFecha = new JLabel("Fecha Solicitud: ");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(etiquetaFecha, gbc);

        nFecha = new JLabel();
        nFecha.setPreferredSize(new Dimension(140, 20));
        gbc.gridx = 2;
        add(nFecha, gbc);

        etiquetaDetalle = new JLabel("Detalle: ");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(etiquetaDetalle, gbc);

        nDetalle = new JLabel();
        gbc.gridx = 2;
        add(nDetalle, gbc);
    }

    public void mostrarDatos(Caso caso) {
        nConsecutivo.setText(String.valueOf(caso.darId()));
        nTitulo.setText(caso.darTitulo());
        nSolicitante.setText(caso.darSolicitante());
        nFecha.setText(caso.darFechaSolicitud());
        nDetalle.setText(caso.darDetalle());
    }
    
    public void limpiarDatos() {
        nConsecutivo.setText("");
        nTitulo.setText("");
        nSolicitante.setText("");
        nFecha.setText("");
        nDetalle.setText("");
    }
    
}
