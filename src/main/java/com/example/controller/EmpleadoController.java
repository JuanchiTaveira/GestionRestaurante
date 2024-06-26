package com.example.controller;

import com.example.config.HibernateUtil;
import com.example.model.Empleado;
import com.example.utils.PasswordUtil;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmpleadoController {

    private final SessionFactory sessionFactory;
    public static Empleado authUser;

    public EmpleadoController() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Boolean iniciarSesion(String usuario, String password) {
        Empleado result = getEmpleado(usuario, PasswordUtil.hashPassword(password));

        authUser = result;

        return result != null;
    }

    public Empleado getEmpleado(String usuario, String password) {
        try (Session session = sessionFactory.openSession()) {
            Empleado empleadoEncontrado = session.createQuery("SELECT u FROM Empleado u WHERE u.usuario = :usuario AND u.password = :password", Empleado.class)
                    .setParameter("usuario", usuario)
                    .setParameter("password", password)
                    .getSingleResult();

            System.out.println("Sesion iniciada correctamente.");

            return empleadoEncontrado;
        } catch (NoResultException e) {
            System.err.println("ERROR: Usuario o contraseña incorrectos.");
            return null;
        }
    }

    public List<Empleado> getAllEmpleados() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT u FROM Empleado u", Empleado.class)
                    .getResultList();
        }
    }

    public void insertarEmpleado(Empleado empleado) {
        try (Session session = sessionFactory.openSession()) {
            empleado.setPassword(PasswordUtil.hashPassword(empleado.getPassword()));

            session.beginTransaction();
            session.persist(empleado);
            session.getTransaction().commit();

            System.out.println("Empleado registrado");
        }
    }

    public void eliminarEmpleado(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Empleado admin = getEmpleadoByUsuario("admin");

            // Actualizar todas las reservas asociadas al empleado
            session.createQuery("UPDATE Reserva r SET r.empleado = :admin WHERE r.empleado.id = :id")
                    .setParameter("admin", admin)
                    .setParameter("id", id)
                    .executeUpdate();

            session.createQuery("DELETE FROM Empleado u WHERE u.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            session.getTransaction().commit();
            System.out.println("Empleado eliminado con id: " + id);
        }
    }

    public Empleado getEmpleadoById(String id) {
        try (Session session = sessionFactory.openSession()) {
            Empleado empleadoEncontrado = session.createQuery("SELECT u FROM Empleado u WHERE u.id = :id", Empleado.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return empleadoEncontrado;
        } catch (NoResultException e) {
            System.err.println("ERROR: Usuario o contraseña incorrectos.");
            return null;
        }
    }

    public Empleado getEmpleadoByUsuario(String usuario) {
        try (Session session = sessionFactory.openSession()) {
            Empleado empleadoEncontrado = session.createQuery("SELECT u FROM Empleado u WHERE u.usuario = :usuario", Empleado.class)
                    .setParameter("usuario", usuario)
                    .getSingleResult();

            return empleadoEncontrado;
        } catch (NoResultException e) {
            System.err.println("Usuario no encontrado.");
            return null;
        }
    }

    public Boolean editarEmpleado(Empleado empleadoActualizado) {
        try (Session session = sessionFactory.openSession()) {
            empleadoActualizado.setPassword(PasswordUtil.hashPassword(empleadoActualizado.getPassword()));

            session.beginTransaction();
            session.merge(empleadoActualizado);
            session.getTransaction().commit();

            System.out.println("Empleado editado con id: " + empleadoActualizado.getId());
            return true;
        }
    }

    public long getEmpleadosAdminCount() {
        try (Session session = sessionFactory.openSession()) {
            long adminsCount = session.createQuery("SELECT u FROM Empleado u WHERE u.admin = true").stream().count();
            return adminsCount;
        }
    }
}
