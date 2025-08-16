/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package universidad.aplicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import universidad.modelo.Empleado;
import universidad.modelo.Profesor;
import universidad.modelo.Directivo;
import universidad.modelo.EmpleadoE;
/**
 *
 * @author Alejandro
 */
public class Ejercicio001 {

    private static List<Empleado> empleados = new ArrayList<>();
    
    public static void main(String[] args) {
     
        try{
        double salarioAnual;
    
        Empleado emp = new Empleado ("111", "CC", "David", 300, "M", LocalDate.now(), "AC");
        System.out.println(emp);
        salarioAnual = emp.salarioAnual();
        System.out.println("Salario Anual: " + salarioAnual);
        empleados.add(emp);
        
        Profesor prof = new Profesor("222", "CC", "carlos", 150, "M", LocalDate.now(), "AC", 6, "Profesional");
        System.out.println(prof);
        salarioAnual = prof.salarioAnual();
        System.out.println("Salario Anual: " + salarioAnual);
        empleados.add(prof);
        
        Empleado emp2 = new Profesor("333", "CC", "Manuel", 200, "M", LocalDate.now(), "AC", 3, "Profesional" );
        System.out.println(emp2);
         salarioAnual = emp2.salarioAnual();
        System.out.println("Salario Anual: " + salarioAnual);
        empleados.add(emp2);
        
        Empleado emp3 = new Directivo("444", "CC", "Malestaino", 1500, "M", LocalDate.now(), "AC", 1, 3 );
        System.out.println(emp3);
         salarioAnual = emp3.salarioAnual();
        System.out.println("Salario Anual: " + salarioAnual);
        empleados.add(emp3);
        }catch(IllegalArgumentException e){
            System.out.println("Error de digitacion del usuario: " + e);
        }
        
       
        
        
        
       mostrarEmpleados();
       mostrarEmpleadosForEach();
       calcularNominaEmpleados();
        
       Empleado empe = new EmpleadoE("111", "CC", "Muriel", 300, "M", LocalDate.now(), "AC", 10);
       
        System.out.println(empe);
    }

 //Implementar metodos para recorrer todos los empleados y calcular su nomina anual
        
        // Mi solucion:
        
        public static void mostrarEmpleados(){
        System.out.println("\nRecorrido de la lista de empleados");
        for(Empleado empleado: empleados)
        {
            System.out.println(empleado);
        }
        }
        
        // Solucion con programacion funcional
        public static void mostrarEmpleadosForEach()
        {
            System.out.println("\nImpresion de empleados usando lambdas y programacion funcional: ");
            empleados.forEach(empleado -> System.out.println(empleado));
            
        }

        
       //Calcular la nomina de los empleados:
        
        public static void calcularNominaEmpleados()
        {
            double salario = 0.0; //notese que Java exige que al inicializar una variable en un metodo esta tenga un valor definido para evitar comportamientos inesperados
            System.out.println("\nCalculo de la nomina anual");
            for(Empleado empleado: empleados){
                salario += empleado.salarioAnual();
            }
            
            System.out.println("La nomina anual de la empresa es de: " + salario);
        }
       
}
