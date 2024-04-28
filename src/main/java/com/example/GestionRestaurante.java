package com.example;

import com.example.controller.EmpleadoController;
import com.example.controller.MesaController;
import com.example.controller.ReservaController;
import com.example.model.Empleado;
import com.example.model.Reserva;
import com.example.model.Cliente;
import com.example.views.LoginPanel;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalDate;

public class GestionRestaurante extends JFrame {

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
		crearMesas();
		insertTestData();

		initialize();
	}

	private void initialize() {
		setTitle("JAJ - Gestión de Restaurante");

		new LoginPanel(this);
	}

	private void insertTestData() {
		EmpleadoController empleadoController = new EmpleadoController();
		ReservaController reservaController = new ReservaController();

		empleadoController.insertarEmpleado(new Empleado("admin", "admin", "Admin", "Admin", "12345678", true));
		empleadoController.insertarEmpleado(new Empleado("juan", "juan", "Juan", "Taveira", "Y123456Z", false));
		reservaController.insertarReserva(new Reserva(new Cliente("Juan", "Taveira", "+34112233", "juan@juan.com"), 1, LocalDate.now(), Reserva.Horario.ALMUERZO, 5));
		reservaController.insertarReserva(new Reserva(new Cliente("Leo", "Messi", "+34112233", "messi@messi.com"), 5, LocalDate.now(), Reserva.Horario.ALMUERZO, 4));
	}

	private void crearMesas() {
		MesaController mesaController = new MesaController();

		mesaController.crearMesa(1, 10);
		mesaController.crearMesa(2, 4);
		mesaController.crearMesa(3, 4);
		mesaController.crearMesa(4, 4);
		mesaController.crearMesa(5, 4);
		mesaController.crearMesa(6, 4);
		mesaController.crearMesa(7, 4);
		mesaController.crearMesa(8, 4);
		mesaController.crearMesa(9, 4);
		mesaController.crearMesa(10, 2);
		mesaController.crearMesa(11, 2);
		mesaController.crearMesa(12, 2);
		mesaController.crearMesa(13, 2);
	}
}
