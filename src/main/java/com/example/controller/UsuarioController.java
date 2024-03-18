package com.example.controller;

import com.example.config.HibernateUtil;
import com.example.model.UsuarioReserva;
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

    public UsuarioReserva getUsuarioByCorreo(String correo) {
        try (Session session = sessionFactory.openSession()) {
            UsuarioReserva usuarioEncontrado = session.createQuery("SELECT u FROM UsuarioReserva u WHERE u.correo = :correo", UsuarioReserva.class)
                    .setParameter("correo", correo)
                    .getSingleResult();

            System.out.println("Usuario encontrado."); //TODO: cambiar por un logger

            return usuarioEncontrado;
        } catch (NoResultException e) {
            System.err.println("ERROR: Usuario no encontrado.");
            return null;
        }
    }

    public List<UsuarioReserva> getAllUsuarios() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT u FROM UsuarioReserva u", UsuarioReserva.class)
                    .getResultList();
        }
    }

    public Boolean insertarUsuarioReserva(UsuarioReserva usuario) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(usuario);
            session.getTransaction().commit();
            System.out.println("Usuario registrado");
            return true;
        } catch (ConstraintViolationException e) {
            System.out.println("ERROR: usuario ya existente");
            return false;
        }
    }
}
