package za.co.discovery.assignment.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConfig {

    private static final String PERSISTENCE_UNIT_NAME = "interstellarPU";
    private static EntityManagerFactory emFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emFactory == null) {
            emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return emFactory;
    }

    public static void closeEntityManagerFactory() {
        if (emFactory != null && emFactory.isOpen()) {
            emFactory.close();
        }
    }
}
