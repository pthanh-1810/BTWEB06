package thanh.Dao.impl;

import jakarta.persistence.*;
import thanh.Dao.VideoDao;
import thanh.config.JPAConfig;
import thanh.entity.Video;

import java.util.List;

public class VideoDaoImpl implements VideoDao {
    @Override
    public void insert(Video v) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(v);
            tx.commit();
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public void update(Video v) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(v);
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
            Video v = em.find(Video.class, id);
            if(v != null) em.remove(v);
            tx.commit();
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public Video findById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(Video.class, id);
        } finally { em.close(); }
    }

    @Override
    public List<Video> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT v FROM Video v", Video.class).getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Video> findByCategory(int categoryId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT v FROM Video v WHERE v.category.id = :cid", Video.class)
                     .setParameter("cid", categoryId).getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Video> search(String keyword) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT v FROM Video v WHERE v.title LIKE :kw", Video.class)
                     .setParameter("kw", "%" + keyword + "%").getResultList();
        } finally { em.close(); }
    }
}
