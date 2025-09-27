package com.universidad.gui.vista;

import com.toedter.calendar.JDateChooser;
import com.universidad.gui.modelo.implementacion.Comanda;
import com.universidad.gui.modelo.implementacion.ESerGen;
import com.universidad.gui.servicio.implementacion.ESerGenServicio;
import com.universidad.gui.servicio.implementacion.EmpleadoServicio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

public class GUIActualizarComanda extends JFrame {

    private ESerGenServicio eSerGenServicio;

    // Componentes
    private JPanel panelPrincipal;
    private JPanel panelBusqueda;
    private JPanel panelDatos;
    private JPanel panelBotones;
    private JTextField txtBuscar, txtID, txtIdESerGen, txtDescripcion, txtPrincipio, txtProteina, txtSopa;
    private JDateChooser jdcFechaCaducidad;
    private JButton btnBuscar, btnSalir, btnActualizar;

    public GUIActualizarComanda(ESerGenServicio eSerGenServicio) {
        this.eSerGenServicio = eSerGenServicio;
        initComponentsManual();
        setLocationRelativeTo(null);
    }

    private void initComponentsManual() {
        setTitle("Actualizar Comanda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(495, 425);

        // Inicializar todos los componentes ANTES de usarlos
        btnBuscar = new JButton("Buscar");
        btnSalir = new JButton("Salir");
        btnActualizar = new JButton("Actualizar");
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
        panelDatos.add(new JLabel("Nueva Fecha de Caducidad: "));
        panelDatos.add(jdcFechaCaducidad);
        panelDatos.add(new JLabel("Nueva Descripción:"));
        panelDatos.add(txtDescripcion);
        panelDatos.add(new JLabel("Nuevo Principio: "));
        panelDatos.add(txtPrincipio);
        panelDatos.add(new JLabel("Nueva proteina: "));
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
        btnActualizar.addActionListener(this::actualizarComanda);
        btnSalir.addActionListener(e -> dispose());

        setContentPane(panelPrincipal);
    }

    private void buscarComanda(ActionEvent evt) {
        try {
            int idComanda = Integer.parseInt(this.txtBuscar.getText());
            Comanda comanda = eSerGenServicio.buscarComandaPorId(idComanda);
            if (comanda == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ningún empleado con documento " + txtBuscar.getText());
                limpiar();
            } else {
                this.txtID.setText(Integer.toString(comanda.getId()));
                this.txtIdESerGen.setText(comanda.getEserGen().getNoDoumento());
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
                
               
                mostrar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void actualizarComanda(ActionEvent evt) {
        try {
            String id = txtBuscar.getText();
            ESerGen serGenerales = empleadoServicioESerGen.searchElementoByNoDocumento(id.strip());
            empleadoServicioESerGen.actualizarElemento(id.strip(), cmbTipoDocumento.getSelectedItem().toString(), txtNombre.getText().strip().toUpperCase(), Double.parseDouble(txtSalario.getText()));
            serGenerales.setCerAlturas(Boolean.parseBoolean(hasAlturasLogical(cmbAlturas.getSelectedItem().toString())));
            JOptionPane.showMessageDialog(this, "Empleado con id " + id + " y nombre " + txtNombre.getText() + " actualizado exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
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
