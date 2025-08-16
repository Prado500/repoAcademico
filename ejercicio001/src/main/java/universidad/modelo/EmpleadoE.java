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
public class EmpleadoE extends Empleado {
    
    private double bonus;

    public EmpleadoE(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String genero, LocalDate fechaNacimiento, String estado, double bonus) {
        super(noDocumento, tipoDocumento, nombre, salarioBase, genero, fechaNacimiento, estado);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    
//    @Override
//    public void setSalarioBase(double salarioBase)
//    {
//        super.setSalarioBase(salarioBase + bonus);
//    } 

    @Override
    public String toString() {
        return "EmpleadoE{" + "Nombre=" + super.getNombre() + ", bonus=" + bonus + ", salarioBase=" + super.getSalarioBase() + '}';
    }
    
    
}
