package com.universidad.gui.vista;

import com.universidad.gui.modelo.implementacion.Administrativo;
import com.universidad.gui.servicio.IObservador;
import com.universidad.gui.servicio.implementacion.AdministrativoServicio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUIListAdministrativos extends JFrame implements IObservador {

    private final AdministrativoServicio administrativoServicio;
    private JButton jButton1Salir;
    private JButton jButton2Mostrar;
    private JCheckBox chkRefrescable;
    private JPanel jPanelCheckBox;
    private JPanel jPanelListEmpleado;
    private JTable jTableListEmpleados;

    public GUIListAdministrativos(AdministrativoServicio administrativoServicio) {
        this.administrativoServicio = administrativoServicio;
        initializeComponents();
        setupLayout();
        setupListeners();
        setLocationRelativeTo(null);
        jTableListEmpleados.setEnabled(false);
        administrativoServicio.agregarObservador(this);
    }

    private void initializeComponents() {
        // Inicializar componentes
        jButton1Salir = new JButton("Salir");
        jButton2Mostrar = new JButton("Mostrar");
        chkRefrescable = new JCheckBox();
        jPanelListEmpleado = new JPanel();
        jTableListEmpleados = new JTable();


        // Panel Norte
        jPanelCheckBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanelCheckBox.add(new JLabel("Auto-Refrescable: "));
        jPanelCheckBox.add(chkRefrescable);


        // Configurar tabla
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Documento", "Tipo", "Nombre", "Salario", "Estatus", "Escalafón"}
        );
        jTableListEmpleados.setModel(model);

        // Configurar frame
        setTitle("Lista de Administrativos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(495, 425));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                administrativoServicio.eliminarObservador(GUIListAdministrativos.this);
            }
        });

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
        add(jPanelCheckBox, BorderLayout.NORTH);
        add(jPanelListEmpleado, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        jButton1Salir.addActionListener(e -> {
            this.administrativoServicio.eliminarObservador(this);
            administrativoServicio.mostrarObservadores();
            dispose();

        });

        jButton2Mostrar.addActionListener(e -> {
            cargarTabla();
            administrativoServicio.mostrarObservadores();
        });

    }


    private void cargarTabla() {

        try {
            List<Administrativo> administrativos = administrativoServicio.mostrarAdministrativo();
                jPanelListEmpleado.setVisible(true);
                DecimalFormat formato = new DecimalFormat("#,##0.00");
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setGroupingSeparator('.');
                simbolos.setDecimalSeparator(',');
                formato.setDecimalFormatSymbols(simbolos);
                DefaultTableModel model = (DefaultTableModel) jTableListEmpleados.getModel();
                model.setRowCount(0);
                for (Administrativo administrativo : administrativos) {
                    Object[] fila = {
                            administrativo.getNoDoumento(),
                            administrativo.getTipoDocumento(),
                            administrativo.getNombre(),
                            "$ " + formato.format(administrativo.getSalarioBase()),
                            administrativo.getEstatus(),
                            administrativo.getEscalafon()
                    };
                    model.addRow(fila);
                }

        } catch (Exception ex) {
            jPanelListEmpleado.setVisible(false);
            JOptionPane.showMessageDialog(this, "Error al mostrar empleados: " + ex.getMessage());
        }
    }

    @Override
    public void actualizar() {
        if (this.chkRefrescable.isSelected())
            this.cargarTabla();
    }
}
