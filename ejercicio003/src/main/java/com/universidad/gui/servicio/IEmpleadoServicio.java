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
 * @param <T>
 */
public interface IEmpleadoServicio<T extends Empleado> {

    public void agregar(T elemento);

    public List<T> mostrar();

    public T searchElementoByNoDocumento(String noDocumento);

    public void actualizarElemento(String noDocumento, String tipoDocumento, String nombre, Double salario);

    public void eliminarLogicamenteElementoPorId(String id);

    public double calcularNominaConBonificacion(ArrayList<T> elementos);

    public double calcularNomina(ArrayList<T> elementos);

    
}
