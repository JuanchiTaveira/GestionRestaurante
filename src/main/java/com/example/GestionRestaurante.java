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
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GestionRestaurante extends JFrame implements ActionListener {

	private final UsuarioController usuarioController = new UsuarioController();
	private final ReservaController reservaController = new ReservaController();

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField tfPassword;
	private JLabel labelPassword;
	private JLabel labelUsuario;
	private JButton btnIniciarSesion;


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
		reservaController.insertarReserva(new Reserva("juan", 1, Reserva.Horario.ALMUERZO));

		initialize();

	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle("Gestión de Restaurante");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centra la ventana en la pantalla

		contentPane = new JPanel(new GridBagLayout());
		setContentPane(contentPane);

		GridBagConstraints gbc;
		Insets insets = new Insets(5, 5, 5, 5); // padding

		labelUsuario = new JLabel("Usuario");
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPane.add(labelUsuario, gbc);

		tfUsuario = new JTextField(15);
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 1;
		gbc.gridy = 0;
		contentPane.add(tfUsuario, gbc);

		labelPassword = new JLabel("Contraseña");
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 0;
		gbc.gridy = 1;
		contentPane.add(labelPassword, gbc);

		tfPassword = new JPasswordField(15);
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 1;
		gbc.gridy = 1;
		contentPane.add(tfPassword, gbc);

		btnIniciarSesion = new JButton("Iniciar sesión");
		gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = 1;
		gbc.gridy = 2;
		contentPane.add(btnIniciarSesion, gbc);
		btnIniciarSesion.addActionListener(this);
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
