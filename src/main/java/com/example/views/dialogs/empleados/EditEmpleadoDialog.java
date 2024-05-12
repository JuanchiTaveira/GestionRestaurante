package com.example.views.dialogs.empleados;

import com.example.controller.EmpleadoController;
import com.example.model.Empleado;
import com.example.utils.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class EditEmpleadoDialog extends JDialog {
    private static final EmpleadoController empleadoController = new EmpleadoController();
    private final JLabel labelIdAutofill, labelUsuarioAutofill;
    private boolean save;
    private JTextField tfNombre, tfApellido, tfDni;
    private JPasswordField passwordField, repeatPasswordField;
    private JComboBox adminComboBox;

    public EditEmpleadoDialog(String id, String usuario, String nombre, String apellido, String dni, String admin) {
    	getContentPane().setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
    	setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
    	setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/cubiertos.png"));
        setTitle("Editar Empleado");
        setSize(400, 350);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        formPanel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);

        JLabel labelId = new JLabel("ID:");
        labelId.setIcon(new ImageIcon("src/main/resources/images/id.png"));
        labelId.setFont(new Font(Constants.ROCKWELL_NOVA, labelId.getFont().getStyle(), labelId.getFont().getSize()));
        formPanel.add(labelId);
        labelIdAutofill = new JLabel(id);
        labelIdAutofill.setFont(new Font(Constants.ROCKWELL_NOVA, labelIdAutofill.getFont().getStyle(), labelIdAutofill.getFont().getSize()));
        labelIdAutofill.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(labelIdAutofill);

        JLabel labelUsuario = new JLabel("Usuario:");
        labelUsuario.setIcon(new ImageIcon("src/main/resources/images/usuario_p.png"));
        labelUsuario.setFont(new Font(Constants.ROCKWELL_NOVA, labelUsuario.getFont().getStyle(), labelUsuario.getFont().getSize()));
        formPanel.add(labelUsuario);
        labelUsuarioAutofill = new JLabel(usuario);
        labelUsuarioAutofill.setFont(new Font(Constants.ROCKWELL_NOVA, labelUsuarioAutofill.getFont().getStyle(), labelUsuarioAutofill.getFont().getSize()));
        labelUsuarioAutofill.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(labelUsuarioAutofill);

        String actualPassword = empleadoController.getEmpleadoById(id).getPassword();

        JLabel labelPassword = new JLabel("Contraseña:");
        labelPassword.setIcon(new ImageIcon("src/main/resources/images/candado_p.png"));
        labelPassword.setFont(new Font(Constants.ROCKWELL_NOVA, labelPassword.getFont().getStyle(), labelPassword.getFont().getSize()));
        formPanel.add(labelPassword);
        passwordField = new JPasswordField(actualPassword);
        passwordField.setFont(new Font(Constants.ROCKWELL_NOVA, passwordField.getFont().getStyle(), passwordField.getFont().getSize()));
        passwordField.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(passwordField);

        JLabel labelRepeatPassword = new JLabel("Repetir contraseña:");
        labelRepeatPassword.setFont(new Font(Constants.ROCKWELL_NOVA, labelRepeatPassword.getFont().getStyle(), labelRepeatPassword.getFont().getSize()));
        labelRepeatPassword.setIcon(new ImageIcon("src/main/resources/images/candado_p.png"));
        formPanel.add(labelRepeatPassword);
        repeatPasswordField = new JPasswordField(actualPassword);
        repeatPasswordField.setFont(new Font(Constants.ROCKWELL_NOVA, repeatPasswordField.getFont().getStyle(), repeatPasswordField.getFont().getSize()));
        repeatPasswordField.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        repeatPasswordField.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(repeatPasswordField);

        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(new Font(Constants.ROCKWELL_NOVA, labelNombre.getFont().getStyle(), labelNombre.getFont().getSize()));
        labelNombre.setIcon(new ImageIcon("src/main/resources/images/firma.png"));
        formPanel.add(labelNombre);
        tfNombre = new JTextField(nombre);
        tfNombre.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        tfNombre.setFont(new Font(Constants.ROCKWELL_NOVA, tfNombre.getFont().getStyle(), tfNombre.getFont().getSize()));
        tfNombre.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfNombre);
        tfNombre.setColumns(10);

        JLabel labelApellido = new JLabel("Apellido:");
        labelApellido.setFont(new Font(Constants.ROCKWELL_NOVA, labelApellido.getFont().getStyle(), labelApellido.getFont().getSize()));
        labelApellido.setIcon(new ImageIcon("src/main/resources/images/candado_p.png"));
        formPanel.add(labelApellido);
        tfApellido = new JTextField(apellido);
        tfApellido.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        tfApellido.setFont(new Font(Constants.ROCKWELL_NOVA, tfApellido.getFont().getStyle(), tfApellido.getFont().getSize()));
        tfApellido.setHorizontalAlignment(SwingConstants.CENTER);
        tfApellido.setColumns(10);
        formPanel.add(tfApellido);

        JLabel labelDni = new JLabel("DNI:");
        labelDni.setIcon(new ImageIcon("src/main/resources/images/dni.png"));
        labelDni.setFont(new Font(Constants.ROCKWELL_NOVA, labelDni.getFont().getStyle(), labelDni.getFont().getSize()));
        formPanel.add(labelDni);
        tfDni = new JTextField(dni);
        tfDni.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        tfDni.setFont(new Font(Constants.ROCKWELL_NOVA, tfDni.getFont().getStyle(), tfDni.getFont().getSize()));
        tfDni.setHorizontalAlignment(SwingConstants.CENTER);
        tfDni.setColumns(10);
        formPanel.add(tfDni);

        JLabel labelAdmin = new JLabel("Admin:");
        labelAdmin.setFont(new Font(Constants.ROCKWELL_NOVA, labelAdmin.getFont().getStyle(), labelAdmin.getFont().getSize()));
        labelAdmin.setIcon(new ImageIcon("src/main/resources/images/admin.png"));
        formPanel.add(labelAdmin);
        adminComboBox = new JComboBox();
        adminComboBox.setBorder(new MatteBorder(2, 2, 2, 2, Constants.COLOR_NEGRO));
        adminComboBox.setFont(new Font(Constants.ROCKWELL_NOVA, adminComboBox.getFont().getStyle(), adminComboBox.getFont().getSize()));
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
        buttonPanel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);

        JButton saveButton = new JButton("Guardar");
        saveButton.setForeground(Constants.COLOR_PRINCIPAL_AMARILLO);
        saveButton.setFont(new Font(Constants.VERDANA, saveButton.getFont().getStyle() | Font.BOLD, 12));
        saveButton.setBackground(Constants.COLOR_NEGRO);
        saveButton.setBorderPainted(false);
        saveButton.addActionListener(e -> {
            // Guardar los valores y cerrar el diálogo
            int confirm = JOptionPane.showConfirmDialog(this, "Quieres confirmar los cambios sobre el empleado con ID: " + id + "?");

            String password = passwordField.getText();
            String repeatPassword = repeatPasswordField.getText();
            String nuevoNombre = tfNombre.getText();
            String nuevoApellido = tfApellido.getText();
            String nuevoDni = tfDni.getText();
            Boolean nuevoAdmin = adminComboBox.getSelectedItem().toString().equals("SI");

            if (password.isBlank() || repeatPassword.isBlank() || nuevoNombre.isBlank() || nuevoApellido.isBlank() || nuevoDni.isBlank()) {
                JOptionPane.showMessageDialog(this, "ERROR: Debe completar todos los campos");
                return;
            }

            if (!password.equals(repeatPassword)) {
                JOptionPane.showMessageDialog(this, "ERROR: Las contraseñas no coinciden.");
                return;
            }

            if (admin.equals("SI") && !nuevoAdmin && empleadoController.getEmpleadosAdminCount() < 2) {
                JOptionPane.showMessageDialog(this, "ERROR: No puede revocar permisos de administrador, ya que éste usuario es el único administrador.");
                return;
            }

            if (confirm == JOptionPane.OK_OPTION) {
                Empleado oldEmpleado = empleadoController.getEmpleadoById(id);

                Empleado empleadoActualizado = Empleado.builder()
                        .id(Integer.valueOf(id))
                        .usuario(usuario)
                        .password(password)
                        .nombre(nuevoNombre)
                        .apellido(nuevoApellido)
                        .dni(nuevoDni)
                        .admin(nuevoAdmin)
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
        return labelIdAutofill.getText();
    }

    public String getUsuario() {
        return labelUsuarioAutofill.getText();
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
