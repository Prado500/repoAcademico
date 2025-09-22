/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.modelo.implementacion;

import com.universidad.gui.modelo.Empleado;
import java.util.ArrayList;
import java.util.List;

public class ESerGen extends Empleado<ESerGen> {

    private boolean cerAlturas;
    private List<Comanda> comandas;

    public ESerGen(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus, boolean cerAlturas) {
        super(noDocumento, tipoDocumento, nombre, salarioBase, estatus);

        this.cerAlturas = cerAlturas;
        this.setBonificacion(this.aplicarBonificacion(this.getSalarioBase()));
        this.comandas = new ArrayList<>();
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
    public double aplicarBonificacion(Double salarioBase) {
        if (this.cerAlturas) {
            salarioBase *= 1.5;
        }
        return salarioBase;
    }

    //Metodos para gestionar la asociacion con Comanda
    
    public void agregarComanda (Comanda comanda){
        this.comandas.add(comanda);
        comanda.setEserGen(this);
    }
    
    public void removerComanda(Comanda comanda){
    this.comandas.remove(comanda);
    comanda.setEserGen(null);
    }
    
    public List<Comanda> getComandas(){
//        List<Comanda> retorno = new ArrayList<Comanda>();
//        for (Comanda comanda : comandas){
//            retorno.add(comanda);
//        }
        return new ArrayList<>(comandas); //copia de comandas
    }

    public void removerComandas(){
    
        for(Comanda comanda : comandas){
            comanda.setEserGen(null);
        }
        comandas.clear();
    }
    
    
}
