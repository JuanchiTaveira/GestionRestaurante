package com.example;

import com.example.controller.ReservaController;
import com.example.controller.UsuarioController;
import com.example.model.Reserva;
import com.example.model.Usuario;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class GestionRestaurante extends JFrame implements ActionListener {

	private final UsuarioController usuarioController = new UsuarioController();
	private final ReservaController reservaController = new ReservaController();

	private JPanel centerPanel;
	private JTextField tfUsuario;
	private JPasswordField tfPassword;
	private JLabel labelPassword;
	private JLabel labelUsuario;
	private JButton btnIniciarSesion;
	private JLabel labelTitulo;
	private JLabel lblIconoUsuario;
	private JLabel lblIconoCandando;
	private GridBagConstraints gbc_1;
	private JLabel lblLogo;
	private GridBagConstraints gbc_2;
	private GridBagConstraints gbc_3;
	private JButton btnRegistrarse;
	private JLabel lblLogopie;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionRestaurante window = new GestionRestaurante();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionRestaurante() {
		getContentPane().setBackground(new Color(245, 210, 10));
		usuarioController.insertarUsuario(new Usuario("juan", "juan"));
		reservaController.insertarReserva(new Reserva("juan", 1, LocalDate.now(), Reserva.Horario.ALMUERZO, 5));

		initialize();

	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle("JAJ - Gestión de Restaurante");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centra la ventana en la pantalla

		Container mainContainer = getContentPane(); // contendor principal
		mainContainer.setLayout(new BorderLayout());

		labelTitulo = new JLabel("JAJ - Gestión de Restaurantes"); // titulo de la pantalla
		labelTitulo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		mainContainer.add(labelTitulo, BorderLayout.NORTH);

		centerPanel = new JPanel(new GridBagLayout()); // contenedor central
		centerPanel.setBackground(new Color(245, 210, 10));

		GridBagConstraints gbc;
		Insets insets = new Insets(5, 5, 5, 5);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(GestionRestaurante.class.getResource("/imagenes/logosinfondo.png")));
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.gridheight = 2;
		gbc_lblLogo.anchor = GridBagConstraints.EAST;
		gbc_lblLogo.gridwidth = 6;
		gbc_lblLogo.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogo.gridx = 0;
		gbc_lblLogo.gridy = 0;
		centerPanel.add(lblLogo, gbc_lblLogo);
		
		lblIconoUsuario = new JLabel("");
		lblIconoUsuario.setIcon(new ImageIcon(GestionRestaurante.class.getResource("/imagenes/usuario.png")));
		GridBagConstraints gbc_lblIconoUsuario = new GridBagConstraints();
		gbc_lblIconoUsuario.gridwidth = 2;
		gbc_lblIconoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblIconoUsuario.gridx = 0;
		gbc_lblIconoUsuario.gridy = 2;
		centerPanel.add(lblIconoUsuario, gbc_lblIconoUsuario);

		labelUsuario = new JLabel("Usuario");
		gbc = new GridBagConstraints();
		gbc.gridwidth = 2;
		gbc.insets = insets;
		gbc.gridx = 2;
		gbc.gridy = 2;
		centerPanel.add(labelUsuario, gbc);

		tfUsuario = new JTextField(15);
		gbc_2 = new GridBagConstraints();
		gbc_2.gridwidth = 2;
		gbc_2.insets = new Insets(5, 5, 5, 0);
		gbc_2.gridx = 4;
		gbc_2.gridy = 2;
		centerPanel.add(tfUsuario, gbc_2);
		
		lblIconoCandando = new JLabel("");
		lblIconoCandando.setIcon(new ImageIcon(GestionRestaurante.class.getResource("/imagenes/candado.png")));
		GridBagConstraints gbc_lblIconoCandando = new GridBagConstraints();
		gbc_lblIconoCandando.gridwidth = 2;
		gbc_lblIconoCandando.insets = new Insets(0, 0, 5, 5);
		gbc_lblIconoCandando.gridx = 0;
		gbc_lblIconoCandando.gridy = 3;
		centerPanel.add(lblIconoCandando, gbc_lblIconoCandando);

		labelPassword = new JLabel("Contraseña");
		gbc_1 = new GridBagConstraints();
		gbc_1.gridwidth = 2;
		gbc_1.insets = insets;
		gbc_1.gridx = 2;
		gbc_1.gridy = 3;
		centerPanel.add(labelPassword, gbc_1);

		tfPassword = new JPasswordField(15);
		gbc_3 = new GridBagConstraints();
		gbc_3.gridwidth = 2;
		gbc_3.insets = new Insets(5, 5, 5, 0);
		gbc_3.gridx = 4;
		gbc_3.gridy = 3;
		centerPanel.add(tfPassword, gbc_3);
		
		btnRegistrarse = new JButton(" Registrarse ");
		GridBagConstraints gbc_btnRegistrarse = new GridBagConstraints();
		gbc_btnRegistrarse.anchor = GridBagConstraints.SOUTH;
		gbc_btnRegistrarse.gridwidth = 3;
		gbc_btnRegistrarse.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegistrarse.gridx = 1;
		gbc_btnRegistrarse.gridy = 4;
		centerPanel.add(btnRegistrarse, gbc_btnRegistrarse);

		btnIniciarSesion = new JButton("Iniciar sesión");
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 0, 0);
		gbc.gridx = 5;
		gbc.gridy = 4;
		centerPanel.add(btnIniciarSesion, gbc);
		btnIniciarSesion.addActionListener(this);
		
		lblLogopie = new JLabel("");
		lblLogopie.setIcon(new ImageIcon(GestionRestaurante.class.getResource("/imagenes/logopie2.png")));
		getContentPane().add(lblLogopie, BorderLayout.SOUTH);

		mainContainer.add(centerPanel, BorderLayout.CENTER);
		GridBagConstraints gbc_lblLogopie = new GridBagConstraints();
		gbc_lblLogo.gridwidth = 5;
		gbc_lblLogo.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogo.gridx = 0;
		gbc_lblLogo.gridy = 0;
		centerPanel.add(lblLogo, gbc_lblLogo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnIniciarSesion)) {
			if (usuarioController.iniciarSesion(tfUsuario.getText(), tfPassword.getText())) {
				JOptionPane.showMessageDialog(this, "Iniciando sesión...");
			} else {
				JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
			}
		}if (e.getSource().equals(btnRegistrarse)) {
			JOptionPane.showMessageDialog(this, "Entrando a pagina de registro");
		}
	}

}
