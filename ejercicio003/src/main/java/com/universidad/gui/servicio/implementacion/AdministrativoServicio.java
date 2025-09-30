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
 * @author Alejandro
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
     * @param administrativo agregamos administrativos a la lista del servicio
     */
    @Override
    public void agregar(Administrativo administrativo) {
        administrativos.add(administrativo);
        //this.notificarObservadores();
    }

    /**
     *
     * @return Devolvemos una copia de la lista del servicio de administrativos
     * donde se agregan a ella unica y exclusivamente los administrativos con
     * estatus = "AC"
     *
     */
    @Override
    public List<Administrativo> mostrar() {
        List<Administrativo> elementosMostrar = new ArrayList<>();
        for (Administrativo administrativo : this.administrativos) {
            if (!(administrativo.getEstatus().equals("IN"))) {
                elementosMostrar.add(administrativo);
            }
        }
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
    public Administrativo searchAdministrativoByNoDocumento(String noDocumento) {
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

    /**
     *
     * Método para actualizar un administrativo usando sus setters
     *
     * @param noDocumento
     * @param tipoDocumento
     * @param nombre
     * @param salario
     */
    @Override
    public void actualizarAdministrativo(String noDocumento, String tipoDocumento, String nombre, Double salario) {

        Administrativo elementoEncontrado = null;

        for (Administrativo administrativo : this.administrativos) {
            if (administrativo.getNoDoumento().equals(noDocumento)) {
                elementoEncontrado = administrativo;
                administrativo.setNoDoumento(noDocumento);
                administrativo.setTipoDocumento(tipoDocumento);
                administrativo.setNombre(nombre);
                administrativo.setSalarioBase(salario);
            }
        }

        if (elementoEncontrado == null) {
            throw new IllegalArgumentException("No fue posible actualizar al empleado con noDocumento " + noDocumento + ". Asegúrese que el No.documento existe y que los datos ingresados son correctos. ");
        }

    }

    /**
     *
     * Método que elimina logicamente a un administrativo mediante AC = "IN"
     * @param id
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

}
