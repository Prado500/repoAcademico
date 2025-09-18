/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.modelo.implementacion;

import com.universidad.gui.modelo.Empleado;
import java.util.ArrayList;

public class ESerGen extends Empleado {

    private boolean cerAlturas;

    public ESerGen(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus, boolean cerAlturas) {
        super(noDocumento, tipoDocumento, nombre, salarioBase, estatus );
        this.cerAlturas = cerAlturas;
    }

    public boolean getCerAlturas() {
        return this.cerAlturas;
    }
    
    public void setCerAlturas(boolean cerAlturas) {
        this.cerAlturas = cerAlturas;
    }

    
    private boolean isBoolean (String cerAlturas){
    return "si".equalsIgnoreCase(cerAlturas) || "no".equalsIgnoreCase(cerAlturas);
    }
    
    @Override
    public void aplicarBonificacion(ArrayList<Administrativo> administrativos) {
        double valorRetorno = this.getSalarioBase();
        if (cerAlturas) {
            valorRetorno *= 1.5;
        }
        return valorRetorno;
    }

}
