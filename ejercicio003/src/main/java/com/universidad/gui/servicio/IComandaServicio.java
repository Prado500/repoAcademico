/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.universidad.gui.servicio;

import com.universidad.gui.modelo.implementacion.Comanda;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public interface IComandaServicio {
    public void agregarComanda(Comanda comanda);
    public void eliminarComandaLogId(int id);
    public Comanda buscarComandaID(int id);
    public List<Comanda> mostrarComandas();
    public void actualizarComanda(Comanda comanda, String descripcion, String principio, String proteina, String fechaCaducidad);
    public int getComandaId();
}
