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

    public List<T> mostrar(List<T> elementos) {
        return new ArrayList<>(elementos);
    }

    public T searchElementoByNoDocumento(String noDocumento) {
        T elementoRetorno = null;
        for (T elemento : elementos) {
            if (elemento.getNoDoumento().equals(noDocumento)) {
                elementoRetorno = elemento;
            }

        }
        return elementoRetorno;
    }
}
