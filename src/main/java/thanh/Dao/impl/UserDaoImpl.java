package thanh.Dao.impl;

import jakarta.persistence.*;
import thanh.Dao.UserDao;
import thanh.config.JPAConfig;
import thanh.entity.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void insert(User u) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(u);
            tx.commit();
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public void update(User u) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(u);
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
            User u = em.find(User.class, id);
            if(u != null) em.remove(u);
            tx.commit();
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public User findById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(User.class, id);
        } finally { em.close(); }
    }

    @Override
    public User findByUsername(String username) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username = :un", User.class);
            q.setParameter("un", username);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally { em.close(); }
    }

    @Override
    public List<User> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u", User.class).getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<User> search(String keyword) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username LIKE :kw", User.class)
                     .setParameter("kw", "%" + keyword + "%").getResultList();
        } finally { em.close(); }
    }
}
