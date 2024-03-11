package com.example;

import com.example.controller.ReservaController;
import com.example.controller.EmpleadoController;
import com.example.model.Reserva;
import com.example.model.Empleado;
import com.example.model.UsuarioReserva;
import com.example.views.LoginPanel;

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

public class GestionRestaurante extends JFrame {

	private final EmpleadoController empleadoController = new EmpleadoController();
	private final ReservaController reservaController = new ReservaController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                GestionRestaurante window = new GestionRestaurante();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the application.
	 */
	public GestionRestaurante() {
		empleadoController.insertarEmpleado(new Empleado("juan", "juan"));
		reservaController.insertarReserva(new Reserva(new UsuarioReserva("Juan", "Taveira", "+34112233", "juan@juan.com"), 1, LocalDate.now(), Reserva.Horario.ALMUERZO, 5));

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

		LoginPanel loginPanel = new LoginPanel(this);
		add(loginPanel, BorderLayout.CENTER);
	}
}
