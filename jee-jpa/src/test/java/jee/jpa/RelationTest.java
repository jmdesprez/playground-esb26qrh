package jee.jpa;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RelationTest {

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
        entityManager.close();
        projet.close();
    }

    @Test
    public void testSimplePersist() {
        // GIVEN
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final Order toSave = new Order();

        toSave.setId(0);
        //toSave.setCustomer(customer);

        // WHEN
        entityManager.persist(toSave);
        final long id = toSave.getId();

        // THEN
        // L'id doit avoir changé
        assertThat(id).isNotEqualTo(0);

        final Customer customer = new Customer();
        customer.setId(0);
        customer.setFirstname("Alice");
        customer.setLastname("Doe");
        customer.setOrders(Lists.newArrayList(toSave));

        entityManager.persist(customer);
        toSave.setCustomer(customer);

        transaction.commit();

        // WHEN
        transaction.begin();
        final Order foundOrder = entityManager.find(Order.class, id);
        transaction.commit();

        // THEN
        assertThat(foundOrder.getId()).isEqualTo(id);
        assertThat(foundOrder.getCustomer().getFirstname()).isEqualTo("Alice");

        System.out.println(Arrays.toString(customer.getOrders().toArray()));
    }

}
