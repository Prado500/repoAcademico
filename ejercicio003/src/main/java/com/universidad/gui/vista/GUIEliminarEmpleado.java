/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.universidad.gui.vista;

/**
 *
 * @author Alejandro
 */
import com.universidad.gui.modelo.Empleado;
import com.universidad.gui.servicio.EmpleadoServicio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIEliminarEmpleado extends JFrame {

    private EmpleadoServicio<Empleado> empleadoServicio;

    // Componentes
    private JPanel panelPrincipal;
    private JPanel panelBusqueda;
    private JPanel panelDatos;
    private JPanel panelBotones;
    private JTextField txtBuscar, txtNombre, txtSalario, txtDocumento, txtTipoDocumento;
    private JButton btnBuscar, btnSalir, btnEliminar;

    public GUIEliminarEmpleado(EmpleadoServicio<Empleado> empleadoServicio) {
        this.empleadoServicio = empleadoServicio;
        initComponentsManual();
        setLocationRelativeTo(null);
    }

    private void initComponentsManual() {
        setTitle("Eliminar Empleado"); //1
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(495, 425);

        // Inicializar todos los componentes ANTES de usarlos
        btnBuscar = new JButton("Buscar");
        btnSalir = new JButton("Salir");
        btnEliminar = new JButton("Eliminar"); //2
        txtBuscar = new JTextField(15);
        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        txtSalario = new JTextField();
        txtSalario.setEditable(false);
        txtDocumento = new JTextField();
        txtDocumento.setEditable(false);
        txtTipoDocumento = new JTextField();
        txtTipoDocumento.setEditable(false);
        

        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de búsqueda (Norte)
        panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar por # documento:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        // Panel de datos (Centro) - Inicialmente oculto
        panelDatos = new JPanel(new GridLayout(4, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos del Empleado"));
        panelDatos.add(new JLabel("Tipo Documento:"));
        panelDatos.add(txtTipoDocumento);
        panelDatos.add(new JLabel("Nombre:"));
        panelDatos.add(txtNombre);
        panelDatos.add(new JLabel("Salario:"));
        panelDatos.add(txtSalario);
        panelDatos.add(new JLabel("Documento:"));
        panelDatos.add(txtDocumento);
        panelDatos.setVisible(false);

        // Panel de botones con GridBagLayout
        panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurar tamaño preferido de botones
        btnSalir.setPreferredSize(new Dimension(100, 30));
        btnEliminar.setPreferredSize(new Dimension(100, 30));

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
        panelBotones.add(btnEliminar, gbc);

        // Agregar paneles al principal
        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Configurar action listeners
        btnBuscar.addActionListener(this::buscarEmpleado);
        btnEliminar.addActionListener(this::borrarEmpleado); //3
        btnSalir.addActionListener(e -> dispose());

        setContentPane(panelPrincipal);
    }

    private void buscarEmpleado(ActionEvent evt) {
        try {
            Empleado empleado = empleadoServicio.searchElementoByNoDocumento(txtBuscar.getText());
            if (empleado == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ningún empleado con documento " + txtBuscar.getText());
                limpiar();
            } else {
                txtTipoDocumento.setText(empleado.getTipoDocumento());
                txtNombre.setText(empleado.getNombre());
                DecimalFormat formato = new DecimalFormat("#,##0.00");
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setGroupingSeparator('.');
                simbolos.setDecimalSeparator(',');
                formato.setDecimalFormatSymbols(simbolos);
                txtSalario.setText("$ " + formato.format(empleado.getSalarioBase()));
                txtDocumento.setText(empleado.getNoDoumento());
                mostrar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void borrarEmpleado(ActionEvent evt) {
        try {
            Empleado empleadoABorrar = empleadoServicio.searchElementoByNoDocumento(txtBuscar.getText());
            if (empleadoABorrar == null) {
                throw new IllegalArgumentException("No se ha encontrado un registro con noDocumento " + txtBuscar.getText());
            }
            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro que desea eliminar al empleado " + empleadoABorrar.getNombre() + " con noDocumento " + empleadoABorrar.getNoDoumento() + "?",
                    "Confirmar Elección",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                empleadoServicio.eliminarLogicamenteElementoPorId(txtBuscar.getText());
                JOptionPane.showMessageDialog(this, "Emplado " + empleadoABorrar.getNombre() + " con noDocumento " + empleadoABorrar.getNoDoumento() + " eliminado exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Eliminación canelada");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void limpiar() {
        txtBuscar.setText("");
        txtNombre.setText("");
        txtSalario.setText("");
        txtDocumento.setText("");
        txtTipoDocumento.setText(" ");
    }

    private void mostrar() {

        panelDatos.setVisible(true);

    }

}
