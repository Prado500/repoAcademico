/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.modelo.implementacion;

import com.universidad.gui.modelo.Empleado;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Alejandro
 */
public class Administrativo extends Empleado {

    private String escalafon;
    private boolean hasBonificacion = false;
    private double bonificacion;

    public Administrativo(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus, String escalafon, boolean hasBonificacion, double bonificacion) {
        super(noDocumento, tipoDocumento, nombre, salarioBase, estatus);
        verificarEscalafon(escalafon);
        this.escalafon = escalafon;
        this.hasBonificacion = hasBonificacion;
        this.bonificacion = calcularBonificacion(salarioBase);
    }

    public String getEscalafon() {
        return escalafon;
    }

    public boolean getHasBonificacion() {
        return hasBonificacion;
    }

    public void setEscalafon(String escalafon) {
        verificarEscalafon(escalafon);
        this.escalafon = escalafon;
    }

    public void setHasBonificacion(boolean hasBonificacion) {
        this.hasBonificacion = hasBonificacion;
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

    private double calcularBonificacion(Double salarioBase){
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
    
    @Override
    public void aplicarBonificacion(ArrayList<Administrativo> administrativos) {
        double valorRetorno;

        for (Administrativo administrativo : administrativos) {

            if (!(administrativo.getHasBonificacion())) {
                administrativo.setHasBonificacion(true);
                double valor = administrativo.getSalarioBase();

                if (administrativo.getEscalafon() == "1") {
                    valorRetorno = valor * 1.1;
                }
                if (administrativo.getEscalafon() == "2") {
                    valorRetorno = valor * 1.3;
                } else {
                    valorRetorno = valor * 1.5;
                }
                administrativo.setSalarioBase(valorRetorno);
            }

        }

    }

    @Override
    public double calcularNominaConBonificacion(ArrayList<Administrativo> administrativos) {
        double valorNomina = 0;
        double nominaAcumulada = 0;
        for (Administrativo administrativo : administrativos) {
            double valor = administrativo.getSalarioBase();
            
            if (!(administrativo.hasBonificacion)) {

                if (administrativo.getEscalafon() == "1") {
                    valorNomina = valor * 1.1;
                }
                if (administrativo.getEscalafon() == "2") {
                    valorNomina = valor * 1.3;
                } else {
                    valorNomina = valor * 1.5;
                }
                nominaAcumulada += valorNomina;
            } else {
                nominaAcumulada += valor;
            }
        }

        return nominaAcumulada;

    }

    @Override
    public double calcularNomina(ArrayList<Administrativo> administrativos) {
        double nominaAcumulada = 0;

        for (Administrativo administrativo : administrativos) {
            nominaAcumulada += administrativo.getSalarioBase();
        }
        return nominaAcumulada;
    }
}
