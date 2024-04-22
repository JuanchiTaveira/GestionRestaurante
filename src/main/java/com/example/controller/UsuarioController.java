package com.example.controller;

import com.example.config.HibernateUtil;
import com.example.model.Cliente;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class UsuarioController {

    private final SessionFactory sessionFactory;

    public UsuarioController() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Cliente getUsuarioByCorreo(String correo) {
        try (Session session = sessionFactory.openSession()) {
            Cliente usuarioEncontrado = session.createQuery("SELECT u FROM Cliente u WHERE u.correo = :correo", Cliente.class)
                    .setParameter("correo", correo)
                    .getSingleResult();

            System.out.println("Usuario encontrado."); //TODO: cambiar por un logger

            return usuarioEncontrado;
        } catch (NoResultException e) {
            System.err.println("ERROR: Usuario no encontrado.");
            return null;
        }
    }

    public List<Cliente> getAllUsuarios() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT u FROM Cliente u", Cliente.class)
                    .getResultList();
        }
    }

    public Boolean insertarUsuarioReserva(Cliente usuario) {
        try (Session session = sessionFactory.openSession()) {

            if (!isValidUser(usuario)) {
                throw new Exception("ERROR: usuario no valido");
            }

            session.beginTransaction();
            session.persist(usuario);
            session.getTransaction().commit();
            System.out.println("Usuario registrado");

            return true;
        } catch (ConstraintViolationException e) {
            System.out.println("ERROR: usuario ya existente");
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: usuario invalido");
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
