package com.universidad.gui.vista;

import com.universidad.gui.modelo.implementacion.Administrativo;
import com.universidad.gui.servicio.implementacion.EmpleadoServicio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUICalcularNominaAdministrativos extends JFrame {

    private EmpleadoServicio<Administrativo> empleadoServicioAdministrativo;

    // Componentes
    private JPanel panelPrincipal;
    // private JPanel panelBusqueda;
    private JPanel panelDatos;
    private JPanel panelBotones;
    private JTextField txtNominaCruda, txtNominaTotal;
    private JButton btnSalir, btnCalcular;

    public GUICalcularNominaAdministrativos(EmpleadoServicio<Administrativo> empleadoServicioAdministrativo) {
        this.empleadoServicioAdministrativo = empleadoServicioAdministrativo;
        initComponentsManual();
        setLocationRelativeTo(null);
        this.txtNominaCruda.setEditable(false);
        this.txtNominaTotal.setEditable(false);
    }

    private void initComponentsManual() {
        setTitle("Calcular nóminas de los administrativos");
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
        //btnCalcular.addActionListener(this::mostrar);
        btnSalir.addActionListener(e -> dispose());

        setContentPane(panelPrincipal);
    }
    
    
    private void calcularNomina(ActionEvent evt) {
        try {
            if (empleadoServicioAdministrativo.mostrar().isEmpty()) {
                JOptionPane.showMessageDialog(this, "La lista está vacia");
                this.ocultar();
            }else{
            this.mostrar();
            this.txtNominaCruda.setText(Double.toString(empleadoServicioAdministrativo.calcularNomina(empleadoServicioAdministrativo.mostrar())));
            }
            
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

    }
    

    private void calcularNominaConBonificacion(ActionEvent evt) {

        try {
             
            this.txtNominaTotal.setText(Double.toString(empleadoServicioAdministrativo.calcularNominaConBonificacion(empleadoServicioAdministrativo.mostrar())));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

       
    }


    private void limpiar() {
        this.txtNominaCruda.setText("");
        this.txtNominaTotal.setText("");
        panelDatos.setVisible(false);
    }

    private void mostrar() {

        panelDatos.setVisible(true);

    }

    private void ocultar() {

        panelDatos.setVisible(false);

    }
    
    //private void

}
