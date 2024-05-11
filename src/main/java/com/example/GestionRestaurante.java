package com.example;

import com.example.controller.ClienteController;
import com.example.controller.EmpleadoController;
import com.example.controller.MesaController;
import com.example.controller.ReservaController;
import com.example.model.Empleado;
import com.example.model.Reserva;
import com.example.model.Cliente;
import com.example.views.LoginPanel;
import com.github.javafaker.Faker;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.Locale;

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
		setTitle("JAJ - Gestión de Restaurantes");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/logo_pestana.png"));

		new LoginPanel(this);
	}

	private void insertTestData() {
		EmpleadoController empleadoController = new EmpleadoController();
		ReservaController reservaController = new ReservaController();
		MesaController mesaController = new MesaController();
		ClienteController clienteController = new ClienteController();

		Empleado empleado = new Empleado("admin", "admin", "Admin", "Admin", "12345678", true);
		Empleado empleado1 = new Empleado("juan", "juan", "Juan", "Taveira", "Y123456Z", false);
		Empleado empleado2 = new Empleado("asier", "asier", "Asier", "Azpiazu", "12345678Z", false);
		empleadoController.insertarEmpleado(empleado);
		empleadoController.insertarEmpleado(empleado1);
		empleadoController.insertarEmpleado(empleado2);
		reservaController.insertarReserva(new Reserva(new Cliente("Juan", "Taveira", "+34112233", "juan@juan.com"), 1, LocalDate.now(), Reserva.Horario.ALMUERZO, 5, empleado));
		reservaController.insertarReserva(new Reserva(new Cliente("Leo", "Messi", "+34112233", "messi@messi.com"), 5, LocalDate.now(), Reserva.Horario.ALMUERZO, 4, empleado2));
		reservaController.insertarReserva(new Reserva(new Cliente("Asier", "Azpiazu", "+34666000666", "asier@gmail.com"), 2, LocalDate.now(), Reserva.Horario.ALMUERZO, 3, empleado1));

		Faker faker = new Faker(new Locale("es"));

		// Crear empleados
		for (int i = 0; i < 10; i++) {
			String usuario = faker.name().username();
			String password = faker.internet().password();
			String nombre = faker.name().firstName();
			String apellido = faker.name().lastName();
			String dni = faker.idNumber().valid();
			boolean admin = i == 0; // El primer empleado será admin

			empleadoController.insertarEmpleado(new Empleado(usuario, password, nombre, apellido, dni, admin));
		}

		// Crear reservas
		for (int i = 0; i < 50; i++) {
			String clienteNombre = faker.name().firstName();
			String clienteApellido = faker.name().lastName();
			String clienteTelefono = faker.phoneNumber().phoneNumber();
			String clienteCorreo = faker.internet().emailAddress();
			Cliente cliente = new Cliente(clienteNombre, clienteApellido, clienteTelefono, clienteCorreo);
			clienteController.insertarClienteReserva(cliente);

			int numeroMesa = faker.number().numberBetween(1, 13);
			LocalDate dia = LocalDate.now().plusDays(faker.number().numberBetween(0, 5));
			Reserva.Horario horario = faker.options().option(Reserva.Horario.class);

			int cantidadPersonas = faker.number().numberBetween(1, mesaController.maxPersonasMesa(numeroMesa));

			// Asignar un empleado al azar a la reserva
			Empleado empleadoRandom = empleadoController.getEmpleadoById(String.valueOf(faker.number().numberBetween(1, 10)));

			Reserva reserva = new Reserva(cliente, numeroMesa, dia, horario, cantidadPersonas, empleadoRandom);
			reservaController.insertarReserva(reserva);
		}
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
