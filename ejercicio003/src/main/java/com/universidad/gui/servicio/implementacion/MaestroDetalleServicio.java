package com.universidad.gui.servicio.implementacion;

import com.universidad.gui.modelo.implementacion.Comanda;
import com.universidad.gui.modelo.implementacion.ESerGen;
import com.universidad.gui.servicio.IMaestroDetalleServicio;

import java.util.ArrayList;
import java.util.List;

public class MaestroDetalleServicio implements IMaestroDetalleServicio {

private final ESerGenServicio eSerGenServicio;
private final ComandaServicio comandaServicio;

    public MaestroDetalleServicio(ESerGenServicio eSerGenServicio, ComandaServicio comandaServicio) {

        this.eSerGenServicio = eSerGenServicio;
        this.comandaServicio = comandaServicio;


    }


    //Métodos asociados con Comanda (DETALLE Relación maestro/detalle)

    /**
     * Método que permite crear una comanda y dejarla asociada a un empleado de servicios generales,
     * usado por la GUICrearyAsignarComanda.
     *
     * @param idESerGen es la cédula del empleado de servicios; es su atributo noDocumento. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param descripcion es la descripción a grandes razgos de la comanda de almuerzos del restaurante doña doris. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param principio describe si el principio será frijoles, arbeja, lentejas o verdura. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param proteina describe si la carne será de res, de cerdo o de pollo. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param sopa describe el tipo de sopa. No admite caracteres extraños. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param fechaComanda es la fecha de caducidad de la comanda.
     */
    @Override
    public void crearYAsignarComanda(String idESerGen, String descripcion, String principio, String proteina, String sopa, String fechaComanda) {

        try {

            ESerGen serGenerales = this.eSerGenServicio.buscarESerGenPorNoDocumento(idESerGen);
            this.comandaServicio.crearYAgregarComanda(descripcion, principio, proteina, sopa, fechaComanda);
            serGenerales.agregarComanda(this.comandaServicio.mostrarComandas().getLast());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Método que busca el último empleado de la lista del servicio y retorna su nombre. Es opcional a simplemente traer al empleado desde el servicio mediante el método de buscarlo por su id y obtener su nombre.
     * @return el nombre del empleado, String
     */
    public String getNombreESerGen(){
        return this.eSerGenServicio.mostrarESerGen().getLast().getNombre();
    }

    /**
     * Método que busca el último empleado de la lista del servicio y retorna su tipo de documento. Es opcional a simplemente traer al empleado desde el servicio mediante el método de buscarlo por su id y obtener su tipo de documento.
     * @return el tipo de documento del empleado, String
     */
    public String getTipoDocumentoESerGen(){
        return this.eSerGenServicio.mostrarESerGen().getLast().getTipoDocumento();
    }

    /**
     * Método que busca el último empleado de la lista del servicio y retorna su número de documento. Es opcional a simplemente traer al empleado desde el servicio mediante el método de buscarlo por su id y obtener su número de documento.
     * @return el nombre del empleado, String
     */
    public String getNoDocumentoESerGen(){
        return this.eSerGenServicio.mostrarESerGen().getLast().getTipoDocumento();
    }


    /**
     * Método para que una comanda sea eliminada lógicamente. El atributo estatus de la comanda pasa a valer "IN".
     * Al usar este metodo sobre una comanda, esta no aparecera al buscarse o al listar todas las existentes.
     * Este metodo tambien hace que el atributo de tipo ESerGen de la comanda pase a valer null.
     * @param idESerGen String, es un número de documento de un empleado de servicios generales.
     * @param idComanda int, es el id unívoco e incremental de una comanda.
     */
    public void eliminaryDesasociarComanda(String idESerGen, int idComanda) {

        try {
            ESerGen serGenerales = eSerGenServicio.buscarESerGenPorNoDocumento(idESerGen);
            Comanda comanda = comandaServicio.buscarComandaID(idComanda);
            serGenerales.removerComanda(comanda);
            comandaServicio.eliminarComandaLogId(idComanda);

        } catch (Exception e) {
            System.out.println("No se pudo eliminar la comanda: " + e);
        }
    }

    /**
     * Método para eliminar lógicamente una comanda no asociada. Su estatus pasa a ser "IN".
     * @param idComanda int, el id de una comanda.
     */
    public void eliminarComanda(int idComanda) {

        this.comandaServicio.eliminarComandaLogId(idComanda);
    }

    /**
     * Método que muestra todas las comandas asociadas a un empleado.
     * @param idESerGen String, el número de documento de un empleado.
     * @return una copia de la lista que tiene como atributo el empleado de servicios generales con todas las comandas asociadas con el.
     */
    public List<Comanda> mostrarComandaPorESerGen(String idESerGen) {

        ESerGen serGenerales = this.eSerGenServicio.buscarESerGenPorNoDocumento(idESerGen);
        if (serGenerales.getComandas().isEmpty())
            throw new IllegalArgumentException("No se han registrado comandas para el empleado " + serGenerales.getNombre() + " con " + serGenerales.getTipoDocumento() + " No. " + serGenerales.getNoDoumento()+".");
        return new ArrayList<>(serGenerales.getComandas());
    }

    /**
     * Método para buscar una comanda por su ID.
     * @param id int, el id de una comanda.
     * @return un objeto del tipo Comanda, cuyo id es igual al id ingresado como parametro.
     */
    public Comanda buscarComandaPorId(int id) {

        return this.comandaServicio.buscarComandaID(id);
    }

    /**
     * Método para actualizar una comanda, buscándola por su ID.
     * @param descripcion es la descripción a grandes razgos de la comanda de almuerzos del restaurante doña doris. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param principio describe si el principio será frijoles, arbeja, lentejas o verdura. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param proteina describe si la carne será de res, de cerdo o de pollo. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param sopa describe el tipo de sopa. No admite caracteres extraños. Solo permite 50 carácteres incluidos espacios en blanco.
     * @param fechaCaducidad es la fecha de caducidad de la comanda.
     */
    public void actualizarComanda( int idComanda, String descripcion, String principio, String proteina, String sopa, String fechaCaducidad) {

        this.comandaServicio.actualizarComanda(idComanda, descripcion, principio, proteina, sopa, fechaCaducidad);
    }

    /**
     * Método para listar todas las comandas existentes en la lista del servicio de las comandas cuyo estatus sea "AC".
     * @return una copia de la lista del servicio de las comandas.
     */
    public List<Comanda> mostrarComandas() {

        return new ArrayList<>(this.comandaServicio.mostrarComandas());
    }

    /**
     * Método para asociar una comanda existente a un empleado de servicios generales
     * @param ESerGenId String, es el número de documento de un empleado de servicios generales.
     * @param comandaId int, es el id de una comanda.
     */
    public void asignarComanda(String ESerGenId, int comandaId) {

        ESerGen serGenerales = this.eSerGenServicio.buscarESerGenPorNoDocumento(ESerGenId);
        Comanda comanda = comandaServicio.buscarComandaID(comandaId);

        if (comanda.getEserGen() != null) {
            throw new IllegalArgumentException("La comanda con ID " + comandaId + " ya fué asignada al empleado " + comanda.getEserGen().getNombre() + " con " + comanda.getEserGen().getTipoDocumento() + " No." + comanda.getEserGen().getNoDoumento());
        }

        comanda.setEserGen(serGenerales);
    }

    /**
     * Método para acceder al servicio de los empleados de servivios generales
     * @return el objeto servicio de los empleados de servicios generales que se inyectó.
     */
    public ESerGenServicio getESerGenServicio() {
        return this.eSerGenServicio;
    }

    /**
     * Método para acceder al servicio de las comandas
     * @return el objeto servicio de las comandas que se inyectó.
     */
    public ComandaServicio getServicioComanda() {
        return this.comandaServicio;
    }
}
