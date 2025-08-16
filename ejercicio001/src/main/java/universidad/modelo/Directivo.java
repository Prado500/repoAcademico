/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidad.modelo;

import java.time.LocalDate;

/**
 * BUG REPORTADO: Al querer cambiar el orden de los atributos del constructor en la firma del metodo, me daba un errorIllegal Type of expression solo porque corte y pegue al final los atributos unicos de la clase Profesor 
 * @author Alejandro
 */
public class Directivo extends Empleado {
    private int numProgDirige;
    private int nivelMando; 

    public Directivo(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String genero, LocalDate fechaNacimiento, String estado, int numProgDirige, int nivelMando) {
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
    public double salarioAnual(){
    double salario = 1.5 * (getSalarioBase() * 12);
    return salario;
    }
         @Override
    public String toString() {
        return "Directivo{" + "noDocumento=" + super.getNoDocumento() + ", tipoDocumento=" + super.getTipoDocumento() + ", nombre=" + super.getNombre() + ", salarioBase=" + super.getSalarioBase() + ", genero=" + super.getGenero() + ", numProgDirige=" + numProgDirige + ", nivelMando=" + nivelMando + '}';
    }
    
}
