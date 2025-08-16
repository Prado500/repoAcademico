/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidad.modelo;

import java.time.LocalDate;

/**
 *
 * @author Alejandro
 */
public class Profesor extends Empleado {
    
    private double intHoraria;
    private String maxNivelEducativo;

    public Profesor(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String genero, LocalDate fechaNacimiento, String estado, double intHoraria, String maxNivelEducativo) {
        super(noDocumento, tipoDocumento, nombre, salarioBase, genero, fechaNacimiento, estado);
    
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
    public double salarioAnual(){
    double salario = 1.1 * (getSalarioBase() * 12);
    return salario;
    
    }
    
    
        @Override
    public String toString() {
        return "Profesor{" + "noDocumento=" + super.getNoDocumento() + ", tipoDocumento=" + super.getTipoDocumento() + ", nombre=" + super.getNombre() + ", salarioBase=" + super.getSalarioBase() + ", genero=" + super.getGenero() + ", intHoraria=" + intHoraria + ", maxNivelEducativo=" + maxNivelEducativo + '}';
    }
    
    
}
