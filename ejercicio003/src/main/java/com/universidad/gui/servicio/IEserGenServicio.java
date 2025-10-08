/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.universidad.gui.servicio;

import com.universidad.gui.modelo.implementacion.Administrativo;
import com.universidad.gui.modelo.implementacion.ESerGen;
import java.util.List;

/**
 *
 * @author David Alejandro De Los Reyes Ostos
 */
public interface IEserGenServicio {

    public void agregarESerGen(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus, boolean certAlturas);

    public List<ESerGen> mostrarESerGen();

    public ESerGen buscarESerGenPorNoDocumento(String noDocumento);

    public void actualizarESerGen(String nNoDocumento, String noDocumento, String tipoDocumento, String nombre, Double salario, boolean cerAlturas);

    public void eliminarLogicamenteESerGenPorId(String id);

    public double calcularNominaConBonificacionESerGen(List<ESerGen> serGenerales);

    public double calcularNominaESerGen(List<ESerGen> serGenerales);

    public String getNombreESerGen();

    public String getTipoDocumentoESerGen();

    public String getNoDocumentoESerGen();


}
