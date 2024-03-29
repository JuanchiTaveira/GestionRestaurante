package com.example.views;

import com.example.GestionRestaurante;
import com.example.controller.EmpleadoController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {

    private GestionRestaurante gestionRestaurante;

    private final EmpleadoController empleadoController = new EmpleadoController();

    private JTextField tfUsuario;
    private JPasswordField tfPassword;
    private JLabel labelPassword;
    private JLabel labelUsuario;
    private JButton btnIniciarSesion;
    private JLabel labelTitulo;

    public LoginPanel(GestionRestaurante gestionRestaurante) {
        this.gestionRestaurante = gestionRestaurante;

        setLayout(new BorderLayout());

        labelTitulo = new JLabel("JAJ - Gestión de Restaurantes"); // titulo de la pantalla
        labelTitulo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelTitulo, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout()); // contenedor central

        GridBagConstraints gbc;
        Insets insets = new Insets(5, 5, 5, 5); // padding

        labelUsuario = new JLabel("Usuario");
        gbc = new GridBagConstraints();
        gbc.insets = insets;
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(labelUsuario, gbc);

        tfUsuario = new JTextField(15);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(tfUsuario, gbc);

        labelPassword = new JLabel("Contraseña");
        gbc = new GridBagConstraints();
        gbc.insets = insets;
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(labelPassword, gbc);

        tfPassword = new JPasswordField(15);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        tfPassword.addActionListener(this);
        centerPanel.add(tfPassword, gbc);

        btnIniciarSesion = new JButton("Iniciar sesión");
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0, 0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        centerPanel.add(btnIniciarSesion, gbc);
        btnIniciarSesion.addActionListener(this);

        add(centerPanel, BorderLayout.CENTER); // agrego el contenedor central al contenedor principal
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnIniciarSesion)) {
            if (empleadoController.iniciarSesion(tfUsuario.getText(), tfPassword.getText())) {
                this.setVisible(false);
                new MainMenu(gestionRestaurante);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        } else if (e.getSource().equals(tfPassword) && e.getModifiers() == 0) {
            btnIniciarSesion.doClick();
        }
    }
}
