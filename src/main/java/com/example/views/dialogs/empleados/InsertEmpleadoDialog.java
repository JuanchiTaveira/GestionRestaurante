package com.example.views.dialogs.empleados;

import com.example.controller.EmpleadoController;
import com.example.model.Empleado;
import com.example.views.utils.Constants;

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
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class InsertEmpleadoDialog extends JDialog {
    private static final EmpleadoController empleadoController = new EmpleadoController();
    private boolean save;
    private Empleado nuevoEmpleado;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JComboBox adminComboBox;
    private JTextField tfNombre, tfApellido, tfUsuario, tfDni;

    public InsertEmpleadoDialog() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/logo_pestana.png"));
    	getContentPane().setBackground(new Color(240, 197, 23));
    	setBackground(new Color(240, 197, 23));
    	setForeground(new Color(0, 0, 0));
        setTitle("Agregar Empleado");
        setSize(400, 350);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 197, 23));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        formPanel.setBackground(new Color(240, 197, 23));

        JLabel label = new JLabel("Usuario:");
        label.setIcon(new ImageIcon("src/main/resources/images/usuario_p.png"));
        label.setFont(new Font(Constants.ROCKWELL_NOVA, label.getFont().getStyle(), label.getFont().getSize()));
        formPanel.add(label);
        tfUsuario = new JTextField();
        tfUsuario.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        tfUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfUsuario);
        
        JLabel label_1 = new JLabel("Contraseña:");
        label_1.setIcon(new ImageIcon("src/main/resources/images/candado_p.png"));
        label_1.setFont(new Font(Constants.ROCKWELL_NOVA, label_1.getFont().getStyle(), label_1.getFont().getSize()));
        formPanel.add(label_1);
        passwordField = new JPasswordField();
        passwordField.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(passwordField);
        
        JLabel label_2 = new JLabel("Repetir contraseña:");
        label_2.setIcon(new ImageIcon("src/main/resources/images/candado_p.png"));
        label_2.setFont(new Font(Constants.ROCKWELL_NOVA, label_2.getFont().getStyle(), label_2.getFont().getSize()));
        formPanel.add(label_2);
        repeatPasswordField = new JPasswordField();
        repeatPasswordField.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        repeatPasswordField.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(repeatPasswordField);

        JLabel label_3 = new JLabel("Nombre:");
        label_3.setIcon(new ImageIcon("src/main/resources/images/firma.png"));
        label_3.setFont(new Font(Constants.ROCKWELL_NOVA, label_3.getFont().getStyle(), label_3.getFont().getSize()));
        formPanel.add(label_3);
        tfNombre = new JTextField();
        tfNombre.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        tfNombre.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfNombre);
        tfNombre.setColumns(10);

        JLabel label_4 = new JLabel("Apellido:");
        label_4.setIcon(new ImageIcon("src/main/resources/images/firma.png"));
        label_4.setFont(new Font(Constants.ROCKWELL_NOVA, label_4.getFont().getStyle(), label_4.getFont().getSize()));
        formPanel.add(label_4);
        tfApellido = new JTextField();
        tfApellido.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        tfApellido.setHorizontalAlignment(SwingConstants.CENTER);
        tfApellido.setColumns(10);
        formPanel.add(tfApellido);

        JLabel label_5 = new JLabel("DNI:");
        label_5.setIcon(new ImageIcon("src/main/resources/images/id.png"));
        label_5.setFont(new Font(Constants.ROCKWELL_NOVA, label_5.getFont().getStyle(), label_5.getFont().getSize()));
        formPanel.add(label_5);
        tfDni = new JTextField();
        tfDni.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        tfDni.setHorizontalAlignment(SwingConstants.CENTER);
        tfDni.setColumns(10);
        formPanel.add(tfDni);

        JLabel label_6 = new JLabel("Admin:");
        label_6.setIcon(new ImageIcon("src/main/resources/images/admin.png"));
        label_6.setFont(new Font(Constants.ROCKWELL_NOVA, label_6.getFont().getStyle(), label_6.getFont().getSize()));
        formPanel.add(label_6);
        adminComboBox = new JComboBox();
        adminComboBox.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        adminComboBox.setBackground(new Color(255, 255, 255));
        adminComboBox.setModel(new DefaultComboBoxModel(new String[] {"SI", "NO"}));
        BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
        basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        adminComboBox.setRenderer(basicComboBoxRenderer);
        formPanel.add(adminComboBox);

        JButton saveButton = new JButton("Guardar");
        saveButton.setBorderPainted(false);
        saveButton.setBackground(new Color(0, 0, 0));
        saveButton.setForeground(new Color(240, 197, 23));
        saveButton.setFont(new Font("Verdana", saveButton.getFont().getStyle() | Font.BOLD, 12));
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
        buttonPanel.setBackground(new Color(240, 197, 23));
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