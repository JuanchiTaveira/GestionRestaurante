package com.example.views;

import com.example.GestionRestaurante;
import com.example.controller.EmpleadoController;
import com.example.model.Empleado;
import com.example.views.dialogs.empleados.EditEmpleadoDialog;
import com.example.views.dialogs.empleados.InsertEmpleadoDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class GestionarEmpleados extends JPanel implements ActionListener {
        private final GestionRestaurante gestionRestaurante;
        private JTable table;
        private final DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto hace que todas las celdas sean no editables
            }
        };
        private static final EmpleadoController empleadoController = new EmpleadoController();
        private JButton btnAgregarEmpleado;
        private JButton btnEditar;
        private JButton btnEliminar;
        private JButton btnVolverAlMenu;

    public GestionarEmpleados(GestionRestaurante gestionRestaurante) {
    	setBackground(new Color(240, 197, 23));
    	setForeground(new Color(240, 197, 23));
        this.gestionRestaurante = gestionRestaurante;
        gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
        gestionRestaurante.setSize(800, 500);
        gestionRestaurante.setLocationRelativeTo(null);

        setLayout(new BorderLayout(0, 0));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        configureTable();


        // Panel principal para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS)); // Usamos BoxLayout en el eje X
        add(panelBotones, BorderLayout.SOUTH);

        // Panel para el botón "Volver al menú"
        JPanel panelVolver = new JPanel();
        panelVolver.setBackground(new Color(240, 197, 23));
        panelVolver.setLayout(new FlowLayout(FlowLayout.LEFT));

        btnVolverAlMenu = new JButton("Volver al menú");
        btnVolverAlMenu.setForeground(new Color(240, 197, 23));
        btnVolverAlMenu.setFont(new Font("Verdana", btnVolverAlMenu.getFont().getStyle() | Font.BOLD, 11));
        btnVolverAlMenu.setBackground(new Color(0, 0, 0));
        btnVolverAlMenu.setBorderPainted(false);
        btnVolverAlMenu.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVolverAlMenu.addActionListener(this);
        panelVolver.add(btnVolverAlMenu);

        // Panel para los otros botones con alineación a la derecha
        JPanel panelAcciones = new JPanel();
        panelAcciones.setBackground(new Color(240, 197, 23));
        panelAcciones.setLayout(new FlowLayout(FlowLayout.RIGHT));

        btnAgregarEmpleado = new JButton("Agregar");
        btnAgregarEmpleado.setForeground(new Color(240, 197, 23));
        btnAgregarEmpleado.setFont(new Font("Verdana", btnAgregarEmpleado.getFont().getStyle() | Font.BOLD, btnAgregarEmpleado.getFont().getSize()));
        btnAgregarEmpleado.setBackground(new Color(0, 0, 0));
        btnAgregarEmpleado.setBorderPainted(false);
        btnAgregarEmpleado.addActionListener(this);
        panelAcciones.add(btnAgregarEmpleado);

        btnEditar = new JButton("Editar");
        btnEditar.setForeground(new Color(240, 197, 23));
        btnEditar.setFont(new Font("Verdana", btnEditar.getFont().getStyle() | Font.BOLD, btnEditar.getFont().getSize()));
        btnEditar.setBackground(new Color(0, 0, 0));
        btnEditar.setBorderPainted(false);
        btnEditar.addActionListener(this);
        panelAcciones.add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(new Color(240, 197, 23));
        btnEliminar.setFont(new Font("Verdana", btnEliminar.getFont().getStyle() | Font.BOLD, btnEliminar.getFont().getSize()));
        btnEliminar.setBorderPainted(false);
        btnEliminar.setBackground(new Color(0, 0, 0));
        btnEliminar.addActionListener(this);
        panelAcciones.add(btnEliminar);

        // Añadir los dos paneles al panel principal de botones
        panelBotones.add(panelVolver);
        panelBotones.add(panelAcciones);
    }

    private void configureTable() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        scrollPane.setBackground(new Color(240, 197, 23));
        add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        tableModel.addColumn("Id");
        tableModel.addColumn("Usuario");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido");
        tableModel.addColumn("DNI");
        tableModel.addColumn("Admin");
        table.setModel(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //centra el contenido de las columnas de la tabla
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        empleadoController.getAllEmpleados().forEach(reserva -> tableModel.addRow(EmpleadoToTableModel(reserva))); //inserta las reservas a la tabla
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnEditar)) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // Si hay una fila seleccionada
                editarEmpleado(selectedRow);
            }
        } else if (e.getSource().equals(btnAgregarEmpleado)) {
            insertarEmpleado();
        } else if (e.getSource().equals(btnEliminar)) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                eliminarEmpleado(selectedRow);
            }
        } else if (e.getSource().equals(btnVolverAlMenu)) {
            this.setVisible(false);
            new MainMenu(gestionRestaurante);
        }
    }


    private String[] EmpleadoToTableModel(Empleado empleado) {
        String[] data = new String[6];

        data[0] = empleado.getId().toString();
        data[1] = empleado.getUsuario();
        data[2] = empleado.getNombre();
        data[3] = empleado.getApellido();
        data[4] = empleado.getDni();
        data[5] = empleado.isAdmin() ? "SI" : "NO";

        return data;
    }

    private void editarEmpleado(int selectedRow) {
        // Obtener los valores de la fila seleccionada
        String id = table.getValueAt(selectedRow, 0).toString();
        String usuario = table.getValueAt(selectedRow, 1).toString();
        String nombre = table.getValueAt(selectedRow, 2).toString();
        String apellido = table.getValueAt(selectedRow, 3).toString();
        String dni = table.getValueAt(selectedRow, 4).toString();
        String admin = table.getValueAt(selectedRow, 5).toString();

        // Crear y mostrar el diálogo de edición
        EditEmpleadoDialog dialog = new EditEmpleadoDialog(id, usuario, nombre, apellido, dni, admin);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        // Actualizar los valores en la tabla si se guarda el diálogo
        if (dialog.isSave()) {
            table.setValueAt(dialog.getId(), selectedRow, 0);
            table.setValueAt(dialog.getUsuario(), selectedRow, 1);
            table.setValueAt(dialog.getNombre(), selectedRow, 2);
            table.setValueAt(dialog.getApellido(), selectedRow, 3);
            table.setValueAt(dialog.getDni(), selectedRow, 4);
            table.setValueAt(dialog.getAdmin(), selectedRow, 5);
        }
    }

    private void insertarEmpleado() {
        InsertEmpleadoDialog dialog = new InsertEmpleadoDialog();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        if (dialog.isSave()) {
            tableModel.addRow(EmpleadoToTableModel(dialog.getNuevoEmpleado()));
        }
    }

    private void eliminarEmpleado(int selectedRow) {
        Integer id = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Quieres eliminar al empleado con ID: " + id + "?");

        if (confirm == JOptionPane.OK_OPTION) {

            Boolean isAdmin = table.getValueAt(selectedRow, 5).toString().equals("SI");

            if (EmpleadoController.authUser.getId() == id) {
                JOptionPane.showMessageDialog(this, "ERROR: No puedes eliminarte a ti mismo.");
                return;
            }

            if (isAdmin && empleadoController.getEmpleadosAdminCount() < 2) {
                JOptionPane.showMessageDialog(this, "ERROR: No puede revocar permisos de administrador, ya que éste usuario es el único administrador.");
                return;
            }

            empleadoController.eliminarEmpleado(id);
            tableModel.removeRow(selectedRow);
        }
    }
}
