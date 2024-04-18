package com.example.views;

import com.example.GestionRestaurante;
import com.toedter.calendar.JCalendar;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PlanoReservar extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlanoReservar(GestionRestaurante gestionRestaurante) {
		setLayout(new BorderLayout(0, 0));

		JPanel calendarContainer = new JPanel(null);
		calendarContainer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		add(calendarContainer, BorderLayout.WEST);

		JCalendar calendar = new JCalendar();
		calendar.setBorder(new EmptyBorder(10, 10, 10, 10));

		GroupLayout gl_calendarContainer = new GroupLayout(calendarContainer);
		gl_calendarContainer.setHorizontalGroup(
			gl_calendarContainer.createParallelGroup(Alignment.LEADING)
				.addComponent(calendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		gl_calendarContainer.setVerticalGroup(
			gl_calendarContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_calendarContainer.createSequentialGroup()
					.addComponent(calendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(305, Short.MAX_VALUE))
		);

		calendarContainer.setLayout(gl_calendarContainer);

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
		gestionRestaurante.setSize(800,500);
		gestionRestaurante.setLocationRelativeTo(null);
	}
}
