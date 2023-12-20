package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

public class GenericDao<E> {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private Class<E> type;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("exemplo-java-hibernate");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        }
    }

    public GenericDao(Class<E> entidade) {
        this.type = entidade;
        em = emf.createEntityManager();
    }

    public GenericDao<E> begin() {
        em.getTransaction().begin();
        return this;
    }

    public GenericDao<E> end() {
        em.getTransaction().commit();
        return this;
    }

    public void create(E entity) {
        em.persist(entity);
    }

    public void update(E entity) {
        em.merge(entity);
    }

    public GenericDao<E> delete(long id) {
        GenericDao<E> dao = new GenericDao<E>(type);
        E entidade = dao.findById(id);
        em.remove(em.contains(entidade) ? entidade : em.merge(entidade));
        return this;
    }

    public E findById(Object id) {
        return em.find(type, id);
    }

    public List<E> findAll() {
        Query query = em.createQuery("FROM " + type.getSimpleName());
        return query.getResultList();
    }
}
