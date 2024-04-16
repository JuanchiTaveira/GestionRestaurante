package com.example;

import com.example.controller.EmpleadoController;
import com.example.controller.ReservaController;
import com.example.model.Empleado;
import com.example.model.Reserva;
import com.example.model.UsuarioReserva;
import com.example.views.LoginPanel;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalDate;

public class GestionRestaurante extends JFrame {

	private final EmpleadoController empleadoController = new EmpleadoController();
	private final ReservaController reservaController = new ReservaController();

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

	public GestionRestaurante() {
		empleadoController.insertarEmpleado(new Empleado("juan", "juan"));
		reservaController.insertarReserva(new Reserva(new UsuarioReserva("Juan", "Taveira", "+34112233", "juan@juan.com"), 1, LocalDate.now(), Reserva.Horario.ALMUERZO, 5));
		reservaController.insertarReserva(new Reserva(new UsuarioReserva("Leo", "Messi", "+34112233", "messi@messi.com"), 5, LocalDate.now(), Reserva.Horario.ALMUERZO, 5));

		initialize();
	}

	private void initialize() {
		setTitle("JAJ - Gestión de Restaurante");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centra la ventana en la pantalla

		LoginPanel loginPanel = new LoginPanel(this);
		add(loginPanel, BorderLayout.CENTER);
	}
}
