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
public class Directivo extends Empleado {
    private int numProgDirige;
    private int nivelMando; 

    public Directivo(int numProgDirige, int nivelMando, String noDocumento, String tipoDocumento, String nombre, double salarioBase, String genero, LocalDate fechaNacimiento, String estado) {
        super(noDocumento, tipoDocumento, nombre, salarioBase, genero, fechaNacimiento, estado);
        this.numProgDirige = numProgDirige;
        this.nivelMando = nivelMando;
    }

    public int getNumProgDirige() {
        return numProgDirige;
    }

    public int getNivelMando() {
        return nivelMando;
    }

    public void setNumProgDirige(int numProgDirige) {
        this.numProgDirige = numProgDirige;
    }

    public void setNivelMando(int nivelMando) {
        this.nivelMando = nivelMando;
    }

    @Override
    public String toString() {
        return "Directivo{" + "numProgDirige=" + numProgDirige + ", nivelMando=" + nivelMando + '}';
    }
    
    
}
