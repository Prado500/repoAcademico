/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.universidad.gui.modelo;

import static com.universidad.gui.modelo.Empleado.ESTATUS_PERMITIDOS;
import static com.universidad.gui.modelo.Empleado.IDENTIFICACIONES_PERMITIDAS;
import static com.universidad.gui.modelo.Empleado.NOMBRE_CARACTERES_PERMITIDOS;
import static com.universidad.gui.modelo.Empleado.PATRON_VERIFICACION;
import com.universidad.gui.modelo.implementacion.Administrativo;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author Alejandro
 * @param <T>
 */
public interface IEmpleado<T extends Empleado<T>> {

    public void setNoDoumento(String noDoumento);

    public void setTipoDocumento(String tipoDocumento);

    public void setNombre(String nombre);

    public void setSalarioBase(double salarioBase);

    public void setFechaNacimiento(String fechaNacimiento);

    public void setEstatus(String estatus);
   
    public String getNoDoumento();

    public String getTipoDocumento();

    public String getNombre();

    public double getSalarioBase();

    public String getFechaNacimiento();

    public String getEstatus();
    
    public double aplicarBonificacion(Double salarioBase);
    
    public double getBonificacion();
    
    public double calcularNominaConBonificacion(ArrayList<T> elementos);
    
    public double calcularNomina(ArrayList<T> elementos);
    
    public T obtenerCopia();
}
