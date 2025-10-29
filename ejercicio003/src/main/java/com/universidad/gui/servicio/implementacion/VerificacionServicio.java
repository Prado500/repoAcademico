package com.universidad.gui.servicio.implementacion;

import com.universidad.gui.modelo.implementacion.Administrativo;
import com.universidad.gui.modelo.implementacion.ESerGen;
import com.universidad.gui.servicio.IVerificacionServicio;


public class VerificacionServicio implements IVerificacionServicio {

    AdministrativoServicio administrativoServicio;
    ESerGenServicio eSerGenServicio;

    public VerificacionServicio(AdministrativoServicio administrativoServicio, ESerGenServicio eSerGenServicio) {

        this.administrativoServicio = administrativoServicio;
        this.eSerGenServicio = eSerGenServicio;

    }

    ;


    /**
     * Método que verifica que el noDocumento de un nuevo administrativo no haya sido otorgado a un empleado de servicios generales, y que llama al metodo del servicio de los administrativos que agrega los administrativos.
     *
     * @param noDocumento   String, número de documento del administrativo.
     * @param tipoDocumento String, tipo de documeto ("CC", "CE", "PA").
     * @param nombre        String.
     * @param salario       double.
     * @param estatus       String, ("AC", "IN").
     * @param escalafon     String, ("1". "2", "3").
     */
    @Override
    public void verificarYAgregarAdministrativo(String noDocumento, String tipoDocumento, String nombre, double salario, String estatus, String escalafon) {
        for (ESerGen serGenerales : this.eSerGenServicio.mostrarESerGen()) {
            if (serGenerales.getNoDoumento().equals(noDocumento) && serGenerales.getEstatus().equals("AC")) {
                throw new IllegalArgumentException(
                        "Ya existe un empleado de servicios generales con el documento " + noDocumento + "."
                );
            }
        }

        this.administrativoServicio.agregarAdministrativo(noDocumento, tipoDocumento, nombre, salario, estatus, escalafon);

    }

    @Override
    public void verificarYAgregarESerGen (String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus, boolean certAlturas){
        for (Administrativo administrativo : this.administrativoServicio.mostrarAdministrativo()) {
            if (administrativo.getNoDoumento().equals(noDocumento) && administrativo.getEstatus().equals("AC")) {
                throw new IllegalArgumentException(
                        "Ya existe un empleado Administrativo con el documento " + noDocumento + "."
                        );
            }
        }
    }
}


