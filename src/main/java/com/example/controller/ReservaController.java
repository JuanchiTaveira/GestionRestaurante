package com.example.controller;

import com.example.config.HibernateUtil;
import com.example.model.Reserva;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.List;

public class ReservaController {

    private final SessionFactory sessionFactory;
    MesaController mesaController = new MesaController();

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

    public int persistReserva(Reserva reserva) {
        try (Session session = sessionFactory.openSession()) {

            if (!verificarDisponibilidadMesa(reserva.getNumeroMesa(), reserva.getDia(), reserva.getHorario())) {
                return -1;
            }

            if (reserva.getDia().isBefore(LocalDate.now())) {
                return -2;
            }

            if(reserva.getNumeroPersonas() > mesaController.maxPersonasMesa(reserva.getNumeroMesa())) {
                return -3;
            }

            session.beginTransaction();
            session.merge(reserva);
            session.getTransaction().commit();
            System.out.println("Reserva registrada");
            return 0;
        }
    }

    public void eliminarReserva(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Reserva r WHERE r.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            System.out.println("Reserva eliminada con id: " + id);
        }
    }

    private Boolean verificarDisponibilidadMesa(Integer mesa, LocalDate dia, Reserva.Horario horario) {
        try (Session session = sessionFactory.openSession()) {
            Long count = session.createQuery("SELECT count(r) FROM Reserva r WHERE r.numeroMesa = :mesa AND r.dia = :dia AND r.horario = :horario", Long.class)
                    .setParameter("mesa", mesa)
                    .setParameter("dia", dia)
                    .setParameter("horario", horario)
                    .getSingleResult();

            return count == 0;
        }
    }

    public Reserva getReserva(Integer numeroMesa, LocalDate dia, Reserva.Horario horario) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT r FROM Reserva r WHERE r.numeroMesa = :numeroMesa AND r.dia = :dia AND r.horario = :horario", Reserva.class)
                    .setParameter("numeroMesa", numeroMesa)
                    .setParameter("dia", dia)
                    .setParameter("horario", horario)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println("ERROR: Reserva no encontrada.");
            return null;
        }
    }

    public List<Integer> getMesasReservadas(LocalDate dia, Reserva.Horario horario) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT r.numeroMesa FROM Reserva r WHERE r.dia = :dia AND r.horario = :horario", Integer.class)
                    .setParameter("dia", dia)
                    .setParameter("horario", horario)
                    .getResultList();
        } catch (NoResultException e) {
            System.err.println("No hay reservas en ese dia y horario.");
            return null;
        }
    }

    public List<Reserva> getReservasDia(LocalDate dia, Reserva.Horario horario) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT r FROM Reserva r WHERE r.dia = :dia AND r.horario = :horario", Reserva.class)
                    .setParameter("dia", dia)
                    .setParameter("horario", horario)
                    .getResultList();
        } catch (NoResultException e) {
            System.err.println("No hay reservas en ese dia y horario.");
            return null;
        }
    }
}
