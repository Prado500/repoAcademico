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
  
   
    public Administrativo(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus, String escalafon) {
        super(noDocumento, tipoDocumento, nombre, salarioBase, estatus);
        verificarEscalafon(escalafon);
        this.escalafon = escalafon;
        this.setBonificacion(this.aplicarBonificacion(this.getSalarioBase()));
        
    }

    public String getEscalafon() {
        return escalafon;
    }


    public void setEscalafon(String escalafon) {
        verificarEscalafon(escalafon);
        this.escalafon = escalafon;
    }


    private void verificarEscalafon(String escalafon) {
        Pattern busqueda = Pattern.compile("^(1|2|3)$");
        Matcher matcher = busqueda.matcher(escalafon);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("""
                                               Ha ingresado un valor inválido para el escalafón.
                                          
                                               Recuerde que el escalafón solo puede ser "1", "2"
                                               o "3". """);
        }
    }

    @Override
    public double aplicarBonificacion(Double salarioBase) {
        double bonificacion = 0;

        if (this.getEscalafon().equals("1")) {
            bonificacion = salarioBase * 1.1;
        }
        if (this.getEscalafon().equals("2")) {
            bonificacion = salarioBase * 1.3;
        } else {
            bonificacion = salarioBase * 1.5;
        }
        return bonificacion;
    }


    
}

