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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Alejandro De los Reyes Ostos
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

    //Métodos heredados y específicos para ESerGen únicamente (Maestro Relación maestro/detalle)

    /**
     * Método para crear y agregar un objeto del tipo ESerGen (Empleado de servicios generales)
     * a la lista del servicio de los empleados generales.
     * @param tipoDocumento String, puede ser "CC" para cédula de ciudadanía, "CE" para cédula de extranjeria o "PA" para pasaporte.
     * @param noDocumento String, es el número de documento. No admite caracteres no numéricos y su longitud debe estar entre 6 y 10 dígitos.
     * @param nombre String, es el nombre del empleado de servicios generales.
     * @param salarioBase double, es la cifra del salario base. No admite caracteres no númericos. No puede ser negativo y debe ser inferior a 20000000.
     * @param estatus String, es el atributo que define si un empleado está activo "AC" o inactivo "IN". Se utiliza para el borrado lógico.
     * @param certAlturas boolean, define si cuenta o no con certificación de alturas.
     */
    @Override
    public void agregarESerGen(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus, boolean certAlturas) {

        ESerGen serGenerales = new ESerGen(noDocumento, tipoDocumento, nombre, salarioBase, estatus, certAlturas);
        this.serGenerales.add(serGenerales);
        this.notificarObservadores();
    }

    /**
     *
     * @return Devolvemos una copia de la lista del servicio de empleados de
     * servicios generales donde se agregan a ella única y exclusivamente los
     * empleados con estatus = "AC".
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
        if (elementosMostrar.isEmpty())
            throw new IllegalArgumentException("La lista está vacía. No hay administrativos registrados todavía");
        return elementosMostrar;
    }

    /**
     *
     * @param noDocumento es el número del documento del empleado de servicios generales; es el atributo String noDocumento de la clase ESerGen.
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
     * Método para actualizar un empleado de servicios generales (objeto ESerGen) usando sus setters
     *
     * @param nNoDocumento String, nuevo número de documento que se asignará al atributo noDocumento de ESerGen. No admite caracteres extraños como !@#$$%^&&* etc...
     * @param noDocumento String, número de documento, atributo de ESerGen. No admite caracteres que no sean números y no acepta una vlor con menos de 6 digitos o con más de 12 digitos
     * @param tipoDocumento String, puede ser pasaporte ("PA"), cédula de extranjería (CE), o cédula de ciudadanía (CC). No admite caracteres extraños como !@#$$%^&&* etc...
     * @param nombre String, no admite caracteres extraños como !@#$$%^&&* etc...
     * @param salario double, debe ser un número entero e inferior a 20M (20000000).
     * @param cerAlturas boolean, true si la tiene false si no. Se usaa para determinar la bonificación del empleado de servicios generales.
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
     * Método que elimina logicamente a un empleado de servicios generales mediante la modificación
     * del atributo estatus de los objetos ESerGenAC. de estatus = "AC" se pasa a "IN".
     *
     * @param idSerGenerales String, es el valor del número de documento de un empleado de servicios generales; es su atributo noDocumento.
     *
     */
    @Override
    public void eliminarLogicamenteESerGenPorId(String idSerGenerales) {

        ESerGen elementoEncontrado = null;
        for (ESerGen serGenerales : this.serGenerales) {
            if (serGenerales.getNoDoumento().equals(idSerGenerales)) {
                elementoEncontrado = serGenerales;
                serGenerales.setEstatus("IN");
            }
        }

        if (elementoEncontrado == null) {
            throw new IllegalArgumentException("No fue posible eliminar al empleado de servicios generales con No.documento " + idSerGenerales + ". Asegúrese que el noDocumento existe y que los datos ingresados son correctos. ");
        }
    }

    /**
     *
     * Método para calcular la nómina con bonificación de los empleados
     * de servicios generales a partir de su atributo bonificación. Se usa polimorfismo
     * para calcular la bonificacion de cada empleado desde las clases
     * estrucutrales al sobreescribir el método calcularBonificación.
     *
     * @param serGeneralesList Copia de la lista del servicio de empleados de sericio genereal ESserGenServicio.
     * @return El valor total de la nómina de los empleados de servicios generales
     * después de aplicada la bonificación.
     *
     */
    @Override
    public double calcularNominaConBonificacionESerGen(List<ESerGen> serGeneralesList) {

        if (this.serGenerales.isEmpty()) {
            throw new IllegalArgumentException("No fue posible calcular la nómina cruda con bonificación para los empleados de servicios generales porque\nno se ha registrado ningúno");
        }

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
     *
     * @param serGeneralesList Copia de la lista del servicio de empleados de sericio genereal ESserGenServicio.
     * @return el valor total de la nómina de los empleados de servicios generales antes de
     * aplicada la bonificación
     *
     */
    @Override
    public double calcularNominaESerGen(List<ESerGen> serGeneralesList) {

        if (this.serGenerales.isEmpty()) {
            throw new IllegalArgumentException("No fue posible calcular la nómina cruda (sin bonificación) para los empleados de servicios generales porque\nno se ha registrado ningúno");
        }

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


    // Métodos para la implementación del patrón observer

    /**
     * Método que agruega un GUI que implementa la interfaz IObservador a la lista de observadores de este servicio.
     * @param observador es una GUI que implementa la interfaz IObservador.
     */
    public void agregarObservador(IObservador observador) {

        this.observadores.add(observador);
    }

    /**
     * Método que recorre la lista de observadores e invoca para cada uno el metodo heredado de IObservador (actualizar()) dentro del cual, en cada GUI, se hace la sobreescritura para que llame al metodo que carga la tabla, que es privado y existe en el mismo GUI.
     * Este mismo metodo es el que se llama en el metodo agregar de este servicio, para que la notificación ocurra tan pronto se agrega un nuevo ESerGen.
     */
    public void notificarObservadores() {

        for (IObservador observador : observadores) {
            observador.actualizar();
        }
    }

    /**
     * Método para de-registrar (eliminar) un observador (GUI) de la lista de observadores del servicio.
     * @param observador es una interfaz que implementa la interfaz IObservador
     */
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
