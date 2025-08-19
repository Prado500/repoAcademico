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
public class EmpleadoServicio <T extends Empleado> {

    private List<T> elementos = new ArrayList<>();

    public void agregar(T elemento) {
        elementos.add(elemento);
    }
    
    public List<T> mostrar(List<T> elementos){
        return this.elementos; 
    }
}
