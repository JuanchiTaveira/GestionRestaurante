package com.example.views;

import com.example.GestionRestaurante;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JButton;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	private GestionRestaurante gestionRestaurante;

	/**
	 * Create the panel.
	 */
	public MainMenu(GestionRestaurante gestionRestaurante) {
		this.gestionRestaurante = gestionRestaurante;

		setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);

		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JButton btnReservar = new JButton("Reservar");
		GridBagConstraints gbc_btnReservar = new GridBagConstraints();
		gbc_btnReservar.insets = new Insets(0, 0, 5, 0);
		gbc_btnReservar.gridx = 6;
		gbc_btnReservar.gridy = 2;
		centerPanel.add(btnReservar, gbc_btnReservar);
		
		JButton btnVerReservas = new JButton("Ver Reservas");
		GridBagConstraints gbc_btnVerReservas = new GridBagConstraints();
		gbc_btnVerReservas.insets = new Insets(0, 0, 5, 0);
		gbc_btnVerReservas.gridx = 6;
		gbc_btnVerReservas.gridy = 4;
		centerPanel.add(btnVerReservas, gbc_btnVerReservas);

		gestionRestaurante.add(this, BorderLayout.CENTER);
	}
}
