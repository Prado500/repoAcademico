/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.servicio.implementacion;

import com.universidad.gui.modelo.implementacion.Administrativo;
import com.universidad.gui.modelo.implementacion.Comanda;
import com.universidad.gui.modelo.implementacion.ESerGen;
import com.universidad.gui.servicio.IComandaServicio;
import com.universidad.gui.servicio.IEserGenServicio;
import com.universidad.gui.servicio.IObservador;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class ESerGenServicio implements IEserGenServicio {

    private final IComandaServicio comandaServicio;
    private List<ESerGen> serGenerales;
    private List<IObservador> observadores;

    public ESerGenServicio(IComandaServicio comandaServicio) {

        this.serGenerales = new ArrayList<>();
        this.comandaServicio = comandaServicio;
        this.observadores = new ArrayList<>();
    }

    //Métodos heredados y específicos para ESerGen únicamente (DETALLE Relacion maestro/detalle)
    /**
     *
     * @param serGenerales agregamos un ESerGen administrativos a la lista del
     * servicio
     *
     */
    @Override
    public void agregarESerGen(ESerGen serGenerales) {

        this.serGenerales.add(serGenerales);
        this.notificarObservadores();

    }

    /**
     *
     * @return Devolvemos una copia de la lista del servicio de empleados de
     * servicios generales donde se agregan a ella unica y exclusivamente los
     * empleados con estatus = "AC"
     *
     */
    @Override
    public List<ESerGen> mostrarESerGen() {

        List<ESerGen> elementosMostrar = new ArrayList<>();
        for (ESerGen serGenerales : this.serGenerales) {
            if (!(serGenerales.getEstatus().equals("IN"))) {
                elementosMostrar.add(serGenerales);
            }
        }
        return elementosMostrar;
    }

    /**
     *
     * @param noDocumento
     * @return Un objeto de tipo ESerGen alojado en la lista del servicio de los
     * empleados de ese tipo con estatus = "AC"
     *
     */
    @Override
    public ESerGen buscarESerGenPorNoDocumento(String noDocumento) {

        ESerGen elementoRetorno = null;
        for (ESerGen serGenerales : this.serGenerales) {
            if (serGenerales.getNoDoumento().equals(noDocumento) && serGenerales.getEstatus().equals("AC")) {
                elementoRetorno = serGenerales;
            }
        }

        if (elementoRetorno == null || elementoRetorno.getEstatus().equals("IN")) {
            throw new IllegalArgumentException("No se encontró ningún registro de un empleado de servicios generales con No.documento " + noDocumento + "\nAsegúrese de ingresar un número de documento válido y existente.");
        }

        return elementoRetorno;
    }

    /**
     *
     * Método para actualizar un empleado de servicios generales usando sus
     * setters
     *
     * @param nNoDocumento
     * @param noDocumento
     * @param tipoDocumento
     * @param nombre
     * @param salario
     * @param cerAlturas
     */
    @Override
    public void actualizarESerGen(String nNoDocumento, String noDocumento, String tipoDocumento, String nombre, Double salario, boolean cerAlturas) {

        ESerGen elementoEncontrado = null;

        for (ESerGen serGenerales : this.serGenerales) {
            if (serGenerales.getNoDoumento().equals(noDocumento)) {
                elementoEncontrado = serGenerales;
                serGenerales.setNoDoumento(nNoDocumento);
                serGenerales.setTipoDocumento(tipoDocumento);
                serGenerales.setNombre(nombre);
                serGenerales.setSalarioBase(salario);
                serGenerales.setCerAlturas(cerAlturas);
            }
        }

        if (elementoEncontrado == null) {
            throw new IllegalArgumentException("No fue posible actualizar al empleado de servicios generales con No.documento " + noDocumento + ". Asegúrese que el No.documento existe y que los datos ingresados son correctos. ");
        }
    }

    /**
     *
     * Método que elimina logicamente a un empleado de servicios generales
     * mediante AC = "IN"
     *
     * @param id
     *
     */
    @Override
    public void eliminarLogicamenteESerGenPorId(String id) {

        ESerGen elementoEncontrado = null;
        for (ESerGen serGenerales : this.serGenerales) {
            if (serGenerales.getNoDoumento().equals(id)) {
                elementoEncontrado = serGenerales;
                serGenerales.setEstatus("IN");
            }
        }

        if (elementoEncontrado == null) {
            throw new IllegalArgumentException("No fue posible eliminar al empleado de servicios generales con No.documento " + id + ". Asegúrese que el noDocumento existe y que los datos ingresados son correctos. ");
        }
    }

    /**
     *
     * Método para calcular la nómina con bonificación de los empleados
     * administrativos a partir de su atributo bonificación. Se usa polimorfismo
     * para calcular la bonificacion de cada empleado desde las clases
     * estrucutrales al sobreescribir el método calcularBonificación.
     *
     * @param serGeneralesList
     * @return el valor total de la nómina de los empleados de servicios generales
     * despues de aplicada la bonificación
     *
     */
    @Override
    public double calcularNominaConBonificacionESerGen(List<ESerGen> serGeneralesList) {

        DecimalFormat formato = new DecimalFormat("#,##0.00");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');
        formato.setDecimalFormatSymbols(simbolos);
        double nominaAcumulada = 0;
        for (ESerGen serGenerales : serGeneralesList) {
            nominaAcumulada += serGenerales.getBonificacion();
        }
        return nominaAcumulada;
    }

    /**
     *
     * Método para calcular la nómina de los empleados de servicios generales sin
     * aplicar la bonificación a partir de su atributo salarioBase
     * @param serGeneralesList
     * @return el valor total de la nómina de los empleados de servicios generales antes de
     * aplicada la bonificación
     *
     */
    @Override
    public double calcularNominaESerGen(List<ESerGen> serGeneralesList) {

        DecimalFormat formato = new DecimalFormat("#,##0.00");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');
        formato.setDecimalFormatSymbols(simbolos);
        double nominaAcumulada = 0;
        for (ESerGen serGenerales : serGeneralesList) {
            nominaAcumulada += serGenerales.getSalarioBase();
        }
        return nominaAcumulada;
    }

    //Métodos asociados con Comanda (DETALLE Relación maestro/detalle)

    public void crearYAsignarComanda(String idESerGen, Comanda comanda) {

        try {

            ESerGen serGenerales = this.buscarESerGenPorNoDocumento(idESerGen);

                serGenerales.agregarComanda(comanda);
                comandaServicio.agregarComanda(comanda);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void crearComandaIndependiente(Comanda comanda) {

        comandaServicio.agregarComanda(comanda);

    }

    public void eliminaryDesasociarComanda(String idESerGen, int idComanda) {

        try {
            ESerGen serGenerales = this.buscarESerGenPorNoDocumento(idESerGen);
            Comanda comanda = comandaServicio.buscarComandaID(idComanda);

            if (serGenerales != null && comanda != null) {
                serGenerales.removerComanda(comanda);
                comandaServicio.eliminarComandaLogId(idComanda);
            } else {
                throw new IllegalArgumentException("No se pudo eliminar la comanda. Asegúrese que el No.documento " + idESerGen + " proporcionado pertenece a un\n empleado de servicios generales existente al cual se le haya asignado una comanda con id " + idComanda);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void eliminarComanda(int idComanda) {

        this.comandaServicio.eliminarComandaLogId(idComanda);
    }

    public List<Comanda> mostrarComandaPorESerGen(String idESerGen) {

        ESerGen serGenerales = this.buscarESerGenPorNoDocumento(idESerGen);
        return new ArrayList<>(serGenerales.getComandas());
    }

    public Comanda buscarComandaPorId(int id) {

        Comanda comanda = this.comandaServicio.buscarComandaID(id);
        return comanda;
    }

    public void actualizarComanda(int idComanda, String descripcion, String principio, String proteina, String sopa, String fechaCaducidad) {

        Comanda comanda = this.comandaServicio.buscarComandaID(idComanda);
        comanda.setDescripcion(descripcion);
        comanda.setPrincipio(principio);
        comanda.setProteina(proteina);
        comanda.setSopa(sopa);
        comanda.setFechaCaducidad(fechaCaducidad);
    }

    public List<Comanda> mostrarComandas() {

        return new ArrayList<>(this.comandaServicio.mostrarComandas());
    }

    public void asignarComanda(String ESerGenId, int comandaId) {

        ESerGen serGenerales = this.buscarESerGenPorNoDocumento(ESerGenId);
        Comanda comanda = comandaServicio.buscarComandaID(comandaId);

        if (comanda.getEserGen() != null) {
            throw new IllegalArgumentException("La comanda con ID " + comandaId + " ya fué asignada al empleado " + comanda.getEserGen().getNombre() + " con " + comanda.getEserGen().getTipoDocumento() + " No." + comanda.getEserGen().getNoDoumento());
        }

        for (Comanda comandaLista : this.comandaServicio.mostrarComandas()) {
            if (comanda.getId() == comandaId) {
                comanda.setEserGen(serGenerales);
                serGenerales.agregarComanda(comanda);
            }
        }
    }

    // Métodos para la implementación del patrón observer
    
    public void agregarObservador(IObservador observador) {

        this.observadores.add(observador);
    }

    public void notificarObservadores() {

        for (IObservador observador : observadores) {
            observador.actualizar();
        }
    }

    public void eliminarObservador(IObservador observador) {

        this.observadores.remove(observador);
    }

    public void mostrarObservadores() {

        for (IObservador observador : observadores) {
            System.out.println(observador);
        }

        if (observadores.isEmpty()) {
            System.out.println("La lista de observadores esta vacia!");

        }

    }

}
