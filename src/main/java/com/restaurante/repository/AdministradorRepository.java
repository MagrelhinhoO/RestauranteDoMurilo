package com.restaurante.repository;

import com.restaurante.model.Administrador;
import jakarta.persistence.EntityManager;
import java.util.List;

public class AdministradorRepository {
    private EntityManager em = JPAUtil.getEntityManager();

    public void salvar(Administrador admin) {
        em.getTransaction().begin();
        if (admin.getId() == 0) {
            em.persist(admin);
        } else {
            em.merge(admin);
        }
        em.getTransaction().commit();
    }

    public Administrador buscarPorId(int id) {
        return em.find(Administrador.class, id);
    }

    public List<Administrador> listarTodos() {
        return em.createQuery("FROM Administrador", Administrador.class).getResultList();
    }

    public void deletar(int id) {
        em.getTransaction().begin();
        Administrador admin = buscarPorId(id);
        if (admin != null) {
            em.remove(admin);
        }
        em.getTransaction().commit();
    }
}