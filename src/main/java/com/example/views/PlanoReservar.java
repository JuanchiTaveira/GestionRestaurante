package com.example.views;

import com.example.GestionRestaurante;
import com.example.controller.ReservaController;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlanoReservar extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final ReservaController reservaController = new ReservaController();
	JButton btnActualizarPlano, btnMesa1, btnMesa2, btnMesa3, btnMesa4, btnMesa5, btnMesa6, btnMesa7, btnMesa8, btnMesa9, btnMesa10, btnMesa11, btnMesa12, btnMesa13;
	JCalendar calendar;
	JComboBox horarioComboBox;
	List<JButton> allBtnMesa;

	public PlanoReservar(GestionRestaurante gestionRestaurante) {
		setPreferredSize(new Dimension(1216, 700));
		setLayout(new BorderLayout(0, 0));

		JPanel filtersContainer = new JPanel(null);
		filtersContainer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		add(filtersContainer, BorderLayout.WEST);

		calendar = new JCalendar();
		calendar.setBorder(new EmptyBorder(10, 10, 0, 10));

		horarioComboBox = new JComboBox();
		horarioComboBox.setModel(new DefaultComboBoxModel(Reserva.Horario.values()));
		BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
		basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		horarioComboBox.setRenderer(basicComboBoxRenderer);
		
		btnActualizarPlano = new JButton("Actualizar");
		btnActualizarPlano.addActionListener(this);

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
		imagePanel.setLayout(null);
		
		btnMesa1 = new JButton("1");
		btnMesa1.addActionListener(this);
		btnMesa1.setBounds(122, 141, 52, 23);
		imagePanel.add(btnMesa1);
		
		btnMesa2 = new JButton("2");
		btnMesa2.addActionListener(this);
		btnMesa2.setBounds(42, 348, 52, 23);
		imagePanel.add(btnMesa2);

		btnMesa3 = new JButton("3");
		btnMesa3.addActionListener(this);
		btnMesa3.setBounds(197, 348, 52, 23);
		imagePanel.add(btnMesa3);

		btnMesa4 = new JButton("4");
		btnMesa4.addActionListener(this);
		btnMesa4.setBounds(42, 530, 52, 23);
		imagePanel.add(btnMesa4);

		btnMesa5 = new JButton("5");
		btnMesa5.addActionListener(this);
		btnMesa5.setBounds(197, 530, 52, 23);
		imagePanel.add(btnMesa5);

		btnMesa6 = new JButton("6");
		btnMesa6.addActionListener(this);
		btnMesa6.setBounds(436, 486, 52, 23);
		imagePanel.add(btnMesa6);

		btnMesa7 = new JButton("7");
		btnMesa7.addActionListener(this);
		btnMesa7.setBounds(579, 486, 52, 23);
		imagePanel.add(btnMesa7);

		btnMesa8 = new JButton("8");
		btnMesa8.addActionListener(this);
		btnMesa8.setBounds(721, 486, 52, 23);
		imagePanel.add(btnMesa8);

		btnMesa9 = new JButton("9");
		btnMesa9.addActionListener(this);
		btnMesa9.setBounds(864, 486, 52, 23);
		imagePanel.add(btnMesa9);

		btnMesa10 = new JButton("10");
		btnMesa10.addActionListener(this);
		btnMesa10.setBounds(450, 624, 52, 23);
		imagePanel.add(btnMesa10);

		btnMesa11 = new JButton("11");
		btnMesa11.addActionListener(this);
		btnMesa11.setBounds(592, 624, 52, 23);
		imagePanel.add(btnMesa11);

		btnMesa12 = new JButton("12");
		btnMesa12.addActionListener(this);
		btnMesa12.setBounds(734, 624, 52, 23);
		imagePanel.add(btnMesa12);

		btnMesa13 = new JButton("13");
		btnMesa13.addActionListener(this);
		btnMesa13.setBounds(877, 624, 52, 23);
		imagePanel.add(btnMesa13);

		allBtnMesa = List.of(btnMesa1, btnMesa2, btnMesa3, btnMesa4, btnMesa5, btnMesa6, btnMesa7, btnMesa8, btnMesa9, btnMesa10, btnMesa11, btnMesa12, btnMesa13);

		setBtnMesaColorAndAddActionListener();

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
		gestionRestaurante.pack();
		gestionRestaurante.setLocationRelativeTo(null);
		gestionRestaurante.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnActualizarPlano)) {
			setBtnMesaColorAndAddActionListener();
		}
	}

	private void setBtnMesaColorAndAddActionListener() {
		LocalDate dateSelected = LocalDate.of(calendar.getYearChooser().getYear(), calendar.getMonthChooser()
				.getMonth() + 1, calendar.getDayChooser().getDay());
		Reserva.Horario horario = Reserva.Horario.valueOf(horarioComboBox.getSelectedItem().toString());

		List<String> mesasReservadas = reservaController.getMesasReservadas(dateSelected, horario).stream().map(Object::toString).toList();

		allBtnMesa.forEach(btn -> {
			if (mesasReservadas.contains(btn.getText())) {
				btn.setBackground(new Color(242, 18, 13));
			} else {
				btn.setBackground(new Color(128, 255, 0));
			}
		});
	}
}
