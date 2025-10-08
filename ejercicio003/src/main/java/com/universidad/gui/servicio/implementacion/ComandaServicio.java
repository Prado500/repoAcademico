/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.servicio.implementacion;

import com.universidad.gui.modelo.implementacion.Comanda;
import com.universidad.gui.servicio.IComandaServicio;
import com.universidad.gui.servicio.IObservador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class ComandaServicio implements IComandaServicio {

    private List<Comanda> comandas;
    private List<IObservador> observadores;

    public ComandaServicio() {
        this.comandas = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    @Override
    public void crearYAgregarComanda(String descripcion, String principio, String proteina, String sopa, String fechaCaducidad) {
        Comanda comanda = new Comanda(descripcion, principio, proteina, sopa, fechaCaducidad);
        int acumulador = 0; 
        this.comandas.add(comanda);
        acumulador = comandas.size();
        comanda.setId(acumulador);
        this.notificarObservadores();
    }

    @Override
    public void eliminarComandaLogId(int id) {
        
        Comanda placeholder = null;
        for (Comanda comanda : comandas) {
            if (comanda.getId() == id) {
                comanda.setEstatus("IN");
                placeholder = comanda;
            }
        }
        if (placeholder == null) {

            throw new IllegalArgumentException("No fue posible eliminar la comanda con id " + id + ". " + "Asegúrese que la comanda existe y que el id proporcionado es el correcto.");
        }
    }

    @Override
    public Comanda buscarComandaID(int id) {
        Comanda retorno = null;
        for (Comanda comanda : comandas) {
            if (comanda.getId() == id && comanda.getEstatus().equals("AC")) {
                retorno = comanda;
            }
        }
        if (retorno == null) {
            throw new IllegalArgumentException("No existe registro de la comanda con id " + id + ". " + "Asegúrese que la comanda existe y que el id proporcionado es el correcto.");
        }
        return retorno;
    }

    @Override
    public List<Comanda> mostrarComandas() {
        List<Comanda> retorno = new ArrayList<>(); 
        for (Comanda comanda : comandas)
            if (comanda.getEstatus().equals("AC"))
                retorno.add(comanda);
        if (retorno.isEmpty())
            throw new IllegalArgumentException("Aún no se ha registrado ninguna comanda");
        return retorno;
    }

    @Override
    public void actualizarComanda(int idComanda, String descripcion, String principio, String proteina, String sopa, String fechaCaducidad) {
        
        try{
            Comanda comanda = this.buscarComandaID(idComanda);
            comanda.setDescripcion(descripcion);
            comanda.setPrincipio(principio);
            comanda.setProteina(proteina);
            comanda.setSopa(sopa);
            comanda.setFechaCaducidad(fechaCaducidad);    
        }catch(Exception e){
            throw new IllegalArgumentException("""
                                               Error actualizando la comanda: 
                                               """ + e.getMessage());
        }
    }

    /**
     * Método para retornar el id de la última comanda creada. se usa para pasarle a la GUI de creación de comandas el id de comanda que se muestra en el mensaje de creación exitosa.
     * @return int, el id de la última comanda creada.
     */
    @Override
    public int getComandaId(){
        return this.comandas.getLast().getId();
    }

    //Métodos para el mecanismo de suscripción de observadores

    /**
     * Método que agrega un observador a la lista de observadores del servicio de las comandas.
     * @param observador un objeto de la familia IObservador (un GUI).
     */
    public void agregarObservador(IObservador observador) {
        this.observadores.add(observador);
    }

    /**
     * Método que elimina un observador a la lista de observadores del servicio de las comandas.
     * @param observador un objeto de la familia IObservador (un GUI).
     */
    public void eliminarObservador(IObservador observador) {
        this.observadores.remove(observador);
    }

    /**
     * Método que notifica a los observadores cuando se realiza la adición de una comanda
     */
    public void notificarObservadores() {
        for (IObservador observador : observadores) {
            observador.actualizar();
        }
    }
}
