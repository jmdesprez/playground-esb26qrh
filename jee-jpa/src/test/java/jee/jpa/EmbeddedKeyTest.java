package jee.jpa;

import jee.jpa.embbededId.Voiture;
import jee.jpa.embbededId.VoitureId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

public class EmbeddedKeyTest {

    private EntityManager entityManager;
    private EntityManagerFactory projet;

    @BeforeEach
    public void init() {
        projet = Persistence.createEntityManagerFactory("projet");

        createEntityManager();
    }

    private void createEntityManager() {
        entityManager = projet.createEntityManager();
        entityManager.setFlushMode(FlushModeType.COMMIT);
    }

    @AfterEach
    public void close() {
        projet.close();
    }

    @Test
    public void testSimplePersist() {
        // GIVEN
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final Voiture toSave = new Voiture();
        final VoitureId id = new VoitureId();
        id.setFoo(0);
        id.setBar(0);
        toSave.setId(id);
        toSave.setName("Ma voiture");

        // WHEN
        entityManager.persist(toSave);
        final VoitureId voitureId = toSave.getId();
        transaction.commit();

        final VoitureId key = new VoitureId();
        key.setBar(voitureId.getBar());
        key.setFoo(voitureId.getFoo());
        final Voiture foundVoiture = entityManager.find(Voiture.class, key);

        // THEN
        assertThat(foundVoiture.getId()).isEqualTo(voitureId);
        assertThat(foundVoiture.getName()).isEqualTo("Ma voiture");
    }


}
