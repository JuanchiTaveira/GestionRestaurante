package com.example.views;

import com.example.GestionRestaurante;
import com.example.model.Reserva;
import com.example.views.utils.ImagePanel;
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
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.JButton;

public class PlanoReservar extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlanoReservar(GestionRestaurante gestionRestaurante) {
		setLayout(new BorderLayout(0, 0));

		JPanel filtersContainer = new JPanel(null);
		filtersContainer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		add(filtersContainer, BorderLayout.WEST);

		JCalendar calendar = new JCalendar();
		calendar.setBorder(new EmptyBorder(10, 10, 0, 10));

		JComboBox horarioComboBox = new JComboBox();
		horarioComboBox.setModel(new DefaultComboBoxModel(Reserva.Horario.values()));
		BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
		basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		horarioComboBox.setRenderer(basicComboBoxRenderer);
		
		JButton btnActualizarPlano = new JButton("Actualizar");

		GroupLayout gl_filtersContainer = new GroupLayout(filtersContainer);
		gl_filtersContainer.setHorizontalGroup(
				gl_filtersContainer.createParallelGroup(Alignment.TRAILING)
						.addComponent(calendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_filtersContainer.createSequentialGroup()
								.addContainerGap()
								.addComponent(horarioComboBox, 0, 184, Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(gl_filtersContainer.createSequentialGroup()
								.addContainerGap(115, Short.MAX_VALUE)
								.addComponent(btnActualizarPlano)
								.addContainerGap())
		);
		gl_filtersContainer.setVerticalGroup(
				gl_filtersContainer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filtersContainer.createSequentialGroup()
								.addComponent(calendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(horarioComboBox, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addGap(20)
								.addComponent(btnActualizarPlano))
		);

		filtersContainer.setLayout(gl_filtersContainer);


		ImagePanel imagePanel = new ImagePanel("/images/plano1c.png");
		add(imagePanel, BorderLayout.CENTER);




		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
		gestionRestaurante.setSize(1200,700);
		gestionRestaurante.setLocationRelativeTo(null);
	}
}
