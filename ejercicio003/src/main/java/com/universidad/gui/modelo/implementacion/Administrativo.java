/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.modelo.implementacion;

import com.universidad.gui.modelo.Empleado;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 *
 * @author Alejandro
 */
public class Administrativo extends Empleado {

    private String escalafon;

    public Administrativo(String escalafon, String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus) {
        super(noDocumento, tipoDocumento, nombre, salarioBase, estatus);
        verificarEscalafon(escalafon);
        this.escalafon = escalafon;
    }

    public String getEscalafon() {
        return escalafon;
    }

    public void setEscalafon(String escalafon) {
        this.escalafon = escalafon;
    }

    @Override
    public double calcularBonificacion() {
        double valor = this.getSalarioBase();
        double valorRetorno;
        if (this.escalafon == "1") {
            valorRetorno = valor * 1.1;
        }
        if (this.escalafon == "2") {
            valorRetorno = valor * 1.3;
        } else {
            valorRetorno = valor * 1.5;
        }
        this.setSalarioBase(valorRetorno);
        return valorRetorno;
    }
    
    private void verificarEscalafon(String escalafon){
       Pattern busqueda = Pattern.compile("^(1|2|3)$");
       Matcher matcher = busqueda.matcher(escalafon); 
       if (!matcher.matches()){
       throw new IllegalArgumentException("""
                                               Ha ingresado un valor inválido para el escalafón.
                                          
                                               Recuerde que el escalafón solo puede ser "1", "2"
                                               o "3". """);
       }
    }
}
