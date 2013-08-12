package jp.co.ctc_g.javaee_rest_sample.util.integration.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.rules.ExternalResource;

public class EntityManagerResource
        extends ExternalResource {

    private EntityManager em;
    private String persistenceUnitName;

    public EntityManagerResource(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    protected void before()
            throws Throwable {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.persistenceUnitName);
        em = emf.createEntityManager();
    }

        @Override
    protected void after() {
        if (em != null)
            em.close();
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
