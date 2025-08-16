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
public class  Empleado {
    private String noDocumento;
    private String tipoDocumento;
    private String nombre;
    private double salarioBase;
    private String genero;
    private LocalDate fechaNacimiento;
    private String estado;

    public Empleado(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String genero, LocalDate fechaNacimiento, String estado) {
        this.noDocumento = noDocumento;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        if (salarioBase <= 0){
        throw new IllegalArgumentException("Ingrese un valor positivo y mayor que 0 cuando digite la cifra del salario.");
        }
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

      public String getNombre() {
        return nombre;
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

    public void setNombre(String nombre) {
        this.tipoDocumento = nombre;
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

    public double salarioAnual() {
    double salario = salarioBase * 12;
    return salario;
    }
    
    @Override
    public String toString() {
        return "Empleado{" + "noDocumento=" + noDocumento + ", tipoDocumento=" + tipoDocumento + ", nombre=" + nombre + ", salarioBase=" + salarioBase + ", genero=" + genero + ", fechaNacimiento=" + fechaNacimiento + ", estado=" + estado + '}';
    }
    
    
    
    
    
    
}
