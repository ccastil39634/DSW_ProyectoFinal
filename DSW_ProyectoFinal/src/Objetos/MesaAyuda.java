/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;
/**
 *
 * @author user
 */
public class MesaAyuda {
    
    private ArrayList<Caso> casos;
    
    public MesaAyuda() {
        casos = new ArrayList<>();
    }
    
    public ArrayList<Caso> darCasos() {
        ArrayList<Caso> copia = new ArrayList<>(casos);
        return copia;
    }
    
    public boolean agregarCaso (int idC, String tituloC, String solicitanteC, String fechaSolicitudC, String detalleC, int estadoAtendidoC){
        int casoBuscado = buscarCaso(idC);
        boolean agregado = false;
        if (casoBuscado == -1){
            Caso nuevoCaso = new Caso(idC, tituloC, solicitanteC, fechaSolicitudC, detalleC, estadoAtendidoC);
            casos.add(nuevoCaso);     
            agregado = true;
        }

        return agregado;
        
    }
    
    public int buscarCaso(int id) {
        int posicion = -1;
        int n = casos.size();

        for (int i = 0; i < n; ++i) {
            if (casos.get(i).darId() == id){
                posicion = i;
            }
        }
        return posicion;
    }
    
    public int contarCasos() {
        int conteo = casos.size();
        
        return conteo;        
    }
    
    
}
