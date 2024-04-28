package com.example.controller;

import com.example.config.HibernateUtil;
import com.example.model.Empleado;
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
        Empleado result = getEmpleado(usuario, password);

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
            session.beginTransaction();
            session.persist(empleado);
            session.getTransaction().commit();
            System.out.println("Empleado registrado");
        }
    }

    public void eliminarEmpleado(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Empleado u WHERE u.id = :id", Empleado.class)
                    .setParameter("id", id);
            session.getTransaction().commit();
            System.out.println("Empleado eliminado con id: " + id);
        }
    }

}
