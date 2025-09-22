/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.modelo.implementacion;

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
public class Comanda {

    private int id;
    private ESerGen eserGen; // Referencia al ESerGen dueño (para asociación bidireccional)
    private String descripcion;
    private String principio;
    private String proteina;
    private String sopa;
    private String fechaCaducidad;
    private String estatus;
    public static final Pattern PATRON_CARACTERES_PERMITIDOS_DESCRIPCION = Pattern.compile("\"^[A-Za-zÁÉÍÓÚáéíóúÜüÑñ\\\\s]{1,204}$\""); // Expresión regular que genera un patrón de búsqueda para verificar si la entrada de los campos que se escribiran manualmente (descripcion, principio, proteina y sopa) esta conformada unica y exclusivamente por combinaciones de letras y vocales del alfabeto del español latino.
    public static final Pattern PATRON_CARACTERES_PERMITIDOS = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÜüÑñ\\\\s]{1,50}$"); // Expresión regular que genera un patrón de búsqueda para verificar si la entrada de los campos que se escribiran manualmente (descripcion, principio, proteina y sopa) esta conformada unica y exclusivamente por combinaciones de letras y vocales del alfabeto del español latino.

    // Constructor
    public Comanda(String descripcion, String principio, String proteina,
            String sopa, String fechaCaducidad) {
        this.id = incrementarId();
        verificarInformacionDescripcion(descripcion);
        this.descripcion = descripcion;
        verificarInformacion(principio);
        this.principio = principio;
        verificarInformacion(proteina);
        this.proteina = proteina;
        verificarInformacion(sopa);
        this.sopa = sopa;
        verificarFechaCaducidad(fechaCaducidad);
        this.fechaCaducidad = fechaCaducidad;
        this.estatus = darEstatus();
        
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public ESerGen getEserGen() {
        return eserGen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrincipio() {
        return principio;
    }

    public String getProteina() {
        return proteina;
    }

    public String getSopa() {
        return sopa;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }
    
    public String getEstatus(){
        return estatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEserGen(ESerGen eserGen) {
        this.eserGen = eserGen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrincipio(String principio) {
        this.principio = principio;
    }

    public void setProteina(String proteina) {
        this.proteina = proteina;
    }

    public void setSopa(String sopa) {
        this.sopa = sopa;
    }
    
    public void setEstatus(String estatus){
        this.estatus = estatus;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String toString() {
        return "Comanda{" + "id=" + id + ", eserGen=" + eserGen + ", descripcion=" + descripcion + ", principio=" + principio + ", proteina=" + proteina + ", sopa=" + sopa + ", fechaCaducidad=" + fechaCaducidad + '}';
    }

    private int incrementarId() {
        int id = this.getId();
        return id += 1;
    }

    private void verificarInformacion(String input) {

        if (descripcion.isBlank() || !PATRON_CARACTERES_PERMITIDOS.matcher(input).matches()) {
            throw new IllegalArgumentException("""
                                           Descripción inválida. Ingrese en la descripción palabras
                                           y oraciones compuestas únicamente por combinaciones
                                           entre las letras y vocales del alfabeto español lationamericano.
                                           """);
        }
    }

    private void verificarInformacionDescripcion(String input) {

        if (descripcion.isBlank() || !PATRON_CARACTERES_PERMITIDOS_DESCRIPCION.matcher(input).matches()) {
            throw new IllegalArgumentException("""
                                           Descripción inválida. Ingrese en la descripción palabras
                                           y oraciones compuestas únicamente por combinaciones
                                           entre las letras y vocales del alfabeto español lationamericano.
                                           """);
        }
    }

    private void verificarFechaCaducidad(String fechaCaducidad) {

        Objects.requireNonNull(fechaCaducidad, "La fecha de caducidad de la comanda no puede ser nula.");

        if (!fechaCaducidad.matches("\\d{2}/\\d{2}/\\d{4}/")) {

            throw new IllegalArgumentException("""
                                                Formato de fecha inválido. Ingrese una fecha de acuerdo al formato
                                                DD/MM/AAAA
                                                """);

        }
        
        try{
        
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
            
            LocalDate fecha = LocalDate.parse(this.fechaCaducidad, formato);
            
            LocalDate minFechaCaducidad = LocalDate.now();
            
            LocalDate maxFechaCaducidad = LocalDate.now().plusDays(15);
            
            if (fecha.isBefore(minFechaCaducidad) || fecha.isAfter(maxFechaCaducidad)){
                throw new IllegalArgumentException("""
                                                   Error de digitación de la fecha de caducidad. Recuerde que la fecha de
                                                   caducidad no puede ser mas antigua a la del día de hoy y no puede extenderse
                                                   mas de 15 días.
                                                   """);
            }
        
        
        }catch(DateTimeParseException e){
            throw new IllegalArgumentException("""
                                               Error de digitación de la fecha. Ingrese una fecha de acuerdo al formato
                                               DD/MM/AAAA
                                               """ + e.getMessage());
        
        }

    }
    
    private String darEstatus(){
     return "AC";
    }

    
}
