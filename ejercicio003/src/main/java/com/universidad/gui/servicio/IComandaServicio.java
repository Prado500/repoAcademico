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
    public void crearYAgregarComanda(String descripcion, String principio, String proteina, String sopa, String fechaCaducidad);
    public void eliminarComandaLogId(int id);
    public Comanda buscarComandaID(int id);
    public List<Comanda> mostrarComandas();
    public void actualizarComanda(int idComanda, String descripcion, String principio, String proteina, String sopa, String fechaCaducidad);
    public int getComandaId();
}
