package com.restaurante.repository;

import com.restaurante.enums.Status;
import com.restaurante.model.Reserva;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class ReservaRepository {
    private EntityManager em = JPAUtil.getEntityManager();

    public void salvar(Reserva reserva) {
        em.getTransaction().begin();
        if (reserva.getId() == 0) {
            em.persist(reserva);
        } else {
            em.merge(reserva);
        }
        em.getTransaction().commit();
    }

    public Reserva buscarPorId(int id) {
        return em.find(Reserva.class, id);
    }

    public List<Reserva> listarTodas() {
        return em.createQuery("FROM Reserva", Reserva.class).getResultList();
    }

    public List<Reserva> listarPorStatus(Status status) {
        return em.createQuery("FROM Reserva WHERE status = :status", Reserva.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Reserva> listarPorData(LocalDate data) {
        return em.createQuery("FROM Reserva WHERE data = :data", Reserva.class)
                .setParameter("data", data)
                .getResultList();
    }

    public List<Reserva> listarPorDataEStatus(LocalDate data, Status status) {
        return em.createQuery(
                        "FROM Reserva WHERE data = :data AND status = :status",
                        Reserva.class)
                .setParameter("data", data)
                .setParameter("status", status)
                .getResultList();
    }

    public void deletar(int id) {
        em.getTransaction().begin();
        Reserva reserva = buscarPorId(id);
        if (reserva != null) {
            em.remove(reserva);
        }
        em.getTransaction().commit();
    }
}