package com.universidad.gui.vista;

import com.toedter.calendar.JDateChooser;
import com.universidad.gui.modelo.implementacion.Comanda;
import com.universidad.gui.modelo.implementacion.ESerGen;
import com.universidad.gui.servicio.implementacion.ESerGenServicio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIAddAndAssignComanda extends JFrame {

    private ESerGenServicio eSerGenServicio;
    // Componentes
    private JPanel panelPrincipal;
    private JPanel panelBusqueda;
    private JPanel panelDatos;
    private JPanel panelBotones;
    private JTextField txtBuscar, txtID, txtDescripcion, txtPrincipio, txtProteina, txtSopa;
    private JDateChooser cldFechaCaducidad;
    private JButton btnBuscar, btnSalir, btnCrearYAsignar;

    public GUIAddAndAssignComanda(ESerGenServicio eSerGenServicio) {
        this.eSerGenServicio = eSerGenServicio;
        initComponentsManual();
        setLocationRelativeTo(null);
    }

    private void initComponentsManual() {
        setTitle("Crear y Asignar Comanda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(495, 425);

        // Inicializar todos los componentes ANTES de usarlos
        btnBuscar = new JButton("Buscar");
        btnSalir = new JButton("Salir");
        btnCrearYAsignar = new JButton("Crear y Asignar");
        txtBuscar = new JTextField(15);
        cldFechaCaducidad = new JDateChooser();
        cldFechaCaducidad.setDateFormatString("dd/MM/yyyy");
        txtID = new JTextField();
        txtDescripcion = new JTextField();
        txtPrincipio = new JTextField();
        txtProteina = new JTextField();
        txtSopa = new JTextField();

        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de búsqueda (Norte)
        panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar por # documento:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        // Panel de datos (Centro) - Inicialmente oculto
        panelDatos = new JPanel(new GridLayout(6, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos de la Comanda"));
        panelDatos.add(new JLabel("Asignada a Empleado con Documento No. "));
        panelDatos.add(txtID);
        panelDatos.add(new JLabel("Fecha de Caducidad:"));
        panelDatos.add(cldFechaCaducidad);
        panelDatos.add(new JLabel("Descripción:"));
        panelDatos.add(txtDescripcion);
        panelDatos.add(new JLabel("Principio:"));
        panelDatos.add(txtPrincipio);
        panelDatos.add(new JLabel("Proteina:"));
        panelDatos.add(txtProteina);
        panelDatos.add(new JLabel("Sopa:"));
        panelDatos.add(txtSopa);
        panelDatos.setVisible(false);

        // Panel de botones con GridBagLayout
        panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurar tamaño preferido de botones
        btnSalir.setPreferredSize(new Dimension(100, 30));
        btnCrearYAsignar.setPreferredSize(new Dimension(100, 30));

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
        panelBotones.add(btnCrearYAsignar, gbc);

        // Agregar paneles al principal
        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Configurar action listeners
        btnBuscar.addActionListener(this::buscarEmpleado);
        btnCrearYAsignar.addActionListener(this::crearYAsignarComanda);
        btnSalir.addActionListener(e -> dispose());

        setContentPane(panelPrincipal);
    }

    private void buscarEmpleado(ActionEvent evt) {
        try {

            ESerGen serGenerales = eSerGenServicio.buscarESerGenPorNoDocumento(this.txtBuscar.getText());
            JOptionPane.showMessageDialog(this, "Empleado encontrado. Presione 'Ok' o cierre esta ventana para crear y asignar una comanda\nAl empleado " + serGenerales.getNombre() + " con " + serGenerales.getTipoDocumento() + " No. " + serGenerales.getNoDoumento());
            this.txtID.setText(this.txtBuscar.getText());
            this.txtID.setEditable(false);
            mostrar();

        } catch (Exception e) {
            limpiar();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }


    private void crearYAsignarComanda(ActionEvent evt) {
        try {
            String idESerGen = this.txtBuscar.getText();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String fechaComanda = formatoFecha.format(this.cldFechaCaducidad.getDate());
            eSerGenServicio.crearYAsignarComanda(idESerGen, this.txtDescripcion.getText(), this.txtPrincipio.getText(), this.txtProteina.getText(), this.txtSopa.getText(), fechaComanda );
            int idComanda = eSerGenServicio.getIdComanda();
            //Acá es válido crear el objeto serGenerales y obtener sus datos con get(), ya que simula que el cliente debera crear el json que se envia a la api, y del json creado puede extraer esa información para personalizar un mensaje de creacion exitosao, en este caso, de creación y asociación exitosas.
            String nombreSerGenerales = eSerGenServicio.getNombreESerGen();
            String tipoDocumentoSerGenerales = eSerGenServicio.getTipoDocumentoESerGen();
            String noDocumentoSerGenerales = eSerGenServicio.getNoDocumentoESerGen();

            JOptionPane.showMessageDialog(this, "Comanda con id " + idComanda + " creada y asignada al empleado " + nombreSerGenerales + " con " + tipoDocumentoSerGenerales + " NO. " + noDocumentoSerGenerales);
            limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void limpiar() {
        txtBuscar.setText("");
        txtDescripcion.setText("");
        txtID.setText("");
        txtPrincipio.setText("");
        txtProteina.setText("");
        txtSopa.setText("");

        panelDatos.setVisible(false);
    }

    private void mostrar() {

        panelDatos.setVisible(true);

    }

}
