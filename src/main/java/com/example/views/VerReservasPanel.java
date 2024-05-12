package com.example.views;

import com.example.GestionRestaurante;
import com.example.controller.ReservaController;
import com.example.model.Reserva;
import com.example.views.dialogs.reservas.EditDialog;
import com.example.views.dialogs.reservas.InsertDialog;
import com.example.utils.Constants;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.table.TableRowSorter;

public class VerReservasPanel extends JPanel implements ActionListener {

    private final GestionRestaurante gestionRestaurante;
    private JTable table;
    private JLabel labelTitulo;

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
        this.gestionRestaurante = gestionRestaurante;
        gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
        gestionRestaurante.setSize(1000, 500);
        gestionRestaurante.setLocationRelativeTo(null);

    	setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        setLayout(new BorderLayout(0, 0));
    	setBorder(new EmptyBorder(7, 5, 5, 5));

        configureTable();

        //Título
        labelTitulo = new JLabel("Gestión de Reservas"); // titulo de la pantalla
        labelTitulo.setBorder(new MatteBorder(0, 0, 2, 0, Constants.COLOR_NEGRO));
        labelTitulo.setFocusCycleRoot(true);
        labelTitulo.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        labelTitulo.setFont(new Font(Constants.YU_GOTHIC_UI_SEMIBOLD, Font.PLAIN, 22));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelTitulo, BorderLayout.NORTH);

        // Panel principal para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS)); // Usamos BoxLayout en el eje X
        add(panelBotones, BorderLayout.SOUTH);

        // Panel para el botón "Volver al menú"
        JPanel panelVolver = new JPanel();
        panelVolver.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        panelVolver.setLayout(new FlowLayout(FlowLayout.LEFT));

        btnVolverAlMenu = new JButton("Volver al menú");
        btnVolverAlMenu.setBackground(Constants.COLOR_BOTON_VOLVER);
        btnVolverAlMenu.setBorderPainted(false);
        btnVolverAlMenu.setBorder(UIManager.getBorder("Button.border"));
        btnVolverAlMenu.setForeground(Constants.COLOR_NEGRO);
        btnVolverAlMenu.setFont(new Font(Constants.VERDANA, btnVolverAlMenu.getFont().getStyle() | Font.BOLD, 12));
        btnVolverAlMenu.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVolverAlMenu.addActionListener(this);
        panelVolver.add(btnVolverAlMenu);

        // Panel para los otros botones con alineación a la derecha
        JPanel panelAcciones = new JPanel();
        panelAcciones.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        panelAcciones.setLayout(new FlowLayout(FlowLayout.RIGHT));

        btnInsertar = new JButton("Agregar");
        btnInsertar.setForeground(Constants.COLOR_PRINCIPAL_AMARILLO);
        btnInsertar.setFont(new Font(Constants.VERDANA, btnInsertar.getFont().getStyle() | Font.BOLD, btnInsertar.getFont().getSize()));
        btnInsertar.setBackground(Constants.COLOR_NEGRO);
        btnInsertar.setBorderPainted(false);
        btnInsertar.addActionListener(this);
        panelAcciones.add(btnInsertar);

        btnEditar = new JButton("Editar");
        btnEditar.setForeground(Constants.COLOR_PRINCIPAL_AMARILLO);
        btnEditar.setFont(new Font(Constants.VERDANA, btnEditar.getFont().getStyle() | Font.BOLD, btnEditar.getFont().getSize()));
        btnEditar.setBackground(Constants.COLOR_NEGRO);
        btnEditar.setBorderPainted(false);
        btnEditar.addActionListener(this);
        panelAcciones.add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Constants.COLOR_PRINCIPAL_AMARILLO);
        btnEliminar.setFont(new Font(Constants.VERDANA, btnEliminar.getFont().getStyle() | Font.BOLD, btnEliminar.getFont().getSize()));
        btnEliminar.setBorderPainted(false);
        btnEliminar.setBackground(Constants.COLOR_NEGRO);
        btnEliminar.addActionListener(this);
        panelAcciones.add(btnEliminar);

        // Añadir los dos paneles al panel principal de botones
        panelBotones.add(panelVolver);
        panelBotones.add(panelAcciones);
    }

    private void configureTable() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new LineBorder(Constants.COLOR_NEGRO, 3));
        scrollPane.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        scrollPane.setViewportView(table);

        tableModel.addColumn("Id");
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Numero mesa");
        tableModel.addColumn("Dia (AAAA-MM-DD)");
        tableModel.addColumn("Horario");
        tableModel.addColumn("Cantidad pers.");
        tableModel.addColumn("Empleado");
        table.setModel(tableModel);

        // Permitir la ordenación
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //centra el contenido de las columnas de la tabla
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
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
        String[] data = new String[7];

        data[0] = reserva.getId().toString();
        data[1] = reserva.getCliente().getCorreo();
        data[2] = reserva.getNumeroMesa().toString();
        data[3] = reserva.getDia().toString();
        data[4] = reserva.getHorario().toString();
        data[5] = reserva.getNumeroPersonas().toString();
        data[6] = reserva.getEmpleado().getUsuario();

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
        String empleado = table.getValueAt(selectedRow, 6).toString();

        // Crear y mostrar el diálogo de edición
        EditDialog dialog = new EditDialog(id, usuarioReserva, numeroMesa, dia, horario, numeroPersonas, empleado);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        // Actualizar los valores en la tabla si se guarda el diálogo
        if (dialog.isSave()) {
            if (dialog.isDeleted()) {
                tableModel.removeRow(selectedRow);
            } else {
                table.setValueAt(dialog.getId(), selectedRow, 0);
                table.setValueAt(dialog.getCorreoReserva(), selectedRow, 1);
                table.setValueAt(dialog.getSpinnerNumeroMesa(), selectedRow, 2);
                table.setValueAt(dialog.getTfDia(), selectedRow, 3);
                table.setValueAt(dialog.getHorario(), selectedRow, 4);
                table.setValueAt(dialog.getSpinnerNumeroPersonas(), selectedRow, 5);
            }
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
