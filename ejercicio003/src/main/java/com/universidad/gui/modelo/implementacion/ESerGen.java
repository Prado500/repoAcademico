/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.modelo.implementacion;

import com.universidad.gui.modelo.Empleado;
import java.util.ArrayList;

public class ESerGen extends Empleado {

    private boolean cerAlturas;

    public ESerGen(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus, boolean cerAlturas, double bonificacion) {
        super(noDocumento, tipoDocumento, nombre, salarioBase, estatus, bonificacion);

        this.cerAlturas = cerAlturas;
    }

    public boolean getCerAlturas() {
        return this.cerAlturas;
    }

    public void setCerAlturas(boolean cerAlturas) {
        this.cerAlturas = cerAlturas;
    }

    private boolean isBoolean(String cerAlturas) {
        return "si".equalsIgnoreCase(cerAlturas) || "no".equalsIgnoreCase(cerAlturas);
    }

    @Override
    public double calcularNominaConBonificacion(ArrayList<ESerGen> serGenerales) {

        double nominaAcumulada = 0;
        for (Administrativo administrativo : administrativos) {
            nominaAcumulada += administrativo.getBonificacion();
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

    @Override
    public double aplicarBonificacion(Double salarioBase) {
        if (this.cerAlturas) {
            salarioBase *= 1.5;
        }
        return salarioBase;
    }

}
