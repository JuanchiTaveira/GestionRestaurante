package com.example.controller;

import com.example.config.HibernateUtil;
import com.example.model.Cliente;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class ClienteController {

    private final SessionFactory sessionFactory;

    public ClienteController() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Cliente getClienteByCorreo(String correo) {
        try (Session session = sessionFactory.openSession()) {
            Cliente clienteEncontrado = session.createQuery("SELECT u FROM Cliente u WHERE u.correo = :correo", Cliente.class)
                    .setParameter("correo", correo)
                    .getSingleResult();

            System.out.println("Cliente encontrado."); //TODO: cambiar por un logger

            return clienteEncontrado;
        } catch (NoResultException e) {
            System.err.println("ERROR: Cliente no encontrado.");
            return null;
        }
    }

    public List<Cliente> getAllClientes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT u FROM Cliente u", Cliente.class)
                    .getResultList();
        }
    }

    public Boolean insertarClienteReserva(Cliente cliente) {
        try (Session session = sessionFactory.openSession()) {

            if (!isValidUser(cliente)) {
                throw new Exception("ERROR: cliente no valido");
            }

            session.beginTransaction();
            session.persist(cliente);
            session.getTransaction().commit();
            System.out.println("Cliente registrado");

            return true;
        } catch (ConstraintViolationException e) {
            System.out.println("ERROR: cliente ya existente");
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: cliente invalido");
            return false;
        }
    }

    private Boolean isValidUser(Cliente cliente) {
        return !cliente.getNombre().isBlank()
                && !cliente.getApellido().isBlank()
                && !cliente.getCorreo().isBlank()
                && !cliente.getTelefono().isBlank();
    }
}
