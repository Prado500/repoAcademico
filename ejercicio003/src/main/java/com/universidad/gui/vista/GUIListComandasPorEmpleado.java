package com.universidad.gui.vista;

import com.universidad.gui.modelo.implementacion.Comanda;
import com.universidad.gui.modelo.implementacion.ESerGen;
import com.universidad.gui.servicio.IObservador;
import com.universidad.gui.servicio.implementacion.ESerGenServicio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUIListComandasPorEmpleado extends JFrame implements IObservador {

    private final ESerGenServicio eSerGenServicio;
    private JButton btnBuscar, jButton1Salir;
    private JCheckBox chkRefrescable;
    private JPanel jPanelBusqueda;
    private JPanel jPanelListComandas;
    private JTable jTableListComandas;
    private JTextField txtBuscar;

    public GUIListComandasPorEmpleado(ESerGenServicio eSerGenServicio) {
        this.eSerGenServicio = eSerGenServicio;
        initializeComponents();
        setupLayout();
        setupListeners();
        setLocationRelativeTo(null);
        jTableListComandas.setEnabled(false);
        this.eSerGenServicio.agregarObservador(this);
    }

    private void initializeComponents() {
        // Inicializar componentes
        btnBuscar = new JButton("Buscar");
        jButton1Salir = new JButton("Salir");
        chkRefrescable = new JCheckBox();
        jPanelListComandas = new JPanel();
        jTableListComandas = new JTable();
        txtBuscar = new JTextField(15);
        //Panel Norte
        jPanelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanelBusqueda.add(new JLabel("Buscar por # documento:"));
        jPanelBusqueda.add(txtBuscar);
        jPanelBusqueda.add(btnBuscar);
        jPanelBusqueda.add(new JLabel("Auto-Refrescable: "));
        jPanelBusqueda.add(chkRefrescable);

        // Configurar tabla
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id", "ID emp.", "Fecha Caducidad", "Descripción", "Principio", "Proteina", "Sopa"}
        );
        jTableListComandas.setModel(model);

        // Configurar frame
        setTitle("Lista de Comandas Por Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(495, 425));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                eSerGenServicio.eliminarObservador(GUIListComandasPorEmpleado.this);
            }
        });
    }

    private void setupLayout() {
        // Layout principal
        setLayout(new BorderLayout());

        // Panel de la tabla
        jPanelListComandas.setLayout(new BorderLayout());
        jPanelListComandas.add(new JScrollPane(jTableListComandas), BorderLayout.CENTER);
        jPanelListComandas.setVisible(false);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        jButton1Salir.setPreferredSize(new Dimension(100, 30));

        // Botón Salir (izquierda)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 38, 10, 0);
        panelBotones.add(jButton1Salir, gbc);

        // Agregar componentes al frame
        add(jPanelBusqueda, BorderLayout.NORTH);
        add(jPanelListComandas, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        jButton1Salir.addActionListener(e -> {
            this.eSerGenServicio.eliminarObservador(this);
            eSerGenServicio.mostrarObservadores();
            dispose();
        });
        btnBuscar.addActionListener(this::buscarEmpleado);

    }

    private void buscarEmpleado(ActionEvent evt) {

        try {

            ESerGen serGenerales = eSerGenServicio.buscarESerGenPorNoDocumento(this.txtBuscar.getText());
            this.cargarTabla(serGenerales.getNoDoumento());

        } catch (Exception e) {
            limpiar();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void cargarTabla(String idESerGen) {
        try {
            List<Comanda> comandasList = eSerGenServicio.mostrarComandaPorESerGen(idESerGen);

                jPanelListComandas.setVisible(true);
                DefaultTableModel model = (DefaultTableModel) jTableListComandas.getModel();
                model.setRowCount(0);
                for (Comanda comanda : comandasList) {
                    Object[] fila = {
                            comanda.getId(),
                            idESerGen,
                            comanda.getFechaCaducidad(),
                            comanda.getDescripcion(),
                            comanda.getPrincipio(),
                            comanda.getProteina(),
                            comanda.getSopa()
                    };
                    model.addRow(fila);
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar comandas: " + e.getMessage());
        }

    }

    private void limpiar() {
        txtBuscar.setText("");

    }

    @Override
    public void actualizar() {
        if (this.chkRefrescable.isSelected()) {
            ESerGen serGenerales = eSerGenServicio.buscarESerGenPorNoDocumento(this.txtBuscar.getText());
            this.cargarTabla(serGenerales.getNoDoumento());
        }
    }
}
