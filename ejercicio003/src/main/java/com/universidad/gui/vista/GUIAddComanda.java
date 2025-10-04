package com.universidad.gui.vista;

import com.toedter.calendar.JDateChooser;
import com.universidad.gui.modelo.implementacion.Comanda;
import com.universidad.gui.modelo.implementacion.ESerGen;
import com.universidad.gui.servicio.implementacion.ESerGenServicio;
import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class GUIAddComanda extends JFrame {

    private ESerGenServicio eSerGenServicio;

    // Componentes
    private JPanel panelPrincipal;
    private JPanel panelDatos;
    private JPanel panelBotones;
    private JTextField txtDescripcion, txtPrincipio, txtProteina, txtSopa;
    private JDateChooser cldFechaCaducidad;
    private JButton btnSalir, btnCrear;

    public GUIAddComanda(ESerGenServicio eSerGenServicio) {
        this.eSerGenServicio = eSerGenServicio;
        initComponentsManual();
        setLocationRelativeTo(null);
    }

    private void initComponentsManual() {
        setTitle("Crear y Asignar Comanda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(495, 425);

        // Inicializar todos los componentes ANTES de usarlos
        btnSalir = new JButton("Salir");
        btnCrear = new JButton("Crear");
        cldFechaCaducidad = new JDateChooser();
        cldFechaCaducidad.setDateFormatString("dd/MM/yyyy");
        txtDescripcion = new JTextField();
        txtPrincipio = new JTextField();
        txtProteina = new JTextField();
        txtSopa = new JTextField();

        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // Panel de datos (Centro) - Inicialmente oculto
        panelDatos = new JPanel(new GridLayout(5, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos de la Comanda"));
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

        // Panel de botones con GridBagLayout
        panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurar tamaño preferido de botones
        btnSalir.setPreferredSize(new Dimension(100, 30));
        btnCrear.setPreferredSize(new Dimension(100, 30));

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
        panelBotones.add(btnCrear, gbc);

        // Agregar paneles al principal
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Configurar action listeners
        btnCrear.addActionListener(this::crearComanda);
        btnSalir.addActionListener(e -> dispose());

        setContentPane(panelPrincipal);
    }

    
    private void crearComanda(ActionEvent evt) {

        try {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String fechaComanda = formatoFecha.format(this.cldFechaCaducidad.getDate());
            eSerGenServicio.crearComandaIndependiente(this.txtDescripcion.getText(), this.txtPrincipio.getText(), this.txtProteina.getText(), this.txtSopa.getText(), fechaComanda);
            int idComanda = eSerGenServicio.getIdComanda();
            JOptionPane.showMessageDialog(this, "Comanda con id " + idComanda + " creada. ");
            limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void limpiar() {
        txtDescripcion.setText("");
        txtPrincipio.setText("");
        txtProteina.setText("");
        txtSopa.setText("");

    }

    private void mostrar() {

        panelDatos.setVisible(true);

    }

}
