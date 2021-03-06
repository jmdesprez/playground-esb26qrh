package jee.jpa;

import jee.jpa.heritage.DisqueDur;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

public class HeritageTest {

    private EntityManager entityManager;
    private EntityManagerFactory projet;

    @Before
    public void init() {
        projet = Persistence.createEntityManagerFactory("projet");

        createEntityManager();
    }

    private void createEntityManager() {
        entityManager = projet.createEntityManager();
        entityManager.setFlushMode(FlushModeType.COMMIT);
    }

    @After
    public void close() {
        entityManager.close();
        projet.close();
    }

    @Test
    public void testSimplePersist() {
        // GIVEN
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final DisqueDur toSave = new DisqueDur();

        toSave.setId(0);
        toSave.setTourMin(7200);

        // WHEN
        entityManager.persist(toSave);
        final long id = toSave.getId();

        transaction.commit();

        // THEN
        // L'id doit avoir changé
        assertThat(id).isNotEqualTo(0);


    }

}
