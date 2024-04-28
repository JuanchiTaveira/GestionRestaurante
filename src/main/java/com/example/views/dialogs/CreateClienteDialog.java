package com.example.views.dialogs;

import com.example.controller.ClienteController;
import com.example.model.Cliente;

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

public class CreateClienteDialog extends JDialog {
    private static final ClienteController CLIENTE_CONTROLLER = new ClienteController();
    private final JTextField tfCorreoReserva, tfNombre, tfApellido, tfTelefono;
    private boolean save;

    public CreateClienteDialog(String correoReserva) {
        setTitle("Crear Cliente");
        setSize(400, 264);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        formPanel.add(new JLabel("Correo cliente:"));
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

            Cliente nuevoCliente = new Cliente(nombre, apellido, telefono, correo);

            Boolean success = CLIENTE_CONTROLLER.insertarClienteReserva(nuevoCliente);

            if (!success) {
                JOptionPane.showMessageDialog(this, "ERROR: Cliente invalido o ya existente.");
                return;
            }

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

    public boolean isSave() {
        return save;
    }


    public String getTfCorreoReserva() {
        return tfCorreoReserva.getText();
    }
}