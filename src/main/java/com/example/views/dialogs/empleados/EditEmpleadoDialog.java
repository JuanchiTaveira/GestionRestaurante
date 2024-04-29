package com.example.views.dialogs.empleados;

import com.example.controller.EmpleadoController;
import com.example.model.Empleado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;

public class EditEmpleadoDialog extends JDialog {
    private static final EmpleadoController empleadoController = new EmpleadoController();
    private final JLabel labelId, labelUsuario;
    private boolean save;
    private JTextField tfNombre;
    private JTextField tfApellido;
    private JTextField tfDni;
    private JComboBox adminComboBox;

    public EditEmpleadoDialog(String id, String usuario, String nombre, String apellido, String dni, String admin) {
        setTitle("Editar Reserva");
        setSize(400, 350);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        formPanel.add(new JLabel("ID:"));
        labelId = new JLabel(id);
        labelId.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(labelId);

        formPanel.add(new JLabel("Usuario:"));
        labelUsuario = new JLabel(usuario);
        labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(labelUsuario);

        formPanel.add(new JLabel("Nombre:"));
        tfNombre = new JTextField(nombre);
        tfNombre.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfNombre);
        tfNombre.setColumns(10);

        formPanel.add(new JLabel("Apellido:"));
        tfApellido = new JTextField(apellido);
        tfApellido.setHorizontalAlignment(SwingConstants.CENTER);
        tfApellido.setColumns(10);
        formPanel.add(tfApellido);

        formPanel.add(new JLabel("DNI:"));
        tfDni = new JTextField(dni);
        tfDni.setHorizontalAlignment(SwingConstants.CENTER);
        tfDni.setColumns(10);
        formPanel.add(tfDni);

        formPanel.add(new JLabel("Admin:"));
        adminComboBox = new JComboBox();
        adminComboBox.setModel(new DefaultComboBoxModel(new String[] {"SI", "NO"}));
        if (admin.equals("SI")) {
            adminComboBox.setSelectedIndex(0);
        } else {
            adminComboBox.setSelectedIndex(1);
        }
        BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
        basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        adminComboBox.setRenderer(basicComboBoxRenderer);
        formPanel.add(adminComboBox);

        //Panel del boton de guardar y eliminar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            // Guardar los valores y cerrar el diálogo
            int confirm = JOptionPane.showConfirmDialog(this, "Quieres confirmar los cambios sobre el empleado con ID: " + id + "?");

            if (confirm == JOptionPane.OK_OPTION) {
                Empleado oldEmpleado = empleadoController.getEmpleadoById(id);

                Empleado empleadoActualizado = Empleado.builder()
                        .id(Integer.valueOf(id))
                        .usuario(usuario)
                        .password(oldEmpleado.getPassword())
                        .nombre(tfNombre.getText())
                        .apellido(tfApellido.getText())
                        .dni(tfDni.getText())
                        .admin(adminComboBox.getSelectedItem().toString() == "SI")
                        .build();

                if (oldEmpleado.equals(empleadoActualizado)) {
                    dispose();
                    return;
                }

                Boolean success = empleadoController.editarEmpleado(empleadoActualizado);

                if (!success) {
                    JOptionPane.showMessageDialog(this, "ERROR al actualizar empleado.");
                    return;
                }

                save = true;
            }

            dispose();
        });
        buttonPanel.add(saveButton);

        panel.add(formPanel, BorderLayout.CENTER);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    public String getId() {
        return labelId.getText();
    }

    public String getUsuario() {
        return labelUsuario.getText();
    }


    public String getNombre() {
        return tfNombre.getText();
    }

    public String getApellido() {
        return tfApellido.getText();
    }

    public String getDni() {
        return tfDni.getText();
    }

    public String getAdmin() {
        return adminComboBox.getSelectedItem().toString();
    }

    public boolean isSave() {
        return save;
    }
}
