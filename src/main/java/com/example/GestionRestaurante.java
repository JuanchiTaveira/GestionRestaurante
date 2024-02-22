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

		GridBagConstraints gbc;
		Insets insets = new Insets(5, 5, 5, 5); // padding

		labelUsuario = new JLabel("Usuario");
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 1;
		centerPanel.add(labelUsuario, gbc);

		tfUsuario = new JTextField(15);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 0);
		gbc.gridx = 1;
		gbc.gridy = 1;
		centerPanel.add(tfUsuario, gbc);

		labelPassword = new JLabel("Contraseña");
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 2;
		centerPanel.add(labelPassword, gbc);

		tfPassword = new JPasswordField(15);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 0);
		gbc.gridx = 1;
		gbc.gridy = 2;
		centerPanel.add(tfPassword, gbc);

		btnIniciarSesion = new JButton("Iniciar sesión");
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 0, 0);
		gbc.gridx = 1;
		gbc.gridy = 3;
		centerPanel.add(btnIniciarSesion, gbc);
		btnIniciarSesion.addActionListener(this);

		mainContainer.add(centerPanel, BorderLayout.CENTER); // agrego el contenedor central al contenedor principal
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnIniciarSesion)) {
			if (usuarioController.iniciarSesion(tfUsuario.getText(), tfPassword.getText())) {
				JOptionPane.showMessageDialog(this, "Iniciando sesión...");
			} else {
				JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
			}
		}
	}
}
