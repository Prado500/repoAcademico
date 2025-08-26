/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.servicio;

import com.universidad.gui.modelo.Empleado;
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
public class EmpleadoServicio<T extends Empleado> {

    private List<T> elementos;

    public EmpleadoServicio() {
        this.elementos = new ArrayList<>();

    }

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

//    public List<T> mostrar(List<T> elementos) {
//        return new ArrayList<>(elementos);
//    }
    public List<T> mostrar() {
        List<T> elementosMostrar = new ArrayList<>();
        for (T elemento : this.elementos) {
            elementosMostrar.add(elemento);
        }
        return elementosMostrar;
    }

    public T searchElementoByNoDocumento(String noDocumento) {
        T elementoRetorno = null;
        System.out.println(elementos);
        for (T elemento : elementos) {
            if (elemento.getNoDoumento().equals(noDocumento)) {
                elementoRetorno = elemento;
            }
        }

        if (elementoRetorno == null) {
            throw new IllegalArgumentException("No se encontró ningún registro con noDocumento " + noDocumento);
        }
        return elementoRetorno;
    }

    //actualizar 
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
}
