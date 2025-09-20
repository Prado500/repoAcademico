/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.modelo;

import com.universidad.gui.modelo.implementacion.Administrativo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author Alejandro
 * @param <T>
 */
public abstract class Empleado <T extends Empleado<T>> implements IEmpleado<T> {

    private String noDoumento;
    private String tipoDocumento;
    private String nombre;
    private double salarioBase;
    private String fechaNacimiento;
    private String estatus;
    private double bonificacion;
    public static final Pattern PATRON_VERIFICACION = Pattern.compile("^\\d{6,12}$"); // Expresión regular que genera un patrón de búsqueda para verificar si la entrada del número de identificación es idóneo o nó (solo se permiten números del 1 al 9) 
    public static final Pattern NOMBRE_CARACTERES_PERMITIDOS = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÜüÑñ\\s]{1,50}$"); // Expresión regular que genera un patrón de búsqueda para verificar si la entrada del nombre es idónea
    public static final Pattern IDENTIFICACIONES_PERMITIDAS = Pattern.compile("^(CC|CE|PA)$");// Expresión regular que genera un patron de búsqueda para verificar que la entrada del tipo de documento sea idónea (solo se aceptan "CC" "CE" o "PA")
    public static final Pattern ESTATUS_PERMITIDOS = Pattern.compile("^(AC|IN)$");// Expresión regular que genera un patron de búsqueda para verificar que la entrada del estatus sea idónea (solo se aceptan "AC" o "IA")

    public Empleado(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus) {

        validarNoDocumento(noDocumento);
        this.noDoumento = noDocumento;

        validarTipoDocumento(tipoDocumento);
        this.tipoDocumento = tipoDocumento;
        validarNombre(nombre);

        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo ");

        validarSalarioBase(salarioBase);
        this.salarioBase = Objects.requireNonNull(salarioBase, "El salario base no puede ser nulo ");
        
        validarEstatus(estatus);
        this.estatus = estatus;
        
        this.bonificacion = aplicarBonificacion(salarioBase);
        
    }

    public Empleado(String noDoumento, String tipoDocumento, String nombre, double salarioBase, String fechaNacimiento, String estatus) {

        this(noDoumento, tipoDocumento, nombre, salarioBase, estatus);

        
        validarFechaNacimiento(fechaNacimiento);
        this.fechaNacimiento = fechaNacimiento;
         
    }

    @Override
    public void setNoDoumento(String noDoumento) {
        validarNoDocumento(noDoumento);
        this.noDoumento = noDoumento;
    }

    @Override
    public void setTipoDocumento(String tipoDocumento) {
        validarTipoDocumento(tipoDocumento);
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre;
    }

    @Override
    public void setSalarioBase(double salarioBase) {
        validarSalarioBase(salarioBase);
        this.salarioBase = Objects.requireNonNull(salarioBase, "El salario base no puede ser nulo ");
    }

    @Override
    public void setFechaNacimiento(String fechaNacimiento) {
        validarFechaNacimiento(fechaNacimiento);
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public void setEstatus(String estatus) {
        validarEstatus(estatus);
        this.estatus = estatus;
    }

    @Override
    public String getNoDoumento() {
        return noDoumento;
    }

    @Override
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getSalarioBase() {
        return salarioBase;
    }

    @Override
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String getEstatus() {
        return estatus;
    }
    
    @Override
    public double getBonificacion(){
        return bonificacion;
    }

    public static Pattern getPATRON_VERIFICACION() {
        return PATRON_VERIFICACION;
    }

    public static Pattern getNOMBRE_CARACTERES_PERMITIDOS() {
        return NOMBRE_CARACTERES_PERMITIDOS;
    }

    public static Pattern getIDENTIFICACIONES_PERMITIDAS() {
        return IDENTIFICACIONES_PERMITIDAS;
    }

    public static Pattern getESTATUS_PERMITIDOS() {
        return ESTATUS_PERMITIDOS;
    }

   // delego la implementacion a las subclases.
    
    private void validarNoDocumento(String noDocumento) {

        if (noDocumento.isBlank() || (!PATRON_VERIFICACION.matcher(noDocumento).matches())) {
            throw new IllegalArgumentException("""
                                               Recuerde que el campo del número de documento no puede
                                               estar en blanco y solo puede contener cifras del 0 al 9.
                                               
                                               Tambien recuerde que los números de identificación en Colombia
                                               solo cuentan con entre 6 y 10 dígitos.""");
        }
    }

    private void validarTipoDocumento(String tipoDocumento) {
        if (tipoDocumento.isBlank() || (!IDENTIFICACIONES_PERMITIDAS.matcher(tipoDocumento).matches())) {
            throw new IllegalArgumentException("""
                                               Solo puede ingresar "CC" para cédula de ciudadanía
                                               "CE" para cédula de extranjería o "PA" para
                                                pasaporte.""");
        }
    }

    private void validarSalarioBase(double salarioBase) {

        if (salarioBase <= 0.0 || salarioBase > 21000000) {
            throw new IllegalArgumentException("""
                                               Ingrese un valor para el salario que sea positivo
                                               y mayor que 0. Recuerde que el salario base no
                                               puede exceder los COP $20.000.000.
                                               """
            );
        }
    }

    private void validarNombre(String nombre) {
        if (nombre.isBlank() || (!NOMBRE_CARACTERES_PERMITIDOS.matcher(nombre).matches())) {
            throw new IllegalArgumentException("""
                                               Recuerde que el campo del nombre no puede estar
                                               en blanco y que los nombres solo pueden incluir
                                               combinaciones de palabras hechas con las vocales
                                               y consonantes del alfabeto.
                                                 """);
        }
    }

    private LocalDate validarFechaNacimiento(String fechaNacimiento) {
        Objects.requireNonNull(fechaNacimiento, "La fecha de nacimiento no puede ir vacia");

        if (!fechaNacimiento.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException("""
                                               Formato de fecha inválido. Ingrese una fecha de acuerdo al
                                               formato DD/MM/AAAA.
                                              """);
        }
        try {

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

            LocalDate fecha = LocalDate.parse(fechaNacimiento, formato);

            LocalDate minFechaNacimiento = LocalDate.now().minusYears(100);

            LocalDate maxFechaNacimiento = LocalDate.now().minusYears(18);

            if (fecha.isBefore(minFechaNacimiento) || fecha.isAfter(maxFechaNacimiento)) {

                throw new IllegalArgumentException("""
                                                   La fecha de nacimiento no puede ser la de una persona
                                                   mayor de 100 años ni menor de 18 años.
                                                  """);
            }

            return fecha;

        } catch (DateTimeParseException e) {

            throw new IllegalArgumentException("Error de digitación de la fecha: " + e.getMessage());
        }

    }

    private void validarEstatus(String estatus) {
        if (estatus.isBlank() || (!ESTATUS_PERMITIDOS.matcher(estatus).matches())) {
            throw new IllegalArgumentException("""
                                               Recuerde que el campo del estatus no puede estar
                                               en blanco y que el estatus solo puede ser "AC" o 
                                               "IN".
                                                 """);
        }
    }

//    @Override
//    public String toString() {
//        return "Empleado{" + "noDoumento=" + noDoumento + ", tipoDocumento=" + tipoDocumento + ", nombre=" + nombre + ", salarioBase=" + salarioBase + ", fechaNacimiento=" + fechaNacimiento + ", estatus=" + estatus + '}';
//    }
    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", salarioBase=" + salarioBase + ", estatus=" + estatus + '}';
    }

}
