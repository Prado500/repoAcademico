package com.universidad.gui.vista;

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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIActualizarESerGen extends JFrame {

    private ESerGenServicio eSerGenServicio;

    // Componentes
    private JPanel panelPrincipal;
    private JPanel panelBusqueda;
    private JPanel panelDatos;
    private JPanel panelBotones;
    private JTextField txtBuscar, txtNombre, txtSalario, txtNuevoDocumento;
    private JComboBox<String> cmbTipoDocumento;
    private JComboBox<String> cmbAlturas;
    private JButton btnBuscar, btnSalir, btnActualizar;

    public GUIActualizarESerGen(ESerGenServicio eSerGenServicio) {
        this.eSerGenServicio = eSerGenServicio;
        initComponentsManual();
        setLocationRelativeTo(null);
    }

    private void initComponentsManual() {
        setTitle("Actualizar Empleado de Servicios Generales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(495, 425);

        // Inicializar todos los componentes ANTES de usarlos
        btnBuscar = new JButton("Buscar");
        btnSalir = new JButton("Salir");
        btnActualizar = new JButton("Actualizar");
        txtBuscar = new JTextField(15);
        txtNombre = new JTextField();
        txtSalario = new JTextField();
        txtNuevoDocumento = new JTextField();
        cmbTipoDocumento = new JComboBox<>(new String[]{"CC", "CE", "PA"});
        cmbAlturas = new JComboBox<>(new String[]{"SI", "NO"});

        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de búsqueda (Norte)
        panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar por # documento:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        // Panel de datos (Centro) - Inicialmente oculto
        panelDatos = new JPanel(new GridLayout(5, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos del Empleado"));
        panelDatos.add(new JLabel("Tipo Documento:"));
        panelDatos.add(cmbTipoDocumento);
        panelDatos.add(new JLabel("Cert. Alturas :"));
        panelDatos.add(cmbAlturas);
        panelDatos.add(new JLabel("Nombre:"));
        panelDatos.add(txtNombre);
        panelDatos.add(new JLabel("Salario:"));
        panelDatos.add(txtSalario);
        panelDatos.add(new JLabel("Nuevo # Documento:"));
        panelDatos.add(txtNuevoDocumento);
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
        btnBuscar.addActionListener(this::buscarEmpleado);
        btnActualizar.addActionListener(this::actualizarAdministrativo);
        btnSalir.addActionListener(e -> dispose());

        setContentPane(panelPrincipal);
    }

    private void buscarEmpleado(ActionEvent evt) {
        try {

            ESerGen serGenerales = eSerGenServicio.buscarESerGenPorNoDocumento(txtBuscar.getText());
            JOptionPane.showMessageDialog(this, "No se encontró ningún empleado con documento " + txtBuscar.getText());


            cmbTipoDocumento.setSelectedItem(serGenerales.getTipoDocumento());
            cmbAlturas.setSelectedItem(hasAlturas(serGenerales.getCerAlturas()));
            txtNombre.setText(serGenerales.getNombre());
            txtSalario.setText(Double.toString(serGenerales.getSalarioBase()));
            txtNuevoDocumento.setText(serGenerales.getNoDoumento());
            mostrar();

        } catch (Exception e) {
            limpiar();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void actualizarAdministrativo(ActionEvent evt) {
        try {

            String id = txtBuscar.getText();
            eSerGenServicio.actualizarESerGen(this.txtNuevoDocumento.getText(), id.strip(), cmbTipoDocumento.getSelectedItem().toString(), txtNombre.getText().strip().toUpperCase(), Double.parseDouble(txtSalario.getText()), Boolean.parseBoolean(hasAlturasLogical(cmbAlturas.getSelectedItem().toString())));
            JOptionPane.showMessageDialog(this, "Empleado con id " + id + " y nombre " + txtNombre.getText() + " actualizado exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private String hasAlturas(Boolean valor) { //saco del objeto y muestro como quiero
        String valRetorno = "NO";
        if (valor) {
            valRetorno = "SI";
        }
        return valRetorno;
    }

    private String hasAlturasLogical(String valor) {

        String valRetorno = "False";

        if (valor == "SI") {

            valRetorno = "True";
        }
        return valRetorno;
    }

    private void limpiar() {
        txtBuscar.setText("");
        txtNombre.setText("");
        txtSalario.setText("");
        txtNuevoDocumento.setText("");
        panelDatos.setVisible(false);
    }

    private void mostrar() {

        panelDatos.setVisible(true);

    }

}
