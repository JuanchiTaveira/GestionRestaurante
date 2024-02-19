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

public class GestionRestaurante extends JFrame implements ActionListener {

	private UsuarioController usuarioController = new UsuarioController();
	private ReservaController reservaController = new ReservaController();

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
		setBounds(100, 100, 703, 485);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(262, 147, 165, 20);
		tfUsuario.setColumns(10);
		contentPane.add(tfUsuario);

		tfPassword = new JPasswordField();
		tfPassword.setBounds(262, 206, 165, 20);
		tfPassword.setColumns(10);
		contentPane.add(tfPassword);

		labelUsuario = new JLabel("Usuario");
		labelUsuario.setBounds(160, 150, 75, 14);
		contentPane.add(labelUsuario);
		
		labelPassword = new JLabel("Contraseña");
		labelPassword.setBounds(160, 209, 75, 14);
		contentPane.add(labelPassword);
		
		btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.setBounds(160, 265, 119, 23);
		btnIniciarSesion.addActionListener(this);
		contentPane.add(btnIniciarSesion);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnIniciarSesion)) {
			System.out.println("Cargando siguiente pantalla...");
			JOptionPane.showMessageDialog(contentPane, "Iniciando sesion...");
		}
	}
}
