package jee.jpa;

import org.junit.After;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Transient;

import static jee.jpa.TechIOCommands.msg;
import static jee.jpa.TechIOCommands.success;
import static org.assertj.core.api.Assertions.assertThat;

public class JPATest {

    private EntityManager entityManager;
    private EntityManagerFactory projet;

    @After
    public void close() {
        entityManager.close();
        projet.close();
    }

    @Test
    public void testSimplePersist() {
        try {
            projet = Persistence.createEntityManagerFactory("projet");
            entityManager = projet.createEntityManager();
            entityManager.setFlushMode(FlushModeType.COMMIT);

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
            assertThat(id).as("L'identifiant doit être auto-incrémenté").isNotEqualTo(0);

            try {
                final Class<Product> productClass = Product.class;
                final Transient annotation = productClass.getDeclaredField("computedString").getAnnotation(Transient.class);
                if(annotation == null) {
                    throw new AssertionError("Le champ 'computedString' ne doit pas être sauvegardé");
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError("Le champ 'computedString' doit être présent");
            }

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
}
