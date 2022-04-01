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
import javax.swing.event.*;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author user
 */
public class PanelCasosAbiertos extends JPanel implements ListSelectionListener {
    
    private InterfazGrafica principal;
    private JList listadoCasos;
    
    public PanelCasosAbiertos(InterfazGrafica interfaz) {
        principal = interfaz;
        
        setLayout(new BorderLayout());
        setBorder(new TitledBorder("Casos pendientes por atender"));
        setPreferredSize(new Dimension(300, 200));
        
        listadoCasos = new JList();
        listadoCasos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listadoCasos.addListSelectionListener(this);
        
        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new LineBorder(Color.BLACK, 1)));
        scroll.getViewport().add(listadoCasos);

        add(scroll, BorderLayout.CENTER);
    }


    public void actualizarLista(ArrayList<Caso> listaActualizada) {
        listadoCasos.setListData(listaActualizada.toArray());
        listadoCasos.setSelectedIndex(0);
    }
    
    public void seleccionar(int seleccionado) {
        listadoCasos.setSelectedIndex(seleccionado);
        listadoCasos.ensureIndexIsVisible(seleccionado);
    }
    
    public boolean haySeleccionado() {
        return !listadoCasos.isSelectionEmpty();
    }
    
    public String darCasoSeleccionado() {
        String titulo = null;

        if (listadoCasos.getSelectedValue() != null) {
            Caso casoSeleccionado = (Caso) listadoCasos.getSelectedValue();
            titulo = casoSeleccionado.darTitulo();
        }
        return titulo;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent evento) {
        if (listadoCasos.getSelectedValue() != null){
            Caso casoSeleccionado = (Caso) listadoCasos.getSelectedValue();
            principal.verDatos(casoSeleccionado);
        }
    }
    
}
