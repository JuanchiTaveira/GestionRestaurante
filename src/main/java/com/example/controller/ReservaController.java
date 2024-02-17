package com.example.controller;

import com.example.config.HibernateUtil;
import com.example.model.Reserva;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ReservaController {

    private static SessionFactory sessionFactory;

    public ReservaController() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Reserva getReserva(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT r FROM Reserva r WHERE r.id = :id", Reserva.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println("ERROR: Reserva no encontrada.");
            return null;
        }
    }

    public List<Reserva> getAllReservas() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT r FROM Reserva r", Reserva.class)
                    .getResultList();
        }
    }

    public void insertarReserva(Reserva reserva) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(reserva);
            session.getTransaction().commit();
            System.out.println("Reserva registrada");
        }
    }

    public void eliminarReserva(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Reserva r WHERE r.id = :id", Reserva.class)
                    .setParameter("id", id);
            session.getTransaction().commit();
            System.out.println("Reserva eliminada con id: " + id);
        }
    }

}
