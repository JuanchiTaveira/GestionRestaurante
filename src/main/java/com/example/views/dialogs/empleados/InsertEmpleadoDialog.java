package com.example.views.dialogs.empleados;

import com.example.controller.EmpleadoController;
import com.example.model.Empleado;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class InsertEmpleadoDialog extends JDialog {
    private static final EmpleadoController empleadoController = new EmpleadoController();
    private boolean save;
    private Empleado nuevoEmpleado;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JComboBox adminComboBox;
    private JTextField tfNombre, tfApellido, tfUsuario, tfDni;

    public InsertEmpleadoDialog() {
        setTitle("Agregar Empleado");
        setSize(400, 350);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        formPanel.add(new JLabel("Usuario:"));
        tfUsuario = new JTextField();
        tfUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfUsuario);
        
        formPanel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(passwordField);
        
        formPanel.add(new JLabel("Repetir contraseña:"));
        repeatPasswordField = new JPasswordField();
        repeatPasswordField.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(repeatPasswordField);

        formPanel.add(new JLabel("Nombre:"));
        tfNombre = new JTextField();
        tfNombre.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfNombre);
        tfNombre.setColumns(10);

        formPanel.add(new JLabel("Apellido:"));
        tfApellido = new JTextField();
        tfApellido.setHorizontalAlignment(SwingConstants.CENTER);
        tfApellido.setColumns(10);
        formPanel.add(tfApellido);

        formPanel.add(new JLabel("DNI:"));
        tfDni = new JTextField();
        tfDni.setHorizontalAlignment(SwingConstants.CENTER);
        tfDni.setColumns(10);
        formPanel.add(tfDni);

        formPanel.add(new JLabel("Admin:"));
        adminComboBox = new JComboBox();
        adminComboBox.setModel(new DefaultComboBoxModel(new String[] {"SI", "NO"}));
        BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
        basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        adminComboBox.setRenderer(basicComboBoxRenderer);
        formPanel.add(adminComboBox);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            String usuario = tfUsuario.getText();
            String password = passwordField.getText();
            String repeatPassword = repeatPasswordField.getText();
            String nombre = tfNombre.getText();
            String apellido = tfApellido.getText();
            String dni = tfDni.getText();
            Boolean admin = adminComboBox.getSelectedItem().toString().equals("SI");

            if (usuario.isBlank() || password.isBlank() || repeatPassword.isBlank() || nombre.isBlank() || apellido.isBlank() || dni.isBlank()) {
                JOptionPane.showMessageDialog(this, "ERROR: Debe completar todos los campos");
                return;
            }

            if (!password.equals(repeatPassword)) {
                JOptionPane.showMessageDialog(this, "ERROR: Las contraseñas no coinciden.");
                return;
            }

            Empleado empleado = empleadoController.getEmpleadoByUsuario(usuario);

            if (empleado != null) {
                JOptionPane.showMessageDialog(this, "ERROR: El usuario ya existe");
                return;
            }

            nuevoEmpleado = Empleado.builder().usuario(usuario).password(password).nombre(nombre).apellido(apellido).dni(dni).admin(admin).build();

            empleadoController.insertarEmpleado(nuevoEmpleado);

            save = true;

            dispose();
        });

        //Panel del boton de guardar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    public Empleado getNuevoEmpleado() {
        return nuevoEmpleado;
    }

    public boolean isSave() {
        return save;
    }
}