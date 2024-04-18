package com.example.views;

import com.example.GestionRestaurante;
import com.toedter.calendar.JCalendar;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class PlanoReservar extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlanoReservar(GestionRestaurante gestionRestaurante) {
		setLayout(new BorderLayout(0, 0));

		JPanel calendarContainer = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0; // en la columna cero
		gbc.gridy = 0; // en la fila cero
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(10, 10, 10, 10); // márgenes para el calendario

		JCalendar calendar = new JCalendar();
		calendarContainer.add(calendar, gbc);

		add(calendarContainer, BorderLayout.WEST);

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
		gestionRestaurante.setSize(800,500);
		gestionRestaurante.setLocationRelativeTo(null);
	}

}
