package com.example.views;

import com.example.GestionRestaurante;
import com.example.views.utils.ImagePanel;

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
import java.awt.Dimension;

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
		gestionRestaurante.setSize(500,300);

        setLayout(new BorderLayout(0, 0));

		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(new Color(240, 197, 23));

		GridBagConstraints gbc;
		Insets insets = new Insets(5, 5, 5, 5); // padding

		JLabel imagenLogo = new JLabel("");
		imagenLogo.setIcon(new ImageIcon("src/main/resources/images/logo.png"));
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(imagenLogo, gbc);
		
		btnReservar = new JButton("Reservar");
		btnReservar.setForeground(new Color(240, 197, 23));
		btnReservar.setBackground(new Color(0, 0, 0));
		btnReservar.setBorderPainted(false);
		btnReservar.setFont(new Font("Verdana", btnReservar.getFont().getStyle() | Font.BOLD, btnReservar.getFont().getSize() + 2));
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 1;
		btnReservar.addActionListener(this);
		centerPanel.add(btnReservar, gbc);

		btnVerReservas = new JButton("Ver Reservas");
		btnVerReservas.setForeground(new Color(240, 197, 23));
		btnVerReservas.setBackground(new Color(0, 0, 0));
		btnVerReservas.setFont(new Font("Verdana", btnVerReservas.getFont().getStyle() | Font.BOLD, btnVerReservas.getFont().getSize() + 1));
		btnVerReservas.setBorderPainted(false);
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 2;
		btnVerReservas.addActionListener(this);
		centerPanel.add(btnVerReservas, gbc);

		add(centerPanel, BorderLayout.CENTER);

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);

		ImagePanel imagePanel = new ImagePanel("/images/logopie.png");
		imagePanel.setPreferredSize(new Dimension(300, 65));
		add(imagePanel, BorderLayout.SOUTH);
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
