package thanh.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("jpa-crud-sqlserver");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
