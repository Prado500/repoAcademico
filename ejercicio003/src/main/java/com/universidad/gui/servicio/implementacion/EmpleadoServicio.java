/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.servicio.implementacion;

import com.universidad.gui.modelo.Empleado;
import com.universidad.gui.servicio.IEmpleadoServicio;
import com.universidad.gui.servicio.IObservador;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 *
 * Servicio para gestionar objetos de la familia Empleado
 *
 * @param <T> el tipo de empleado que debe extender de Empleado
 *
 */
public class EmpleadoServicio<T extends Empleado> implements IEmpleadoServicio<T> {

    private List<T> elementos;

    private List<IObservador> observadores;

    public EmpleadoServicio() {
        this.elementos = new ArrayList<>();
        this.observadores = new ArrayList<>();

    }

    /**
     *
     * @param elemento
     */
    @Override
    public void agregar(T elemento) {
        elementos.add(elemento);
        this.notificarObservadores();
    }

//    public List<T> mostrar(List<T> elementos) {
//        return new ArrayList<>(elementos);
//    }
    @Override
    public List<T> mostrar() {
        List<T> elementosMostrar = new ArrayList<>();
        for (T elemento : this.elementos) {
            if (!(elemento.getEstatus().equals("IN"))) {
                elementosMostrar.add(elemento);
            }
        }
        return elementosMostrar;
    }

    @Override
    public T searchElementoByNoDocumento(String noDocumento) {
        T elementoRetorno = null;
        System.out.println(elementos);
        for (T elemento : elementos) {
            if (elemento.getNoDoumento().equals(noDocumento) && elemento.getEstatus().equals("AC")) {
                elementoRetorno = elemento;
            }
        }

        if (elementoRetorno == null || elementoRetorno.getEstatus().equals("IN")) {
            throw new IllegalArgumentException("No se encontró ningún registro con No.documento " + noDocumento + "\nAsegúrese de ingresar un número de documento válido y existente.");
        }
        return elementoRetorno;
    }

    //actualizar 
    @Override
    public void actualizarElemento(String noDocumento, String tipoDocumento, String nombre, Double salario) {
        T elementoEncontrado = null;
        for (T elemento : elementos) {
            if (elemento.getNoDoumento().equals(noDocumento)) {
                elementoEncontrado = elemento;
                elemento.setNoDoumento(noDocumento);
                elemento.setTipoDocumento(tipoDocumento);
                elemento.setNombre(nombre);
                elemento.setSalarioBase(salario);
            }
        }

        if (elementoEncontrado == null) {
            throw new IllegalArgumentException("No fue posible actualizar al empleado con noDocumento " + noDocumento + ". Asegúrese que el noDocumento existe y que los datos ingresados son correctos. ");
        }

    }

    @Override
    public void eliminarLogicamenteElementoPorId(String id) {
        T elementoEncontrado = null;
        for (T elemento : elementos) {
            if (elemento.getNoDoumento().equals(id)) {
                elementoEncontrado = elemento;
                elemento.setEstatus("IN");
            }
        }

        if (elementoEncontrado == null) {
            throw new IllegalArgumentException("No fue posible eliminar al empleado con noDocumento " + id + ". Asegúrese que el noDocumento existe y que los datos ingresados son correctos. ");
        }

    }

    @Override
    public double calcularNominaConBonificacion(List<T> elementos) {
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');
        formato.setDecimalFormatSymbols(simbolos);
        double nominaAcumulada = 0;
        for (T elemento : elementos) {
            nominaAcumulada += elemento.getBonificacion();
        }

        return nominaAcumulada;

    }

    @Override
    public double calcularNomina(List<T> elementos) {
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');
        formato.setDecimalFormatSymbols(simbolos);
        double nominaAcumulada = 0;
        for (T elemento : elementos) {
            nominaAcumulada += elemento.getSalarioBase();
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
