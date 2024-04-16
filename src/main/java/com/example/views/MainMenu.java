package com.example.views;

import com.example.GestionRestaurante;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MainMenu extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final GestionRestaurante gestionRestaurante;
	private JButton btnReservar;
	private JButton btnVerReservas;

    /**
	 * Create the panel.
	 */
	public MainMenu(GestionRestaurante gestionRestaurante) {
		this.gestionRestaurante = gestionRestaurante;

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

		add(centerPanel, BorderLayout.CENTER);

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
		gestionRestaurante.setSize(800,500);
		gestionRestaurante.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVerReservas)) {
			this.setVisible(false);
			new VerReservasPanel(gestionRestaurante);
		} else if (e.getSource().equals(btnReservar)) {
			//TODO: agregar panel para reservar
		}
	}
}
