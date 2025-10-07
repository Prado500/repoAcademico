package com.universidad.gui.vista;

import com.universidad.gui.modelo.implementacion.Comanda;
import com.universidad.gui.servicio.IObservador;
import com.universidad.gui.servicio.implementacion.ESerGenServicio;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.table.DefaultTableModel;

public class GUIListComandas extends JFrame implements IObservador {

    private final ESerGenServicio eSerGenServicio;
    private JButton jButton1Salir;
    private JButton jButton2Mostrar;
    private JCheckBox chkRefrescable;
    private JPanel jPanelCheckBox;
    private JPanel jPanelListComandas;
    private JTable jTableListComandas;

    public GUIListComandas(ESerGenServicio eSerGenServicio) {
        this.eSerGenServicio = eSerGenServicio;
        initializeComponents();
        setupLayout();
        setupListeners();
        setLocationRelativeTo(null);
        jTableListComandas.setEnabled(false);
        eSerGenServicio.agregarObservador(this);
    }

    private void initializeComponents() {
        // Inicializar componentes
        jButton1Salir = new JButton("Salir");
        jButton2Mostrar = new JButton("Mostrar");
        chkRefrescable = new JCheckBox();
        jPanelListComandas = new JPanel();
        jTableListComandas = new JTable();
        
        //Panel Norte
        jPanelCheckBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanelCheckBox.add(new JLabel("Auto-Refrescable: "));
        jPanelCheckBox.add(chkRefrescable);

        // Configurar tabla
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id", "ID emp.", "Fecha Caducidad", "Descripción", "Principio", "Proteina", "Sopa"}
        );
        jTableListComandas.setModel(model);

        // Configurar frame
        setTitle("Lista de Comandas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(495, 425));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                eSerGenServicio.eliminarObservador(GUIListComandas.this);
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
        add(jPanelListComandas, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        jButton1Salir.addActionListener(e -> {
            this.eSerGenServicio.eliminarObservador(this);
            eSerGenServicio.mostrarObservadores();
            dispose();
        });

        jButton2Mostrar.addActionListener(e -> {
            this.cargarTabla();
        });
    }

    private void cargarTabla() {

        try {

            List<Comanda> comandasList = eSerGenServicio.mostrarComandas();
                jPanelListComandas.setVisible(true);
                DefaultTableModel model = (DefaultTableModel) jTableListComandas.getModel();
                model.setRowCount(0);
                String idESerGen;
                for (Comanda comanda : comandasList) {
                    if (comanda.getEserGen() == null) {
                        idESerGen = "Sin Asignar";
                    }else{
                        idESerGen = comanda.getEserGen().getNoDoumento();
                    }
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
        } catch (Exception ex) {
            jPanelListComandas.setVisible(false);
            JOptionPane.showMessageDialog(this, "Error al mostrar comandas: " + ex.getMessage());
        }

    }


    @Override
    public void actualizar() {
       if (this.chkRefrescable.isSelected())   
        this.cargarTabla();
    }
}
