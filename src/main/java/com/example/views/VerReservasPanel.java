package com.example.views;

import com.example.GestionRestaurante;
import com.example.controller.ReservaController;
import com.example.model.Reserva;
import com.example.views.dialogs.EditDialog;
import com.example.views.dialogs.InsertDialog;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerReservasPanel extends JPanel implements ActionListener {

    private GestionRestaurante gestionRestaurante;
    private JTable table;
    private DefaultTableModel tableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Esto hace que todas las celdas sean no editables
        }
    };
    private ReservaController reservaController = new ReservaController();
    private JButton btnInsertar;
    private JButton btnEditar;
    private JButton btnEliminar;

    public VerReservasPanel(GestionRestaurante gestionRestaurante) {
        setLayout(new BorderLayout(0, 0));
    	setBorder(new EmptyBorder(5, 5, 5, 5));
        this.gestionRestaurante = gestionRestaurante;

        initialize();

        gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);
        
        btnInsertar = new JButton("Insertar");
        btnInsertar.addActionListener(this);
        panel.add(btnInsertar);
        
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(this);
        panel.add(btnEditar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        panel.add(btnEliminar);
    }

    private void initialize() {

        configureTable();

    }

    private void configureTable() {
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        tableModel.addColumn("Id");
        tableModel.addColumn("Usuario reserva");
        tableModel.addColumn("Numero mesa");
        tableModel.addColumn("Dia");
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
                    table.setValueAt(dialog.getUsuarioReserva(), selectedRow, 1);
                    table.setValueAt(dialog.getSpinnerNumeroMesa(), selectedRow, 2);
                    table.setValueAt(dialog.getTfDia(), selectedRow, 3);
                    table.setValueAt(dialog.getHorario(), selectedRow, 4);
                    table.setValueAt(dialog.getSpinnerNumeroPersonas(), selectedRow, 5);
                }
            }
        } else if (e.getSource().equals(btnInsertar)) {
            InsertDialog dialog = new InsertDialog();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
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
}
