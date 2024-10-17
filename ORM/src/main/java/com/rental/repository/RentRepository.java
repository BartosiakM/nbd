package com.rental.repository;

import com.rental.model.Rent;
import jakarta.persistence.*;

import java.util.List;

public class RentRepository implements Repository<Rent> {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("NBDUnit");

    @Override
    public Rent getByID(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Rent.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Rent> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Rent> query = em.createQuery("SELECT r FROM Rent r", Rent.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Rent add(Rent rent) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(rent);
            transaction.commit();
            return rent;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(Rent rent) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Rent attachedRent = em.find(Rent.class, rent.getRentId());
            if (attachedRent != null) {
                em.remove(attachedRent);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Rent rent) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(rent);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
