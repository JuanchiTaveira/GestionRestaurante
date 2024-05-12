package com.example.views;

import com.example.GestionRestaurante;
import com.example.controller.EmpleadoController;
import com.example.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Dimension;

public class MainMenu extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final GestionRestaurante gestionRestaurante;
	private JButton btnReservar;
	private JButton btnVerReservas;
	private JButton btnGestionarEmpleados;
	private JButton btnLogout;

    /**
	 * Create the panel.
	 */
	public MainMenu(GestionRestaurante gestionRestaurante) {
    	setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
		this.gestionRestaurante = gestionRestaurante;
		gestionRestaurante.setSize(600,450);
		gestionRestaurante.setLocationRelativeTo(null);

        setLayout(new BorderLayout(0, 0));

		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);

		GridBagConstraints gbc;
		Insets insets = new Insets(5, 5, 5, 5); // padding

		JLabel imagenLogo = new JLabel("");
		ImageIcon icono = new ImageIcon("src/main/resources/images/logo_transp3a.png");
		Image image = icono.getImage(); // Obtener la imagen del ImageIcon
		Image nuevaImagen = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Escalar la imagen
		icono = new ImageIcon(nuevaImagen); // Crear un nuevo ImageIcon con la imagen escalada
		imagenLogo.setIcon(icono); // Establecer el ImageIcon en el JLabel
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(imagenLogo, gbc);

		btnReservar = new JButton("          Reservar           ");
		btnReservar.setForeground(Constants.COLOR_BLANCO);
		btnReservar.setBackground(Constants.COLOR_NEGRO);
		btnReservar.setBorderPainted(false);
		btnReservar.setFont(new Font(Constants.VERDANA, btnReservar.getFont().getStyle() | Font.BOLD, 18));
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 1;
		btnReservar.addActionListener(this);
		centerPanel.add(btnReservar, gbc);

		btnVerReservas = new JButton("  Gestionar Reservas  ");
		btnVerReservas.setForeground(Constants.COLOR_BLANCO);
		btnVerReservas.setBackground(Constants.COLOR_NEGRO);
		btnVerReservas.setFont(new Font(Constants.VERDANA, btnVerReservas.getFont().getStyle() | Font.BOLD, 14));
		btnVerReservas.setBorderPainted(false);
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 2;
		btnVerReservas.addActionListener(this);
		centerPanel.add(btnVerReservas, gbc);

		if (EmpleadoController.authUser.isAdmin()) {
			btnGestionarEmpleados = new JButton("Gestionar Empleados");
			btnGestionarEmpleados.setForeground(Constants.COLOR_BLANCO);
			btnGestionarEmpleados.setBackground(Constants.COLOR_NEGRO);
			btnGestionarEmpleados.setFont(new Font(Constants.VERDANA, btnGestionarEmpleados.getFont().getStyle() | Font.BOLD, 14));
			btnGestionarEmpleados.setBorderPainted(false);
			gbc = new GridBagConstraints();
			gbc.insets = insets;
			gbc.gridx = 0;
			gbc.gridy = 3;
			btnGestionarEmpleados.addActionListener(this);
			centerPanel.add(btnGestionarEmpleados, gbc);
		}

		add(centerPanel, BorderLayout.CENTER);

		gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);

		ImagePanel imagePanel = new ImagePanel("/images/logopie1c.png");
		imagePanel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
		imagePanel.setPreferredSize(new Dimension(300, 50));
		add(imagePanel, BorderLayout.SOUTH);


		// Panel para el botón "Cerrar sesion"
		JPanel panelLogout = new JPanel();
		panelLogout.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
		panelLogout.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelLogout.setLayout(new FlowLayout(FlowLayout.LEFT));

		btnLogout = new JButton("Cerrar sesión");
		btnLogout.setForeground(Constants.COLOR_NEGRO);
		btnLogout.setFont(new Font(Constants.VERDANA, btnLogout.getFont().getStyle() | Font.BOLD, 12));
		btnLogout.setBackground(Constants.COLOR_BOTON_VOLVER);
		btnLogout.setBorderPainted(false);
		btnLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogout.addActionListener(this);
		panelLogout.add(btnLogout);

		add(panelLogout, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVerReservas)) {
			this.setVisible(false);
			new VerReservasPanel(gestionRestaurante);
		} else if (e.getSource().equals(btnReservar)) {
			this.setVisible(false);
			new PlanoReservar(gestionRestaurante);
		} else if (e.getSource().equals(btnGestionarEmpleados)) {
			this.setVisible(false);
			new GestionarEmpleados(gestionRestaurante);
		} else if (e.getSource().equals(btnLogout)) {
			this.setVisible(false);
			EmpleadoController.authUser = null;
			new LoginPanel(gestionRestaurante);
		}
	}
}
