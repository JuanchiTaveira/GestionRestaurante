package com.example.views;

import com.example.GestionRestaurante;
import com.example.controller.EmpleadoController;
import com.example.views.utils.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Dimension;

public class MainMenu extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final GestionRestaurante gestionRestaurante;
	private JButton btnReservar;
	private JButton btnVerReservas;
	private JButton btnGestionarEmpleados;
	private JButton btnLogout;

    /**
	 * Create the panel.
	 */
	public MainMenu(GestionRestaurante gestionRestaurante) {
    	setBackground(new Color(240, 197, 23));
		this.gestionRestaurante = gestionRestaurante;
		gestionRestaurante.setSize(700,450);
		gestionRestaurante.setLocationRelativeTo(null);

        setLayout(new BorderLayout(0, 0));

		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(new Color(240, 197, 23));

		GridBagConstraints gbc;
		Insets insets = new Insets(5, 5, 5, 5); // padding

		JLabel imagenLogo = new JLabel("");
		imagenLogo.setIcon(new ImageIcon("C:\\Users\\juanjose\\Documents\\GitHub\\GestionRestaurante\\src\\main\\resources\\images\\lOGO_G.png"));
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(imagenLogo, gbc);

		btnReservar = new JButton("Reservar");
		btnReservar.setForeground(new Color(240, 197, 23));
		btnReservar.setBackground(new Color(0, 0, 0));
		btnReservar.setBorderPainted(false);
		btnReservar.setFont(new Font("Verdana", btnReservar.getFont().getStyle() | Font.BOLD, 14));
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 1;
		btnReservar.addActionListener(this);
		centerPanel.add(btnReservar, gbc);

		btnVerReservas = new JButton("Ver Reservas");
		btnVerReservas.setForeground(new Color(240, 197, 23));
		btnVerReservas.setBackground(new Color(0, 0, 0));
		btnVerReservas.setFont(new Font("Verdana", btnVerReservas.getFont().getStyle() | Font.BOLD, 14));
		btnVerReservas.setBorderPainted(false);
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 2;
		btnVerReservas.addActionListener(this);
		centerPanel.add(btnVerReservas, gbc);

		if (EmpleadoController.authUser.isAdmin()) {
			btnGestionarEmpleados = new JButton("Gestionar Empleados");
			gbc = new GridBagConstraints();
			gbc.insets = insets;
			gbc.gridx = 0;
			gbc.gridy = 3;
			btnGestionarEmpleados.addActionListener(this);
			centerPanel.add(btnGestionarEmpleados, gbc);
		}

		add(centerPanel, BorderLayout.CENTER);

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);

		ImagePanel imagePanel = new ImagePanel("/images/logopie.png");
		imagePanel.setBackground(new Color(240, 197, 23));
		imagePanel.setPreferredSize(new Dimension(300, 65));
		add(imagePanel, BorderLayout.SOUTH);


		// Panel para el botón "Cerrar sesion"
		JPanel panelLogout = new JPanel();
		panelLogout.setBackground(new Color(240, 197, 23));
		panelLogout.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelLogout.setLayout(new FlowLayout(FlowLayout.LEFT));

		btnLogout = new JButton("Cerrar sesión");
		btnLogout.setForeground(new Color(240, 197, 23));
		btnLogout.setFont(new Font("Verdana", btnLogout.getFont().getStyle() | Font.BOLD, 12));
		btnLogout.setBackground(new Color(0, 0, 0));
		btnLogout.setBorderPainted(false);
		btnLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogout.addActionListener(this);
		panelLogout.add(btnLogout);

		add(panelLogout, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVerReservas)) {
			this.setVisible(false);
			new VerReservasPanel(gestionRestaurante);
		} else if (e.getSource().equals(btnReservar)) {
			this.setVisible(false);
			new PlanoReservar(gestionRestaurante);
		} else if (e.getSource().equals(btnGestionarEmpleados)) {
			this.setVisible(false);
			new GestionarEmpleados(gestionRestaurante);
		} else if (e.getSource().equals(btnLogout)) {
			this.setVisible(false);
			EmpleadoController.authUser = null;
			new LoginPanel(gestionRestaurante);
		}
	}
}
