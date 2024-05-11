package com.example.views.dialogs.clientes;

import com.example.controller.ClienteController;
import com.example.model.Cliente;
import com.example.views.utils.Constants;

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
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class CreateClienteDialog extends JDialog {
    private static final ClienteController clienteController = new ClienteController();
    private final JTextField tfCorreoReserva, tfNombre, tfApellido, tfTelefono;
    private boolean save;

    public CreateClienteDialog(String correoReserva) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/cubiertos.png"));
        setTitle("Crear Cliente");
        setSize(400, 264);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);

        JLabel label = new JLabel("Correo:");
        label.setFont(new Font(Constants.ROCKWELL_NOVA, label.getFont().getStyle(), label.getFont().getSize()));
        label.setIcon(new ImageIcon("src/main/resources/images/correo.png"));
        formPanel.add(label);
        tfCorreoReserva = new JTextField("");
        tfCorreoReserva.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        tfCorreoReserva.setFont(new Font(Constants.ROCKWELL_NOVA, tfCorreoReserva.getFont().getStyle(), tfCorreoReserva.getFont().getSize()));
        tfCorreoReserva.setHorizontalAlignment(SwingConstants.CENTER);
        tfCorreoReserva.setText(correoReserva);
        formPanel.add(tfCorreoReserva);

        JLabel label_1 = new JLabel("Nombre:");
        label_1.setIcon(new ImageIcon("src/main/resources/images/firma.png"));
        label_1.setFont(new Font(Constants.ROCKWELL_NOVA, label_1.getFont().getStyle(), label_1.getFont().getSize()));
        formPanel.add(label_1);
        tfNombre = new JTextField();
        tfNombre.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        tfNombre.setFont(new Font(Constants.ROCKWELL_NOVA, tfNombre.getFont().getStyle(), tfNombre.getFont().getSize()));
        tfNombre.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfNombre);

        JLabel label_2 = new JLabel("Apellido:");
        label_2.setIcon(new ImageIcon("src/main/resources/images/firma.png"));
        label_2.setFont(new Font(Constants.ROCKWELL_NOVA, label_2.getFont().getStyle(), label_2.getFont().getSize()));
        formPanel.add(label_2);
        tfApellido = new JTextField();
        tfApellido.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        tfApellido.setFont(new Font(Constants.ROCKWELL_NOVA, tfApellido.getFont().getStyle(), tfApellido.getFont().getSize()));
        tfApellido.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfApellido);

        JLabel label_3 = new JLabel("Telefono:");
        label_3.setIcon(new ImageIcon("src/main/resources/images/telefono.png"));
        label_3.setFont(new Font(Constants.ROCKWELL_NOVA, label_3.getFont().getStyle(), label_3.getFont().getSize()));
        formPanel.add(label_3);
        tfTelefono = new JTextField();
        tfTelefono.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        tfTelefono.setFont(new Font(Constants.ROCKWELL_NOVA, tfTelefono.getFont().getStyle(), tfTelefono.getFont().getSize()));
        tfTelefono.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfTelefono);

        JButton saveButton = new JButton("Guardar");
        saveButton.setBorderPainted(false);
        saveButton.setForeground(Constants.COLOR_PRINCIPAL_AMARILLO);
        saveButton.setBackground(Constants.COLOR_NEGRO);
        saveButton.setFont(new Font(Constants.VERDANA, saveButton.getFont().getStyle() | Font.BOLD, 12));
        saveButton.addActionListener(e -> {

            String nombre = tfNombre.getText();
            String apellido = tfApellido.getText();
            String telefono = tfTelefono.getText();
            String correo = tfCorreoReserva.getText();

            if (nombre.isBlank() || apellido.isBlank() || telefono.isBlank() || correo.isBlank()) {
                JOptionPane.showMessageDialog(this, "ERROR: Debe completar todos los campos");
                return;
            }

            Cliente nuevoUsuario = new Cliente(nombre, apellido, telefono, correo);

            Boolean success = clienteController.insertarClienteReserva(nuevoUsuario);

            if (!success) {
                JOptionPane.showMessageDialog(this, "ERROR: Usuario invalido o ya existente.");
                return;
            }

            save = true;

            dispose();
        });

        //Panel del boton de guardar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
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