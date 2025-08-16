/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ejercicio002.aplicacion;

import ejercicio002.modelo.TipoA;

/**
 * Este es el ejercicio 002.
 * @author Alejandro
 */
public class Ejercicio002 {

    public static void main(String[] args) {
        
        TipoA uno = new TipoA();
        uno.setNombre("Jenkins");
        TipoA.setCargo("Obrero");
        
        TipoA dos = new TipoA();
        dos.setNombre("Mauricio");
        TipoA.setCargo("Ingeniero");
        
        System.out.println("Uno: " + uno);
        System.out.println("Dos: " + dos);
        
        
        
    }
}
