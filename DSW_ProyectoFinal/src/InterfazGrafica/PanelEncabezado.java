/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author user
 */
public class PanelEncabezado extends JPanel {
    
    private JLabel imagen;
    
    public PanelEncabezado(){
        FlowLayout layout = new FlowLayout();
        layout.setHgap(0);
        layout.setVgap(0);
        setLayout(layout);
        
        ImageIcon icono = new ImageIcon("files/encabezado.jpg");
        
        imagen = new JLabel("");
        imagen.setIcon(icono);
        add(imagen);
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Color.LIGHT_GRAY));
    }
    
}
