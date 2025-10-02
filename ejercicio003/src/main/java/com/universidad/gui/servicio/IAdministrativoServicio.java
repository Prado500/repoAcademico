/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.universidad.gui.servicio;

import com.universidad.gui.modelo.implementacion.Administrativo;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public interface IAdministrativoServicio {

    public void agregarAdministrativo( String noDocumento, String tipoDocumento, String nombre, double salario, String estatus, String escalafon);

    public List<Administrativo> mostrarAdministrativo();

    public Administrativo buscarAdministrativoPorNoDocumento(String noDocumento);

    public void actualizarAdministrativo(String nNoDocumento, String noDocumento, String tipoDocumento, String nombre, Double salario, String escalafon);

    public void eliminarLogicamenteAdministrativoPorId(String id);

    public double calcularNominaConBonificacionAdministrativo(List<Administrativo> elementos);

    public double calcularNominaAdministrativo(List<Administrativo> elementos);

}
