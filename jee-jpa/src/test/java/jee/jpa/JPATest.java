package jee.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import static jee.jpa.TechIOCommands.msg;
import static jee.jpa.TechIOCommands.success;
import static org.assertj.core.api.Assertions.assertThat;

public class JPATest {

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
        try {
            // GIVEN
            final EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            final Product toSave = new Product();
            toSave.setId(0);
            toSave.setTitle("mon produit");

            // WHEN
            entityManager.persist(toSave);
            final long id = toSave.getId();
            transaction.commit();

            // THEN
            // L'id doit avoir changé
            assertThat(id).isNotEqualTo(0);

            // WHEN
            transaction.begin();
            final Product foundProduct = entityManager.find(Product.class, id);
            transaction.commit();

            // THEN
            assertThat(foundProduct.getId()).isEqualTo(id);
            assertThat(foundProduct.getTitle()).isEqualTo("mon produit");

            msg("Kudos 🌟", "GGWP !");

        } catch (AssertionError ae) {
            success(false);
            msg("Oups ! 🐞", ae.getMessage());
        }
    }

    @Test
    public void testUpdateAttachedEntity() {
        testUpdateEntity(false, false);
    }

    @Test
    public void testUpdateDetachedEntity() {
        testUpdateEntity(true, false);
    }

    public void testUpdateEntity(boolean detachEntity, boolean closeEntityManager) {
        // GIVEN
        final String baseTitle = "titre v1";
        final String updatedTitle = "titre v2";

        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final Product product = new Product();
        product.setId(0);
        product.setTitle(baseTitle);

        // WHEN
        entityManager.persist(product);
        final long id = product.getId();

        transaction.commit();

        if (detachEntity) {
            entityManager.detach(product);
        }

        if (closeEntityManager) {
            entityManager.close();
            createEntityManager();
        }

        product.setTitle(updatedTitle);

        // THEN
        // L'id doit avoir changé
        assertThat(id).isNotEqualTo(0);

        // WHEN
        final Product foundProduct = entityManager.find(Product.class, id);

        // THEN
        assertThat(foundProduct.getId()).isEqualTo(id);
        if (detachEntity) {
            assertThat(foundProduct.getTitle()).isEqualTo(baseTitle);
        } else {
            assertThat(foundProduct.getTitle()).isEqualTo(updatedTitle);
        }

    }
}
