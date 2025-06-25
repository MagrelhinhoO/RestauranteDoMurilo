package com.restaurante.repository;

import com.restaurante.model.Cliente;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ClienteRepository {
    private EntityManager em = JPAUtil.getEntityManager();

    public void salvar(Cliente cliente) {
        em.getTransaction().begin();
        if (cliente.getId() == 0) {
            em.persist(cliente);
        } else {
            em.merge(cliente);
        }
        em.getTransaction().commit();
    }

    public Cliente buscarPorId(int id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> listarTodos() {
        return em.createQuery("FROM Cliente", Cliente.class).getResultList();
    }

    public void deletar(int id) {
        em.getTransaction().begin();
        Cliente cliente = buscarPorId(id);
        if (cliente != null) {
            em.remove(cliente);
        }
        em.getTransaction().commit();
    }
}