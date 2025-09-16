package com.universidad.gui.vista;

import com.universidad.gui.modelo.implementacion.Administrativo;
import com.universidad.gui.modelo.implementacion.ESerGen;
import com.universidad.gui.servicio.implementacion.EmpleadoServicio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUIListESerGen extends JFrame {

    private EmpleadoServicio<ESerGen> empleadoServicioESerGen;
    private JButton jButton1Salir;
    private JButton jButton2Mostrar;
    private JPanel jPanelListEmpleado;
    private JTable jTableListEmpleados;

    public GUIListESerGen(EmpleadoServicio<ESerGen> empleadoServicioESerGen) {
        this.empleadoServicioESerGen = empleadoServicioESerGen;
        initializeComponents();
        setupLayout();
        setupListeners();
        setLocationRelativeTo(null);
        jTableListEmpleados.setEnabled(false);
    }

    private void initializeComponents() {
        // Inicializar componentes
        jButton1Salir = new JButton("Salir");
        jButton2Mostrar = new JButton("Mostrar");
        jPanelListEmpleado = new JPanel();
        jTableListEmpleados = new JTable();

        // Configurar tabla
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Documento", "Tipo", "Nombre", "Salario", "Estatus", "Cert. Alturas"}
        );
        jTableListEmpleados.setModel(model);

        // Configurar frame
        setTitle("Lista de Empleados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(495, 425));
    }

    private void setupLayout() {
        // Layout principal
        setLayout(new BorderLayout());

        // Panel de la tabla
        jPanelListEmpleado.setLayout(new BorderLayout());
        jPanelListEmpleado.add(new JScrollPane(jTableListEmpleados), BorderLayout.CENTER);
        jPanelListEmpleado.setVisible(false);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        jButton1Salir.setPreferredSize(new Dimension(100, 30));
        jButton2Mostrar.setPreferredSize(new Dimension(100, 30));

        // Botón Salir (izquierda)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 38, 10, 0);
        panelBotones.add(jButton1Salir, gbc);

        // Botón Mostrar (derecha)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 38);
        panelBotones.add(jButton2Mostrar, gbc);

        // Agregar componentes al frame
        add(jPanelListEmpleado, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        jButton1Salir.addActionListener(e -> dispose());

        jButton2Mostrar.addActionListener(e -> {
            try {
                List<ESerGen> ESerGenList = empleadoServicioESerGen.mostrar();
                if (ESerGenList != null && !ESerGenList.isEmpty()) {
                    jPanelListEmpleado.setVisible(true);
                    DecimalFormat formato = new DecimalFormat("#,##0.00");
                    DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                    simbolos.setGroupingSeparator('.');
                    simbolos.setDecimalSeparator(',');
                    formato.setDecimalFormatSymbols(simbolos);
                    DefaultTableModel model = (DefaultTableModel) jTableListEmpleados.getModel();
                    model.setRowCount(0);
                    String alturas;

                    for (ESerGen serGenerales : ESerGenList) {
                        if (serGenerales.getCerAlturas()) {
                            alturas = "SI";
                        }
                        Object[] fila = {
                            
                            serGenerales.getNoDoumento(),
                            serGenerales.getTipoDocumento(),
                            serGenerales.getNombre(),
                            "$ " + formato.format(serGenerales.getSalarioBase()),
                            serGenerales.getEstatus(),
                            this.hasAlturas(serGenerales.getCerAlturas())
                        };
                        model.addRow(fila);
                        alturas = "NO";
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "La lista está vacía");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al mostrar empleados: " + ex.getMessage());
            }
        });
    }

    private String hasAlturas(boolean alturas) {

        String alturasRetorno = "NO";

        if (alturas) {
            alturasRetorno = "SI";
        }
        return alturasRetorno;
    }
}
