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

    //Métodos heredados y específicos para ESerGen únicamente (DETALLE Relacion maestro/detalle)

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
    public void agregarESerGen(String tipoDocumento, String noDocumento, String nombre, double salarioBase, String estatus, boolean certAlturas) {

        ESerGen serGenerales = new ESerGen(noDocumento , tipoDocumento, nombre, salarioBase, estatus, certAlturas);
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
     * despues de aplicada la bonificación.
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
     *
     * @param serGeneralesList Copia de la lista del servicio de empleados de sericio genereal ESserGenServicio.
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

    /**
     * Método que permite crear una comanda y dejarla asociada a un empleado de servicios generales,
     * usado por la GUICrearyAsignarComanda.
     *
     * @param idESerGen es la cédula del empleado de servicios; es su atributo noDocumento. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param descripcion es la descripción a grandes razgos de la comanda de almuerzos del restaurante doña doris. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param principio describe si el principio será frijoles, arbeja, lentejas o verdura. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param proteina describe si la carne será de res, de cerdo o de pollo. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param sopa describe el tipo de sopa. No admite caracteres extraños. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param fechaComanda es la fecha de caducidad de la comanda.
     */
    public void crearYAsignarComanda(String idESerGen, String descripcion, String principio, String proteina, String sopa, String fechaComanda) {

        try {

            Comanda comanda = new Comanda(descripcion, principio, proteina, sopa, fechaComanda);
            ESerGen serGenerales = this.buscarESerGenPorNoDocumento(idESerGen);

            serGenerales.agregarComanda(comanda);
            comandaServicio.agregarComanda(comanda);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Método para crear una comanda sin asignarla a ningún empleado de servicios generales.
     * La comanda se crea y se envía a la lista del servicio de las comandas ComandaServicio.
     * @param descripcion es la descripción a grandes razgos de la comanda de almuerzos del restaurante doña doris. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param principio describe si el principio será frijoles, arbeja, lentejas o verdura. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param proteina describe si la carne será de res, de cerdo o de pollo. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param sopa describe el tipo de sopa. No admite caracteres extraños. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param fechaCaducidad es la fecha de caducidad de la comanda.
     */

    public void crearComandaIndependiente(String descripcion, String principio, String proteina, String sopa, String fechaCaducidad) {

        Comanda comanda = new Comanda(descripcion, principio, proteina, sopa, fechaCaducidad);
        comandaServicio.agregarComanda(comanda);

    }

    /**
     * Método para que una comanda sea eliminada lógicamente. El atributo estatus de la comanda pasa a valer "IN".
     * Al usar este metodo sobre una comanda, esta no aparecera al buscarse o al listar todas las existentes.
     * Este metodo tambien hace que el atributo de tipo ESerGen de la comanda pase a valer null.
     * @param idESerGen String, es un número de documento de un empleado de servicios generales.
     * @param idComanda int, es el id unívoco e incremental de una comanda.
     */
    public void eliminaryDesasociarComanda(String idESerGen, int idComanda) {

        try {
            ESerGen serGenerales = this.buscarESerGenPorNoDocumento(idESerGen);
            Comanda comanda = comandaServicio.buscarComandaID(idComanda);
            serGenerales.removerComanda(comanda);
            comandaServicio.eliminarComandaLogId(idComanda);

        } catch (Exception e) {
            System.out.println("No se pudo eliminar la comanda: " + e);
        }
    }

    /**
     * Método para eliminar lógicamente una comanda no asociada. Su estatus pasa a ser "IN".
     * @param idComanda int, el id de una comanda.
     */
    public void eliminarComanda(int idComanda) {

        this.comandaServicio.eliminarComandaLogId(idComanda);
    }

    /**
     * Método que muestra todas las comandas asociadas a un empleado.
     * @param idESerGen String, el número de documento de un empleado.
     * @return una copia de la lista que tiene como atributo el empleado de servicios generales con todas las comandas asociadas con el.
     */
    public List<Comanda> mostrarComandaPorESerGen(String idESerGen) {

        ESerGen serGenerales = this.buscarESerGenPorNoDocumento(idESerGen);
        return new ArrayList<>(serGenerales.getComandas());
    }

    /**
     * Método para buscar una comanda por su ID.
     * @param id int, el id de una comanda.
     * @return un objeto del tipo Comanda, cuyo id es igual al id ingresado como parametro.
     */
    public Comanda buscarComandaPorId(int id) {

        Comanda comanda = this.comandaServicio.buscarComandaID(id);
        return comanda;
    }

    /**
     * Método para actualizar una comanda, buscándola por su ID.
     * @param descripcion es la descripción a grandes razgos de la comanda de almuerzos del restaurante doña doris. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param principio describe si el principio será frijoles, arbeja, lentejas o verdura. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param proteina describe si la carne será de res, de cerdo o de pollo. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param sopa describe el tipo de sopa. No admite caracteres extraños. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param fechaCaducidad es la fecha de caducidad de la comanda.
     */
    public void actualizarComanda(int idComanda, String descripcion, String principio, String proteina, String sopa, String fechaCaducidad) {

        Comanda comanda = this.comandaServicio.buscarComandaID(idComanda);
        comanda.setDescripcion(descripcion);
        comanda.setPrincipio(principio);
        comanda.setProteina(proteina);
        comanda.setSopa(sopa);
        comanda.setFechaCaducidad(fechaCaducidad);
    }

    /**
     * Método para listar todas las comandas existentes en la lista del servicio de las comandas cuyo estatus sea "AC".
     * @return una copia de la lista del servicio de las comandas.
     */
    public List<Comanda> mostrarComandas() {

        return new ArrayList<>(this.comandaServicio.mostrarComandas());
    }

    /**
     * Método para asociar una comanda existente a un empleado de servicios generales
     * @param ESerGenId String, es el número de documento de un empleado de servicios generales.
     * @param comandaId int, es el id de una comanda.
     */
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

    /**
     * Método que agruega un GUI que implementa la interfaz IObservador a la lista de observadores de este servicio.
     * @param observador es un GUI que implementa la interfaz IObservador.
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
