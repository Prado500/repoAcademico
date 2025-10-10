package com.universidad.gui.servicio;

import com.universidad.gui.modelo.implementacion.Comanda;

import java.util.List;

public interface IMaestroDetalleServicio {

    public void crearYAsignarComanda(String idESerGen, String descripcion, String principio, String proteina, String sopa, String fechaComanda);

    public void eliminaryDesasociarComanda(String idESerGen, int idComanda);

    public void eliminarComanda(int idComanda);

    public List<Comanda> mostrarComandaPorESerGen(String idESerGen);

    public Comanda buscarComandaPorId(int id);

    public void actualizarComanda( int idComanda, String descripcion, String principio, String proteina, String sopa, String fechaCaducidad);

    public List<Comanda> mostrarComandas();

    public void asignarComanda(String ESerGenId, Comanda comanda);


}
