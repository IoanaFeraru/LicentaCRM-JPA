package Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestGenerareSchemaSQL {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("persistenceUnit").createEntityManager();
    }
}