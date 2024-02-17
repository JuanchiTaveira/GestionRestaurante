package com.example;

import com.example.controller.UsuarioController;
import com.example.model.Usuario;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class GestionRestaurante {

	private JFrame frame;

	private UsuarioController usuarioController = new UsuarioController();

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
		initialize();

		usuarioController.insertarUsuario(new Usuario("juan123", "123456"));
		Boolean result = usuarioController.iniciarSesion("juan123", "123456");
		System.out.println(result);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
