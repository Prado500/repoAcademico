/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.servicio.implementacion;

import com.universidad.gui.modelo.implementacion.Comanda;
import com.universidad.gui.modelo.implementacion.ESerGen;
import com.universidad.gui.servicio.IComandaServicio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class ESerGenServicio extends EmpleadoServicio<ESerGen> {

    private IComandaServicio comandaServicio;

    public ESerGenServicio(IComandaServicio comandaServicio) {

        this.comandaServicio = comandaServicio;
    }

    public void asignarComanda(String idESerGen, Comanda comanda) {
        ESerGen serGenerales = this.searchElementoByNoDocumento(idESerGen);
        try {
            if (serGenerales != null) {
                serGenerales.agregarComanda(comanda);
                comandaServicio.agregarComanda(comanda);
            } else {
                throw new IllegalArgumentException("Error al asignar comanda. No se enconttró ningún empleado con id" + idESerGen);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void crearComandaIndependiente(Comanda comanda){
        comandaServicio.agregarComanda(comanda);
    }
    
    public void eliminaryDesasociarComanda(String idESerGen, int idComanda) {
        ESerGen serGenerales = this.searchElementoByNoDocumento(idESerGen);
        Comanda comanda = comandaServicio.buscarComandaID(idComanda);

        try {
            if (serGenerales != null && comanda != null) {
                serGenerales.removerComanda(comanda);
                comandaServicio.eliminarComandaLogId(idComanda);
            } else {
                if (serGenerales == null) {
                    throw new IllegalArgumentException("Error al eliminar comanda. No se enconttró ningún empleado con id" + idESerGen);
                } else if (comanda == null) {
                    throw new IllegalArgumentException("Error al eliminar comanda. No se enconttró ningún empleado con id" + idESerGen);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public List<Comanda> mostrarComandaPorESerGen(String idESerGen) {

        ESerGen serGenerales = this.searchElementoByNoDocumento(idESerGen);

        if (serGenerales == null) {
            throw new IllegalArgumentException("Error al mostrar las comandas. No se enconttró ningún empleado con id" + idESerGen);
        }
        return new ArrayList<>(serGenerales.getComandas());
    }

    public void actualizarComanda(String idESerGen, int idComanda, String descripcion, String principio, String proteina, String sopa, String fechaCaducidad) {
        try {

            Comanda comanda = this.comandaServicio.buscarComandaID(idComanda);
            if (comanda == null) {
                throw new IllegalArgumentException("No existe registro de la comanda con id " + idComanda + ". Asegúrese que la comanda con id " + idComanda + "\nExista para el empleado con id: " + idESerGen);
            }
            comanda.setDescripcion(descripcion);
            comanda.setPrincipio(principio);
            comanda.setProteina(proteina);
            comanda.setSopa(sopa);
            comanda.setFechaCaducidad(fechaCaducidad);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    
    public List<Comanda> mostrarComandas(){
        return new ArrayList<>(this.comandaServicio.mostrarComandas());
    }
}
