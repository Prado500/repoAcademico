/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.servicio.implementacion;

import com.universidad.gui.modelo.implementacion.Administrativo;
import com.universidad.gui.servicio.IAdministrativoServicio;
import com.universidad.gui.servicio.IObservador;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Alejandro De Los Reyes Ostos
 */
public class AdministrativoServicio implements IAdministrativoServicio {

    private List<Administrativo> administrativos;

    private List<IObservador> observadores;

    /**
     *
     * Intanciamos las listas de los administrativos y de los observadores
     *
     */
    public AdministrativoServicio() {
        this.administrativos = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    /**
     *
     * @param noDocumento
     * @param tipoDocumento
     * @param nombre
     * @param salario
     * @param estatus
     * @param escalafon
     */
    @Override
    public void agregarAdministrativo( String noDocumento, String tipoDocumento, String nombre, double salario, String estatus, String escalafon) {

        Administrativo administrativo = new Administrativo(noDocumento, tipoDocumento, nombre, salario, estatus, escalafon);
        this.administrativos.add(administrativo);
        this.notificarObservadores();
    }

    /**
     *
     * @return Devolvemos una copia de la lista del servicio de administrativos
     * donde se agregan a ella unica y exclusivamente los administrativos con
     * estatus = "AC"
     *
     */
    @Override
    public List<Administrativo> mostrarAdministrativo() {
        
        List<Administrativo> elementosMostrar = new ArrayList<>();

        for (Administrativo administrativo : this.administrativos) {
            if (!(administrativo.getEstatus().equals("IN"))) {
                elementosMostrar.add(administrativo);
            }
        }

        if (elementosMostrar.isEmpty())
            throw new IllegalArgumentException("La lista está vacía. No hay administrativos registrados todavía");
        return elementosMostrar;
    }

    /**
     *
     * @param noDocumento
     * @return Un objeto de tipo Administrativo alojado en la lista del servicio
     * de los administrativos con estatus = "AC"
     *
     */
    @Override
    public Administrativo buscarAdministrativoPorNoDocumento(String noDocumento) {
        
        Administrativo elementoRetorno = null;
        for (Administrativo administrativo : this.administrativos) {
            if (administrativo.getNoDoumento().equals(noDocumento) && administrativo.getEstatus().equals("AC")) {
                elementoRetorno = administrativo;
            }
        }

        if (elementoRetorno == null || elementoRetorno.getEstatus().equals("IN")) {
            throw new IllegalArgumentException("No se encontró ningún registro de un administrativo con No.documento " + noDocumento + "\nAsegúrese de ingresar un número de documento válido y existente.");
        }
        return elementoRetorno;
    }

    public List<String> obtenerNombreSalarioEstatus(String noDocumento) {

        Administrativo administrativo = this.buscarAdministrativoPorNoDocumento(noDocumento);

        List<String> nombreSalarioEstatus = new ArrayList<>();
        nombreSalarioEstatus.add(administrativo.getNombre());
        nombreSalarioEstatus.add(Double.toString(administrativo.getSalarioBase()));
        nombreSalarioEstatus.add(administrativo.getNombre());

        return nombreSalarioEstatus;
    }

    /**
     *
     * Método para actualizar un administrativo usando sus setters
     *
     * @param nNoDocumento
     * @param noDocumento
     * @param tipoDocumento
     * @param nombre
     * @param salario
     * @param escalafon
     */
    @Override
    public void actualizarAdministrativo(String nNoDocumento, String noDocumento, String tipoDocumento, String nombre, Double salario, String escalafon) {

        Administrativo elementoEncontrado = null;

        for (Administrativo administrativo : this.administrativos) {
            if (administrativo.getNoDoumento().equals(noDocumento)) {
                elementoEncontrado = administrativo;
                administrativo.setNoDoumento(nNoDocumento);
                administrativo.setTipoDocumento(tipoDocumento);
                administrativo.setNombre(nombre);
                administrativo.setSalarioBase(salario);
                administrativo.setEscalafon(escalafon);
            }
        }

        if (elementoEncontrado == null) {
            throw new IllegalArgumentException("No fue posible actualizar al empleado con No.documento " + noDocumento + ". Asegúrese que el No.documento existe y que los datos ingresados son correctos. ");
        }

    }

    /**
     *
     * Método que elimina logicamente a un administrativo mediante AC = "IN"
     * @param id
     * 
     */
    @Override
    public void eliminarLogicamenteAdministrativoPorId(String id) {
        
        Administrativo elementoEncontrado = null;
        for (Administrativo administrativo : this.administrativos) {
            if (administrativo.getNoDoumento().equals(id)) {
                elementoEncontrado = administrativo;
                administrativo.setEstatus("IN");
            }
        }

        if (elementoEncontrado == null) {
            throw new IllegalArgumentException("No fue posible eliminar al administrativo con No.documento " + id + ". Asegúrese que el noDocumento existe y que los datos ingresados son correctos. ");
        }
    }
    
    /**
     * 
     * Método para calcular la nómina con bonificación de los empleados administrativos a partir de su atributo bonificación
     * Se usa polimorfismo para calcular la bonificacion de cada empleado desde las clases estrucutrales al sobreescribir el método calcularBonificación.
     * @param administrativos
     * @return el valor total de la nómina de los administrativos después de aplicada la bonificación
     * 
     */
    @Override
    public double calcularNominaConBonificacionAdministrativo(List<Administrativo> administrativos) {

        if (this.administrativos.isEmpty()) {
            throw new IllegalArgumentException("No fue posible calcular la nómina con bonificación para los empleados administrativos porque\nno se ha registrado ningúno");
        }

        DecimalFormat formato = new DecimalFormat("#,##0.00");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');
        formato.setDecimalFormatSymbols(simbolos);
        double nominaAcumulada = 0;

        for (Administrativo administrativo : administrativos) {
            nominaAcumulada += administrativo.getBonificacion();
        }
        return nominaAcumulada;
    }
    
    /**
     * 
     * Método para calcular la nómina de los administrativos a partir de su atributo salarioBase
     * @param administrativos
     * @return el valor total de la nómina de los administrativos sin bonificación
     * 
     */
    @Override
    public double calcularNominaAdministrativo(List<Administrativo> administrativos) {

        if (this.administrativos.isEmpty()) {
            throw new IllegalArgumentException("No fue posible calcular la nómina cruda (sin bonificación) para los empleados administrativos porque\nno se ha registrado ningúno");
        }

        DecimalFormat formato = new DecimalFormat("#,##0.00");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');
        formato.setDecimalFormatSymbols(simbolos);
        double nominaAcumulada = 0;
        for (Administrativo administrativo : administrativos) {
            nominaAcumulada += administrativo.getSalarioBase();
        }
        return nominaAcumulada;  
    }
    
    //Metodos del servicio de notificacion
    
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
