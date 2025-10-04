package com.universidad.gui.vista;

import com.universidad.gui.modelo.implementacion.Administrativo;
import com.universidad.gui.servicio.implementacion.AdministrativoServicio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUICalcularNominaAdministrativos extends JFrame {

    private final AdministrativoServicio administrativoServicio;

    // Componentes
    private JPanel panelPrincipal;
    private JPanel panelDatos;
    private JPanel panelBotones;
    private JTextField txtNominaCruda, txtNominaTotal;
    private JButton btnSalir, btnCalcular;

    public GUICalcularNominaAdministrativos(AdministrativoServicio administrativoServicio) {
        this.administrativoServicio = administrativoServicio;
        initComponentsManual();
        setLocationRelativeTo(null);
        this.txtNominaCruda.setEditable(false);
        this.txtNominaTotal.setEditable(false);
    }

    private void initComponentsManual() {
        setTitle("Calcular nóminas de los Administrativos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(495, 425);

        // Inicializar todos los componentes ANTES de usarlos
        btnSalir = new JButton("Salir");
        btnCalcular = new JButton("Calcular");
        txtNominaCruda = new JTextField(15);
        txtNominaTotal = new JTextField();

        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de búsqueda (Norte)
//        panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        panelBusqueda.add(new JLabel("Buscar por # documento:"));
//        panelBusqueda.add(btnNominaCruda);
        // Panel de datos (Centro) - Inicialmente oculto
        panelDatos = new JPanel(new GridLayout(2, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Mosáico de nóminas"));
        panelDatos.add(new JLabel("Nómina antes de bonificación:"));
        panelDatos.add(txtNominaCruda);
        panelDatos.add(new JLabel("Nómina después de bonificación:"));
        panelDatos.add(txtNominaTotal);
        panelDatos.setVisible(false);

        // Panel de botones con GridBagLayout
        panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurar tamaño preferido de botones
        btnSalir.setPreferredSize(new Dimension(100, 30));
        btnCalcular.setPreferredSize(new Dimension(100, 30));

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
        panelBotones.add(btnCalcular, gbc);

        // Agregar paneles al principal
        //panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Configurar action listeners
        btnCalcular.addActionListener(this::calcularNomina);
        btnCalcular.addActionListener(this::calcularNominaConBonificacion);
        btnSalir.addActionListener(e -> dispose());

        setContentPane(panelPrincipal);
    }

    private void calcularNomina(ActionEvent evt) {

        try {
            this.txtNominaCruda.setText("$ " + this.aplicarFormato(administrativoServicio.calcularNominaAdministrativo(administrativoServicio.mostrarAdministrativo())));
            this.mostrar();

        } catch (Exception e) {
            this.ocultar();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

    }

    private void calcularNominaConBonificacion(ActionEvent evt) {

        try {
            this.txtNominaTotal.setText("$ " + this.aplicarFormato(administrativoServicio.calcularNominaConBonificacionAdministrativo(administrativoServicio.mostrarAdministrativo())));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

    }


    private void mostrar() {

        panelDatos.setVisible(true);

    }

    private void ocultar() {

        panelDatos.setVisible(false);

    }

    private String aplicarFormato(Double numero) {

        DecimalFormat formato = new DecimalFormat("#,##0.00");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');
        formato.setDecimalFormatSymbols(simbolos);
        
        return formato.format(numero);
    }
}
