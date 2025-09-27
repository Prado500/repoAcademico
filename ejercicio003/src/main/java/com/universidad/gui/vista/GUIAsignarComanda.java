package com.universidad.gui.vista;

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

public class GUIAsignarComanda extends JFrame {

    private ESerGenServicio eSerGenServicio;

    // Componentes
    private JPanel panelPrincipal;
    private JPanel panelBusqueda;
    private JPanel panelDatos;
    private JPanel panelBotones;
    private JTextField txtBuscar, txtID;
    private JButton btnBuscar, btnSalir, btnAsignar;

    public GUIAsignarComanda(ESerGenServicio eSerGenServicio) {
        this.eSerGenServicio = eSerGenServicio;
        initComponentsManual();
        setLocationRelativeTo(null);
    }

    private void initComponentsManual() {
        setTitle("Asignar Comanda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(495, 425);

        // Inicializar todos los componentes ANTES de usarlos
        btnBuscar = new JButton("Buscar");
        btnSalir = new JButton("Salir");
        btnAsignar = new JButton("Asignar");
        txtBuscar = new JTextField(15);
        txtID = new JTextField();

        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de búsqueda (Norte)
        panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar por # documento:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        // Panel de datos (Centro) - Inicialmente oculto
        panelDatos = new JPanel(new GridLayout(1, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos de la Comanda"));
        panelDatos.add(new JLabel("Se Asignará al Empleado con Documento No. "));
        panelDatos.add(txtID);
        panelDatos.setVisible(false);

        // Panel de botones con GridBagLayout
        panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurar tamaño preferido de botones
        btnSalir.setPreferredSize(new Dimension(100, 30));
        btnAsignar.setPreferredSize(new Dimension(100, 30));

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
        panelBotones.add(btnAsignar, gbc);

        // Agregar paneles al principal
        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Configurar action listeners
        btnBuscar.addActionListener(this::buscarEmpleado);
        btnAsignar.addActionListener(evt -> {
            try {
                String idComandaS = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el ID de la comanda que asignará. Si no lo conoce, diríjase desde el menú principal\na la barra de opiones, Comanda, y Ver Comandas o Ver Comandas Por Empleado",
                        "Ingresar ID de la comanda",
                        JOptionPane.QUESTION_MESSAGE
                );
                int idComanda = Integer.parseInt(idComandaS);
                asignarComanda(idComanda);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e);
            }
        });

        btnSalir.addActionListener(e -> dispose());

        setContentPane(panelPrincipal);
    }

    private void buscarEmpleado(ActionEvent evt) {
        try {
            ESerGen serGenerales = eSerGenServicio.searchElementoByNoDocumento(this.txtBuscar.getText());
            System.out.println("Este es el id: " + this.txtBuscar.getText());
            if (serGenerales == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ningún empleado con documento " + txtBuscar.getText() + "\nAsegúrese de ingresar un número de documento válido y existente.");
                //limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Empleado encontrado. Presione 'Ok' o cierre esta ventana y haga clic en el botón Asignar Comanda para asignar una comanda\nAl empleado " + serGenerales.getNombre() + " con " + serGenerales.getTipoDocumento() + " No. " + serGenerales.getNoDoumento());
                this.txtID.setText(this.txtBuscar.getText());
                this.txtID.setEditable(false);
                mostrar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error. Asegúrese de ingresar un ID de comanda válido y existente:\n" + e.getMessage());
        }
    }

    private void asignarComanda(int idComanda) {
        try {
            ESerGen serGenerales = eSerGenServicio.searchElementoByNoDocumento(this.txtBuscar.getText());
            String idEserGen = this.txtBuscar.getText();

            if (this.txtID.getText().isBlank()) {
                throw new IllegalArgumentException("""
                                                   No es posible asignar una comanda sin primero buscar un empleado.
                                                   Primero busque un empleado por su número de documento y luego
                                                   Asígnele a ese empleado la comanda.
                                                   """);
            }
            
            eSerGenServicio.asignarComanda(idEserGen, idComanda);
            JOptionPane.showMessageDialog(this, "Comanda exitosamente asignada al empleado " + serGenerales.getNombre() + " con " +serGenerales.getTipoDocumento() +  " No. " + idEserGen);
            limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void limpiar() {
        txtBuscar.setText("");
        txtID.setText("");
        panelDatos.setVisible(false);
    }

    private void mostrar() {
        panelDatos.setVisible(true);
    }

}
