/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Organizacion;

import java.time.LocalDate;

/**
 *
 * @author Alejandro
 */
public class Profesor extends Empleado {
    
    private double intHoraria;
    private String maxNivelEducativo;

    public Profesor(double intHoraria, String maxNivelEducativo, String noDocumento, String tipoDocumento, double salarioBase, String genero, LocalDate fechaNacimiento, String estado) {
        super(noDocumento, tipoDocumento, salarioBase, genero, fechaNacimiento, estado);
        this.intHoraria = intHoraria;
        this.maxNivelEducativo = maxNivelEducativo;
    }

    public double getIntHoraria() {
        return intHoraria;
    }

    public String getMaxNivelEducativo() {
        return maxNivelEducativo;
    }

    public void setIntHoraria(double intHoraria) {
        this.intHoraria = intHoraria;
    }

    public void setMaxNivelEducativo(String maxNivelEducativo) {
        this.maxNivelEducativo = maxNivelEducativo;
    }

    @Override
    public String toString() {
        return "Profesor{" + "intHoraria=" + intHoraria + ", maxNivelEducativo=" + maxNivelEducativo + '}';
    }
    
    
}
