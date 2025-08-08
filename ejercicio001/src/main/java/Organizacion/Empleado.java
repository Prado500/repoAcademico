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
public class Empleado {
    private String noDocumento;
    private String tipoDocumento;
    private double salarioBase;
    private String genero;
    private LocalDate fechaNacimiento;
    private String estado;

    public Empleado(String noDocumento, String tipoDocumento, double salarioBase, String genero, LocalDate fechaNacimiento, String estado) {
        this.noDocumento = noDocumento;
        this.tipoDocumento = tipoDocumento;
        this.salarioBase = salarioBase;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public String getNoDocumento() {
        return noDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public String getGenero() {
        return genero;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setNoDocumento(String noDocumento) {
        this.noDocumento = noDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Empleado{" + "noDocumento=" + noDocumento + ", tipoDocumento=" + tipoDocumento + ", salarioBase=" + salarioBase + ", genero=" + genero + ", fechaNacimiento=" + fechaNacimiento + ", estado=" + estado + '}';
    }
    
    
    
    
    
    
}
