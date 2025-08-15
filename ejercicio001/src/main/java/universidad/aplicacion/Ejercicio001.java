/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package universidad.aplicacion;

import java.time.LocalDate;
import universidad.modelo.Empleado;
import universidad.modelo.Profesor;

/**
 *
 * @author Alejandro
 */
public class Ejercicio001 {

    public static void main(String[] args) {
        
        Empleado emp = new Empleado ("111", "CC", "David", 100, "M", LocalDate.now(), "AC");
        System.out.println(emp);
        
        Profesor prof = new Profesor("222", "CC", "carlos", 150, "M", LocalDate.now(), "AC", 6, "Profesional");
        System.out.println(prof);
        
        
        
        
    }
}
