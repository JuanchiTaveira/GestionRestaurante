package com.example.views;

import com.example.GestionRestaurante;
import com.example.model.Reserva;
import com.toedter.calendar.JCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class PlanoReservar extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlanoReservar(GestionRestaurante gestionRestaurante) {
		setLayout(new BorderLayout(0, 0));

		JPanel calendarContainer = new JPanel(null);
		calendarContainer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		add(calendarContainer, BorderLayout.WEST);

		JCalendar calendar = new JCalendar();
		calendar.setBorder(new EmptyBorder(10, 10, 10, 10));

		JComboBox horarioComboBox = new JComboBox();
		horarioComboBox.setModel(new DefaultComboBoxModel(Reserva.Horario.values()));
		BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
		basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		horarioComboBox.setRenderer(basicComboBoxRenderer);

		GroupLayout gl_calendarContainer = new GroupLayout(calendarContainer);
		gl_calendarContainer.setHorizontalGroup(
			gl_calendarContainer.createParallelGroup(Alignment.LEADING)
				.addComponent(calendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(Alignment.TRAILING, gl_calendarContainer.createSequentialGroup()
					.addContainerGap()
					.addComponent(horarioComboBox, 0, 184, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_calendarContainer.setVerticalGroup(
			gl_calendarContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_calendarContainer.createSequentialGroup()
					.addComponent(calendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(horarioComboBox, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(241, Short.MAX_VALUE))
		);

		calendarContainer.setLayout(gl_calendarContainer);

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
		gestionRestaurante.setSize(800,500);
		gestionRestaurante.setLocationRelativeTo(null);
	}
}
