/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.servicio.implementacion;

import com.universidad.gui.modelo.implementacion.Comanda;
import com.universidad.gui.servicio.IComandaServicio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class ComandaServicio implements IComandaServicio{
     private List<Comanda> comandas = new ArrayList<>();
     
     
    @Override
    public void agregarComanda(Comanda comanda) {
        this.comandas.add(comanda);
    }

    @Override
    public void eliminarComandaId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Comanda buscarComandaID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Comanda> verComandas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
