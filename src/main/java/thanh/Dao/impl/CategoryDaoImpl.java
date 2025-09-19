package thanh.Dao.impl;

import jakarta.persistence.*;
import thanh.Dao.CategoryDao;
import thanh.config.JPAConfig;
import thanh.entity.Category;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void insert(Category c) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(c);
            tx.commit();
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public void update(Category c) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(c);
            tx.commit();
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Category c = em.find(Category.class, id);
            if(c != null) em.remove(c);
            tx.commit();
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public Category findById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally { em.close(); }
    }

    @Override
    public List<Category> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Category> findByOwner(int ownerId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c WHERE c.owner.id = :oid", Category.class)
                     .setParameter("oid", ownerId).getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Category> search(String keyword) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c WHERE c.name LIKE :kw", Category.class)
                     .setParameter("kw", "%" + keyword + "%").getResultList();
        } finally { em.close(); }
    }
}
