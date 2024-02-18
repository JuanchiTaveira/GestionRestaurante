package com.example;

import com.example.controller.ReservaController;
import com.example.controller.UsuarioController;
import com.example.model.Reserva;
import com.example.model.Usuario;

import javax.swing.JFrame;
import java.awt.EventQueue;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class GestionRestaurante {

	private UsuarioController usuarioController = new UsuarioController();
	private ReservaController reservaController = new ReservaController();

	private JFrame frame;
	private JTextField tfUsuario;
	private JPasswordField tfPassword;
	private JLabel labelPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionRestaurante window = new GestionRestaurante();
					window.frame.setVisible(true);
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
		// testeando que funcione Usuario y Reserva
		usuarioController.insertarUsuario(new Usuario("juan123", "123456"));
		reservaController.insertarReserva(new Reserva("juan", 1, Reserva.Horario.ALMUERZO));

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 703, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(262, 147, 165, 20);
		frame.getContentPane().add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(262, 206, 165, 20);
		frame.getContentPane().add(tfPassword);
		tfPassword.setColumns(10);
		
		JLabel labelUsuario = new JLabel("Usuario");
		labelUsuario.setBounds(160, 150, 75, 14);
		frame.getContentPane().add(labelUsuario);
		
		labelPassword = new JLabel("Contraseña");
		labelPassword.setBounds(160, 209, 75, 14);
		frame.getContentPane().add(labelPassword);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.addActionListener(e -> {
			if (usuarioController.iniciarSesion(tfUsuario.getText(), tfPassword.getText())) {
				System.out.println("Cargando siguiente pantalla...");
			}
        });
		btnIniciarSesion.setBounds(160, 265, 119, 23);
		frame.getContentPane().add(btnIniciarSesion);
	}
}
