package com.universidad.gui.servicio;

public interface IVerificacionServicio {

    public void verificarYAgregarAdministrativo(String noDocumento, String tipoDocumento, String nombre, double salario, String estatus, String escalafon);

    public void verificarYAgregarESerGen(String noDocumento, String tipoDocumento, String nombre, double salarioBase, String estatus, boolean certAlturas);

}
