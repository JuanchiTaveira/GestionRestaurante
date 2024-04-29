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
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

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
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(240, 197, 23));

		GridBagConstraints gbc;
		Insets insets = new Insets(5, 5, 5, 5); // padding
		
		btnReservar = new JButton("Reservar");
		btnReservar.setForeground(new Color(240, 197, 23));
		btnReservar.setBackground(new Color(0, 0, 0));
		btnReservar.setBorderPainted(false);
		btnReservar.setFont(new Font("Verdana", btnReservar.getFont().getStyle() | Font.BOLD, btnReservar.getFont().getSize() + 2));
		btnReservar.setBounds(160, 119, 120, 25);
		btnReservar.addActionListener(this);
		centerPanel.setLayout(null);
		centerPanel.add(btnReservar);

		btnVerReservas = new JButton("Ver Reservas");
		btnVerReservas.setForeground(new Color(240, 197, 23));
		btnVerReservas.setBackground(new Color(0, 0, 0));
		btnVerReservas.setBorderPainted(false);
		btnVerReservas.setFont(new Font("Verdana", btnVerReservas.getFont().getStyle() | Font.BOLD, btnVerReservas.getFont().getSize() + 1));
		btnVerReservas.setBounds(149, 155, 148, 25);
		btnVerReservas.addActionListener(this);
		centerPanel.add(btnVerReservas);

		add(centerPanel, BorderLayout.CENTER);
		
		JLabel imagenLogo = new JLabel("");
		imagenLogo.setIcon(new ImageIcon("src/main/resources/images/logo.png"));
		imagenLogo.setBounds(181, 27, 80, 82);
		centerPanel.add(imagenLogo);

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JLabel imagenFooter = new JLabel("");
		imagenFooter.setIcon(new ImageIcon("src/main/resources/images/logopie.png"));
		panel.add(imagenFooter);
		gestionRestaurante.setSize(500,300);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVerReservas)) {
			this.setVisible(false);
			new VerReservasPanel(gestionRestaurante);
		} else if (e.getSource().equals(btnReservar)) {
			this.setVisible(false);
			new PlanoReservar(gestionRestaurante);
		}
	}
}
