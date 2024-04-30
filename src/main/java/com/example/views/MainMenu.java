package com.example.views;

import com.example.GestionRestaurante;
import com.example.controller.EmpleadoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

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
		this.gestionRestaurante = gestionRestaurante;
		gestionRestaurante.setSize(800,500);
		gestionRestaurante.setLocationRelativeTo(null);

        setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc;
		Insets insets = new Insets(5, 5, 5, 5); // padding
		
		btnReservar = new JButton("Reservar");
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 0;
		btnReservar.addActionListener(this);
		centerPanel.add(btnReservar, gbc);

		btnVerReservas = new JButton("Ver Reservas");
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 1;
		btnVerReservas.addActionListener(this);
		centerPanel.add(btnVerReservas, gbc);

		if (EmpleadoController.authUser.isAdmin()) {
			btnGestionarEmpleados = new JButton("Gestionar Empleados");
			gbc = new GridBagConstraints();
			gbc.insets = insets;
			gbc.gridx = 0;
			gbc.gridy = 2;
			btnGestionarEmpleados.addActionListener(this);
			centerPanel.add(btnGestionarEmpleados, gbc);
		}

		add(centerPanel, BorderLayout.CENTER);

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);

		// Panel para el botón "Cerrar sesion"
		JPanel panelLogout = new JPanel();
		panelLogout.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelLogout.setLayout(new FlowLayout(FlowLayout.LEFT));

		btnLogout = new JButton("Cerrar sesión");
		btnLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogout.addActionListener(this);
		panelLogout.add(btnLogout);

		add(panelLogout, BorderLayout.SOUTH);
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
