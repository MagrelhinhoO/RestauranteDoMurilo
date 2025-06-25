package com.restaurante.repository;

import com.restaurante.model.Mesa;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MesaRepository {
    private EntityManager em = JPAUtil.getEntityManager();

    public void salvar(Mesa mesa) {
        em.getTransaction().begin();
        if (mesa.getId() == 0) {
            em.persist(mesa);
        } else {
            em.merge(mesa);
        }
        em.getTransaction().commit();
    }

    public Mesa buscarPorId(int id) {
        return em.find(Mesa.class, id);
    }

    public List<Mesa> listarTodas() {
        return em.createQuery("FROM Mesa", Mesa.class).getResultList();
    }

    public List<Mesa> listarDisponiveis() {
        return em.createQuery("FROM Mesa WHERE status = 'DISPONIVEL'", Mesa.class).getResultList();
    }

    public void deletar(int id) {
        em.getTransaction().begin();
        Mesa mesa = buscarPorId(id);
        if (mesa != null) {
            em.remove(mesa);
        }
        em.getTransaction().commit();
    }
}