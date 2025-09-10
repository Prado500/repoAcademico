/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.modelo.implementacion;

import com.universidad.gui.modelo.Empleado;

/**
 *
 * @author Alejandro
 */
public class Administrativo extends Empleado {

    private int escalafon;

    public Administrativo(int escalafon, String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus) {
        super(noDocumento, tipoDocumento, nombre, salarioBase, estatus);
        this.escalafon = escalafon;
    }

    public int getEscalafon() {
        return escalafon;
    }

    public void setEscalafon(int escalafon) {
        this.escalafon = escalafon;
    }

    @Override
    public double calcularBonificacion() {
        double valor = this.getSalarioBase();
        double valorRetorno;
        if (this.escalafon == 1) {
            valorRetorno = valor * 1.1;
        }
        if (this.escalafon == 2) {
            valorRetorno = valor * 1.3;
        } else {
            valorRetorno = valor * 1.5;
        }
        this.setSalarioBase(valorRetorno);
        return valorRetorno;
    }

}
