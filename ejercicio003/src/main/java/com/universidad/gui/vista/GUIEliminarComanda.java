package com.universidad.gui.vista;

import com.toedter.calendar.JDateChooser;
import com.universidad.gui.modelo.implementacion.Comanda;
import com.universidad.gui.servicio.implementacion.MaestroDetalleServicio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIEliminarComanda extends JFrame {

    private final MaestroDetalleServicio maestroDetalleServicio;

    // Componentes
    private JPanel panelPrincipal;
    private JPanel panelBusqueda;
    private JPanel panelDatos;
    private JPanel panelBotones;
    private JTextField txtBuscar, txtID, txtIdESerGen, txtDescripcion, txtPrincipio, txtProteina, txtSopa;
    private JDateChooser jdcFechaCaducidad;
    private JButton btnBuscar, btnSalir, btnActualizar;

    public GUIEliminarComanda(MaestroDetalleServicio maestroDetalleServicio) {
        this.maestroDetalleServicio = maestroDetalleServicio;
        initComponentsManual();
        this.txtID.setEditable(false);
        this.txtIdESerGen.setEditable(false);
        this.txtDescripcion.setEditable(false);
        this.txtPrincipio.setEditable(false);
        this.txtProteina.setEditable(false);
        this.txtSopa.setEditable(false);
        this.jdcFechaCaducidad.setEnabled(false);
        setLocationRelativeTo(null);
    }

    private void initComponentsManual() {
        setTitle("Eliminar Comanda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(495, 425);

        // Inicializar todos los componentes ANTES de usarlos
        btnBuscar = new JButton("Buscar");
        btnSalir = new JButton("Salir");
        btnActualizar = new JButton("Eliminar");
        txtBuscar = new JTextField(15);
        txtID = new JTextField();
        txtIdESerGen = new JTextField();
        txtDescripcion = new JTextField();
        txtPrincipio = new JTextField();
        txtProteina = new JTextField();
        txtSopa = new JTextField();
        jdcFechaCaducidad = new JDateChooser();
        jdcFechaCaducidad.setDateFormatString("dd/MM/yyyy");

        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de búsqueda (Norte)
        panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar ID:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        // Panel de datos (Centro) - Inicialmente oculto
        panelDatos = new JPanel(new GridLayout(7, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos de la Comanda"));
        panelDatos.add(new JLabel("ID:"));
        panelDatos.add(txtID);
        panelDatos.add(new JLabel("ID emp.: "));
        panelDatos.add(txtIdESerGen);
        panelDatos.add(new JLabel("Fecha de Caducidad: "));
        panelDatos.add(jdcFechaCaducidad);
        panelDatos.add(new JLabel("Descripción:"));
        panelDatos.add(txtDescripcion);
        panelDatos.add(new JLabel("Principio: "));
        panelDatos.add(txtPrincipio);
        panelDatos.add(new JLabel("Proteina: "));
        panelDatos.add(txtProteina);
        panelDatos.add(new JLabel("Nueva sopa: "));
        panelDatos.add(txtSopa);
        panelDatos.setVisible(false);

        // Panel de botones con GridBagLayout
        panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurar tamaño preferido de botones
        btnSalir.setPreferredSize(new Dimension(100, 30));
        btnActualizar.setPreferredSize(new Dimension(100, 30));

        // Botón Salir (izquierda)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 38, 10, 0);
        panelBotones.add(btnSalir, gbc);

        // Botón Actualizar (derecha)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 38);
        panelBotones.add(btnActualizar, gbc);

        // Agregar paneles al principal
        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Configurar action listeners
        btnBuscar.addActionListener(this::buscarComanda);
        btnActualizar.addActionListener(this::eliminarComanda);
        btnSalir.addActionListener(e -> dispose());

        setContentPane(panelPrincipal);
    }

    private void buscarComanda(ActionEvent evt) {
        try {
            int idComanda = Integer.parseInt(this.txtBuscar.getText().trim());
            Comanda comanda = maestroDetalleServicio.buscarComandaPorId(idComanda);
            this.txtID.setText(Integer.toString(comanda.getId()));
            String idESerGen = "No asignada";
            if (comanda.getEserGen() != null) {
                idESerGen = comanda.getEserGen().getNoDoumento();
            }
            this.txtIdESerGen.setText(idESerGen);
//                DateTimeFormatter formatoFechaCaducidad = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
//                LocalDate fechaCaducidad = LocalDate.parse(comanda.getFechaCaducidad(), formatoFechaCaducidad);

//                //Pasar de String A date
//    
//                SimpleDateFormat formatoFechaCaducidad = new SimpleDateFormat("dd/MM/yyyy");
//                formatoFechaCaducidad.setLenient(false);
//                formatoFechaCaducidad.parse(comanda.getFechaCaducidad());  
//                this.jdcFechaCaducidad.setDate(formatoFechaCaducidad.parse(comanda.getFechaCaducidad()));
            //pasar de LocalDateTime a Date
            DateTimeFormatter formatoFechaCaducidad = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
            LocalDate fechaCaducidad = LocalDate.parse(comanda.getFechaCaducidad(), formatoFechaCaducidad);
            Date fechaCaducidadDate = Date.from(fechaCaducidad.atStartOfDay(ZoneId.systemDefault()).toInstant());
            this.jdcFechaCaducidad.setDate(fechaCaducidadDate);
            this.txtDescripcion.setText(comanda.getDescripcion());
            this.txtPrincipio.setText(comanda.getPrincipio());
            this.txtProteina.setText(comanda.getProteina());
            this.txtSopa.setText(comanda.getSopa());
            mostrar();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            limpiar();

        }
    }

    private void eliminarComanda(ActionEvent evt) {
        try {

            if (this.txtID.getText().isEmpty()) {
                throw new IllegalArgumentException("""
                                                   Es necesario que busque la comanda por su ID único antes de poder eliminarla
                                                   """);
            }

            Comanda comanda = maestroDetalleServicio.buscarComandaPorId(Integer.parseInt(this.txtID.getText()));

            String placeholder = " no asignada";

            if (comanda.getEserGen() != null) {
                placeholder = " asignada al empleado " + comanda.getEserGen().getNombre() + " con " + comanda.getEserGen().getTipoDocumento() + " No. " + comanda.getEserGen().getNoDoumento() + "?";
            }

            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Está Seguro que desea eliminar la comanda con ID " + this.txtID.getText() + placeholder,
                    "Confirmar Elección",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                if (comanda.getEserGen() == null) {
                    JOptionPane.showMessageDialog(this, "Comanda con ID " + this.txtID.getText() + " y sin asignar eliminada exitosamente");
                    maestroDetalleServicio.eliminarComanda(Integer.parseInt(this.txtID.getText()));
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(this, "Comanda con ID " + this.txtID.getText() + " y asignada al empleado " + comanda.getEserGen().getNombre() + " con " + comanda.getEserGen().getTipoDocumento() + " No. " + comanda.getEserGen().getNoDoumento() + "\nEliminada exitosamente");
                    maestroDetalleServicio.eliminaryDesasociarComanda(comanda.getEserGen().getNoDoumento(), Integer.parseInt(this.txtID.getText()));
                    limpiar();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Eliminación cancelada");
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            limpiar();
        }
    }

    private void limpiar() {
        txtBuscar.setText("");
        txtDescripcion.setText("");
        txtID.setText("");
        txtIdESerGen.setText("");
        txtPrincipio.setText("");
        txtProteina.setText("");
        txtSopa.setText("");
        panelDatos.setVisible(false);
    }
    
    
    private void mostrar() {

        panelDatos.setVisible(true);

    }

}
