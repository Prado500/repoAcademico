/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio002.modelo;

/**
 *
 * @author Alejandro
 */
public class TipoA {
    
    private String nombre;
    static String cargo;
    
    public TipoA (String nombre, String cargo){
    this.nombre = nombre;
    this.cargo = cargo;
    }
    
    public TipoA()
    {
    
    }
    public String getNombre() {
        return nombre;
    }

    public static String getCargo() {
        return cargo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static void setCargo(String cargo) {
        TipoA.cargo = cargo;
    }

    @Override
    public String toString() {
        return "TipoA{" + "nombre=" + nombre + ", cargo=" + cargo +'}';
    }
    
    
    
    
    
}
