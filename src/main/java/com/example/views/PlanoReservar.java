package com.example.views;

import com.example.GestionRestaurante;
import com.toedter.calendar.JDateChooser;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class PlanoReservar extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlanoReservar(GestionRestaurante gestionRestaurante) {
		setLayout(new BorderLayout(0, 0));

		JDateChooser dateChooser = new JDateChooser();
		add(dateChooser, BorderLayout.WEST);

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
		gestionRestaurante.setSize(800,500);
		gestionRestaurante.setLocationRelativeTo(null);
	}

}
