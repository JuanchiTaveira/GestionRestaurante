package com.example.controller;

import com.example.config.HibernateUtil;
import com.example.model.Mesa;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MesaController {
    private final SessionFactory sessionFactory;

    public MesaController() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Mesa getMesa(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT m FROM Mesa m WHERE m.id = :id", Mesa.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println("ERROR: Mesa no encontrada.");
            return null;
        }
    }

    public List<Mesa> getAllMesas() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT m FROM Mesa m", Mesa.class)
                    .getResultList();
        }
    }

    public Boolean crearMesa(Integer numeroMesa, Integer maxPersonas) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(Mesa.builder().numeroMesa(numeroMesa).maxPersonas(maxPersonas).build());
            session.getTransaction().commit();
            System.out.println("Mesa " + numeroMesa + " registrada");
            return true;
        }
    }

    public void eliminarMesa(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Mesa m WHERE m.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            System.out.println("Mesa eliminada con id: " + id);
        }
    }
}
