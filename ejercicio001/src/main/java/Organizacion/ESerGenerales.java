/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Organizacion;

import java.time.LocalDate;

/**
 *
 * @author Alejandro
 */
public class ESerGenerales extends Empleado {
    
    private String experticia;
    private boolean alturas;

    public ESerGenerales(String experticia, boolean alturas, String noDocumento, String tipoDocumento, double salarioBase, String genero, LocalDate fechaNacimiento, String estado) {
        super(noDocumento, tipoDocumento, salarioBase, genero, fechaNacimiento, estado);
        this.experticia = experticia;
        this.alturas = alturas;
    }

    public String getExperticia() {
        return experticia;
    }

    public boolean isAlturas() {
        return alturas;
    }

    public void setExperticia(String experticia) {
        this.experticia = experticia;
    }

    public void setAlturas(boolean alturas) {
        this.alturas = alturas;
    }

    @Override
    public String toString() {
        return "ESerGenerales{" + "experticia=" + experticia + ", alturas=" + alturas + '}';
    }
    
    
}

