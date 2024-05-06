package com.example.views.dialogs.clientes;

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
    	setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/logo_pestana.png"));
        setTitle("Crear Cliente");
        setSize(400, 264);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 197, 23));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBackground(new Color(240, 197, 23));

        JLabel label = new JLabel("Correo:");
        label.setFont(new Font("Rockwell Nova", label.getFont().getStyle(), label.getFont().getSize()));
        label.setIcon(new ImageIcon("src/main/resources/images/correo.png"));
        formPanel.add(label);
        tfCorreoReserva = new JTextField("");
        tfCorreoReserva.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        tfCorreoReserva.setFont(new Font("Rockwell Nova", tfCorreoReserva.getFont().getStyle(), tfCorreoReserva.getFont().getSize()));
        tfCorreoReserva.setHorizontalAlignment(SwingConstants.CENTER);
        tfCorreoReserva.setText(correoReserva);
        formPanel.add(tfCorreoReserva);

        JLabel label_1 = new JLabel("Nombre:");
        label_1.setIcon(new ImageIcon("src/main/resources/images/firma.png"));
        label_1.setFont(new Font("Rockwell Nova", label_1.getFont().getStyle(), label_1.getFont().getSize()));
        formPanel.add(label_1);
        tfNombre = new JTextField();
        tfNombre.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        tfNombre.setFont(new Font("Rockwell Nova", tfNombre.getFont().getStyle(), tfNombre.getFont().getSize()));
        tfNombre.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfNombre);

        JLabel label_2 = new JLabel("Apellido:");
        label_2.setIcon(new ImageIcon("src/main/resources/images/firma.png"));
        label_2.setFont(new Font("Rockwell Nova", label_2.getFont().getStyle(), label_2.getFont().getSize()));
        formPanel.add(label_2);
        tfApellido = new JTextField();
        tfApellido.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        tfApellido.setFont(new Font("Rockwell Nova", tfApellido.getFont().getStyle(), tfApellido.getFont().getSize()));
        tfApellido.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfApellido);

        JLabel label_3 = new JLabel("Telefono:");
        label_3.setIcon(new ImageIcon("src/main/resources/images/telefono.png"));
        label_3.setFont(new Font("Rockwell Nova", label_3.getFont().getStyle(), label_3.getFont().getSize()));
        formPanel.add(label_3);
        tfTelefono = new JTextField();
        tfTelefono.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        tfTelefono.setFont(new Font("Rockwell Nova", tfTelefono.getFont().getStyle(), tfTelefono.getFont().getSize()));
        tfTelefono.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfTelefono);

        JButton saveButton = new JButton("Guardar");
        saveButton.setBorderPainted(false);
        saveButton.setForeground(new Color(240, 197, 23));
        saveButton.setBackground(new Color(0, 0, 0));
        saveButton.setFont(new Font("Verdana", saveButton.getFont().getStyle() | Font.BOLD, 12));
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