/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author Alejandro
 */
public class Empleado {

    private String noDoumento;
    private String tipoDocumento;
    private String nombre;
    private double salarioBase;
    private String fechaNacimiento;
    public static final Pattern PATRON_VERIFICACION = Pattern.compile("^\\d+$");

    public Empleado(String nombre, double salrioBase) {

        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo ");

        if (salarioBase <= 0) {
            throw new IllegalArgumentException("Ingrese un valor para el salario que sea positivo y mayor que 0");
        }

        this.salarioBase = Objects.requireNonNull(salarioBase, "El salario base no puede ser nulo ");

    }

    public Empleado(String noDoumento, String tipoDocumento, String nombre, double salarioBase, String fechaNacimiento) {
        
        this(nombre, salarioBase);
        validarnoDocumento(noDoumento);
        this.noDoumento = noDoumento;
        this.tipoDocumento = tipoDocumento;
        validarFechaNacimiento(fechaNacimiento);
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNoDoumento(String noDoumento) {
        this.noDoumento = noDoumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNoDoumento() {
        return noDoumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public static Pattern getPATRON_VERIFICACION() {
        return PATRON_VERIFICACION;
    }
    
    private void validarnoDocumento(String noDocumento) {

        if (noDocumento.isBlank() || (!PATRON_VERIFICACION.matcher(noDocumento).matches())) {
            throw new IllegalArgumentException("Recuerde que el número de documento no puede estar en blanco y solo puede contener cifras del 0 al 9");
        }
    }

    private LocalDate validarFechaNacimiento(String fechaNacimiento) {
        Objects.requireNonNull(fechaNacimiento, "La fecha de nacimiento no puede ir vacia");

        if (!fechaNacimiento.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException("Formato de fecha inválido.\nIngrese una fecha de acuerdo al formato DD/MM/AAAA. ");
        }

        try {

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy").withResolverStyle(ResolverStyle.STRICT);

            LocalDate fecha = LocalDate.parse(fechaNacimiento, formato);

            LocalDate minFechaNacimiento = LocalDate.now().minusYears(100);
           
            LocalDate maxFechaNacimiento = LocalDate.now().minusYears(18);

            if (fecha.isBefore(minFechaNacimiento) || fecha.isAfter(maxFechaNacimiento)) {

                throw new IllegalArgumentException("La fecha de nacimiento no puede ser la de una persona mayor de 100 años.\ni menor de 18 años");            
            }

            return fecha;
            
        } catch (DateTimeParseException e) {
            
            throw new IllegalArgumentException("Error de digitación de la fecha: " + e.getMessage());
        }
    
        
    
    }

    

    
    
}
