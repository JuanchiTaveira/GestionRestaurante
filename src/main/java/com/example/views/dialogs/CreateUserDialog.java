package com.example.views.dialogs;

import com.example.controller.UsuarioController;
import com.example.model.UsuarioReserva;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class CreateUserDialog extends JDialog {
    private static final UsuarioController usuarioController = new UsuarioController();
    private final JTextField tfCorreoReserva, tfNombre, tfApellido, tfTelefono;
    private boolean save;

    public CreateUserDialog(String correoReserva) {
        setTitle("Crear Usuario");
        setSize(400, 264);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 197, 23));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBackground(new Color(240, 197, 23));

        formPanel.add(new JLabel("Correo usuario:"));
        tfCorreoReserva = new JTextField(correoReserva);
        tfCorreoReserva.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfCorreoReserva);

        formPanel.add(new JLabel("Nombre:"));
        tfNombre = new JTextField();
        tfNombre.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfNombre);

        formPanel.add(new JLabel("Apellido:"));
        tfApellido = new JTextField();
        tfApellido.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfApellido);

        formPanel.add(new JLabel("Telefono:"));
        tfTelefono = new JTextField();
        tfTelefono.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfTelefono);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {

            String nombre = tfNombre.getText();
            String apellido = tfApellido.getText();
            String telefono = tfTelefono.getText();
            String correo = tfCorreoReserva.getText();

            UsuarioReserva nuevoUsuario = new UsuarioReserva(nombre, apellido, telefono, correo);

            Boolean success = usuarioController.insertarUsuarioReserva(nuevoUsuario);

            if (!success) {
                JOptionPane.showMessageDialog(this, "ERROR: Usuario invalido o ya existente.");
                return;
            }

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

    public boolean isSave() {
        return save;
    }


    public String getTfCorreoReserva() {
        return tfCorreoReserva.getText();
    }
}