/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import javax.swing.*;

/**
 *
 * @author user
 */
public class PanelBotones extends JPanel{
    
    JTextArea jTextArea;
    public PanelBotones(){
        
        jTextArea = new JTextArea("Texto de prueba", 40, 100);
        add(jTextArea);
    }
    
}
