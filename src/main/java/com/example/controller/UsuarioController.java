package com.example.controller;

import com.example.config.HibernateUtil;
import com.example.model.Usuario;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UsuarioController {

    private static SessionFactory sessionFactory;

    public UsuarioController() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Boolean iniciarSesion(String usuario, String password) {
        Usuario result = getUsuario(usuario, password);

        return result != null;
    }

    public Usuario getUsuario(String usuario, String password) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.password = :password", Usuario.class)
                    .setParameter("usuario", usuario)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println("ERROR: Usuario o contraseña incorrecto.");
            return null;
        }
    }

    public List<Usuario> getAllUsuarios() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT u FROM Usuario u", Usuario.class)
                    .getResultList();
        }
    }

    public void insertarUsuario(Usuario usuario) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(usuario);
            session.getTransaction().commit();
            System.out.println("Usuario registrado");
        }
    }

    public void eliminarUsuario(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Usuario u WHERE u.id = :id", Usuario.class)
                    .setParameter("id", id);
            session.getTransaction().commit();
            System.out.println("Usuario eliminado con id: " + id);
        }
    }

}
