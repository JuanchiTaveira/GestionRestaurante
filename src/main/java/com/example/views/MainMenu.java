package com.example.views;

import com.example.GestionRestaurante;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JButton;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = 1L;

    /**
	 * Create the panel.
	 */
	public MainMenu(GestionRestaurante gestionRestaurante) {

        setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc;
		Insets insets = new Insets(5, 5, 5, 5); // padding
		
		JButton btnReservar = new JButton("Reservar");
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(btnReservar, gbc);

		JButton btnVerReservas = new JButton("Ver Reservas");
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 1;
		centerPanel.add(btnVerReservas, gbc);

		add(centerPanel, BorderLayout.CENTER);

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
	}
}
