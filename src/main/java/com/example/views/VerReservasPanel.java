package com.example.views;

import com.example.GestionRestaurante;
import com.example.controller.ReservaController;
import com.example.model.Reserva;
import com.example.views.dialogs.EditDialog;
import com.example.views.dialogs.InsertDialog;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VerReservasPanel extends JPanel implements ActionListener {

    private final GestionRestaurante gestionRestaurante;
    private JTable table;
    private final DefaultTableModel tableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Esto hace que todas las celdas sean no editables
        }
    };
    private static final ReservaController reservaController = new ReservaController();
    private JButton btnInsertar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnVolverAlMenu;

    public VerReservasPanel(GestionRestaurante gestionRestaurante) {
    	setBackground(new Color(240, 197, 23));
        setLayout(new BorderLayout(0, 0));
    	setBorder(new EmptyBorder(5, 5, 5, 5));
        this.gestionRestaurante = gestionRestaurante;

        configureTable();

        gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);

        // Panel principal para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(240, 197, 23));
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS)); // Usamos BoxLayout en el eje X
        add(panelBotones, BorderLayout.SOUTH);

        // Panel para el botón "Volver al menú"
        JPanel panelVolver = new JPanel();
        panelVolver.setBackground(new Color(240, 197, 23));
        panelVolver.setLayout(new FlowLayout(FlowLayout.LEFT));

        btnVolverAlMenu = new JButton("Volver al menú");
        btnVolverAlMenu.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVolverAlMenu.addActionListener(this);
        panelVolver.add(btnVolverAlMenu);

        // Panel para los otros botones con alineación a la derecha
        JPanel panelAcciones = new JPanel();
        panelAcciones.setBackground(new Color(240, 197, 23));
        panelAcciones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnInsertar = new JButton("Insertar");

        btnInsertar = new JButton("Insertar");
        btnInsertar.addActionListener(this);
        panelAcciones.add(btnInsertar);

        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(this);
        panelAcciones.add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        panelAcciones.add(btnEliminar);

        // Añadir los dos paneles al panel principal de botones
        panelBotones.add(panelVolver);
        panelBotones.add(panelAcciones);
    }

    private void configureTable() {
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setBackground(new Color(240, 197, 23));
        scrollPane.setViewportView(table);

        tableModel.addColumn("Id");
        tableModel.addColumn("Usuario reserva");
        tableModel.addColumn("Numero mesa");
        tableModel.addColumn("Dia (AAAA-MM-DD)");
        tableModel.addColumn("Horario");
        tableModel.addColumn("Cantidad pers.");
        table.setModel(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //centra el contenido de las columnas de la tabla
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        reservaController.getAllReservas().forEach(reserva -> tableModel.addRow(reservaToTableModel(reserva))); //inserta las reservas a la tabla
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnEditar)) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // Si hay una fila seleccionada
                editarReserva(selectedRow);
            }
        } else if (e.getSource().equals(btnInsertar)) {
            insertarReserva();
        } else if (e.getSource().equals(btnEliminar)) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                eliminarReserva(selectedRow);
            }
        } else if (e.getSource().equals(btnVolverAlMenu)) {
            this.setVisible(false);
            new MainMenu(gestionRestaurante);
        }
    }


    private String[] reservaToTableModel(Reserva reserva) {
        String[] data = new String[6];

        data[0] = reserva.getId().toString();
        data[1] = reserva.getUsuarioReserva().getCorreo();
        data[2] = reserva.getNumeroMesa().toString();
        data[3] = reserva.getDia().toString();
        data[4] = reserva.getHorario().toString();
        data[5] = reserva.getNumeroPersonas().toString();

        return data;
    }

    private void editarReserva(int selectedRow) {
        // Obtener los valores de la fila seleccionada
        String id = table.getValueAt(selectedRow, 0).toString();
        String usuarioReserva = table.getValueAt(selectedRow, 1).toString();
        String numeroMesa = table.getValueAt(selectedRow, 2).toString();
        String dia = table.getValueAt(selectedRow, 3).toString();
        String horario = table.getValueAt(selectedRow, 4).toString();
        String numeroPersonas = table.getValueAt(selectedRow, 5).toString();

        // Crear y mostrar el diálogo de edición
        EditDialog dialog = new EditDialog(id, usuarioReserva, numeroMesa, dia, horario, numeroPersonas);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        // Actualizar los valores en la tabla si se guarda el diálogo
        if (dialog.isSave()) {
            table.setValueAt(dialog.getId(), selectedRow, 0);
            table.setValueAt(dialog.getCorreoReserva(), selectedRow, 1);
            table.setValueAt(dialog.getSpinnerNumeroMesa(), selectedRow, 2);
            table.setValueAt(dialog.getTfDia(), selectedRow, 3);
            table.setValueAt(dialog.getHorario(), selectedRow, 4);
            table.setValueAt(dialog.getSpinnerNumeroPersonas(), selectedRow, 5);
        }
    }

    private void insertarReserva() {
        InsertDialog dialog = new InsertDialog();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        if (dialog.isSave()) {
            tableModel.addRow(reservaToTableModel(dialog.getNuevaReserva()));
        }
    }

    private void eliminarReserva(int selectedRow) {
        Integer id = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Quieres eliminar la reserva con ID: " + id + "?");

        if (confirm == JOptionPane.OK_OPTION) {
            reservaController.eliminarReserva(id);
            tableModel.removeRow(selectedRow);
        }
    }
}
