package jee.jpa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JPQLTest {

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
  public void testQuery() {
    final EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    final Product toSave = new Product();
    toSave.setId(0);
    toSave.setTitle("mon produit");

    // WHEN
    entityManager.persist(toSave);
    final long id = toSave.getId();
    transaction.commit();

    final TypedQuery<Product> query = entityManager.createQuery(
            "from Product p where p.id = :id",
            Product.class);
    query.setParameter("id", id);
    final Product product = query.getSingleResult();

    assertThat(product.getTitle()).isEqualTo("mon produit");
  }

  @Test
  public void testFetchQuery() {
    final EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    final Product toSave = new Product();
    toSave.setId(0);
    toSave.setTitle("mon produit");

    entityManager.persist(toSave);
    final long id = toSave.getId();
    transaction.commit();

    final TypedQuery<Product> query = entityManager.createQuery(
            "from Product m fetch all properties",
            Product.class);
    final Product product = query.getSingleResult();

    assertThat(product.getTitle()).isEqualTo("mon produit");
  }

  @Test
  public void testNamedQuery() {
    final EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    final Product toSave = new Product();
    toSave.setId(0);
    toSave.setTitle("mon produit");
    toSave.setStock(25);

    entityManager.persist(toSave);
    final long id = toSave.getId();
    transaction.commit();

    final TypedQuery<Product> query = entityManager.createNamedQuery("low stock",
            Product.class);
    final List<Product> products = query.getResultList();

    assertThat(products.get(0).getTitle()).isEqualTo("mon produit");
  }

  @Test
  public void testNativeQuery() {
    final EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    Query query = entityManager.createNativeQuery(
            "delete from produits");
    int deletedCount = query.executeUpdate();
    transaction.commit();
  }

  @Test
  public void testCriteria() {
    final EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    final Product toSave = new Product();
    toSave.setId(0);
    toSave.setTitle("mon produit");
    toSave.setStock(25);

    entityManager.persist(toSave);
    final long id = toSave.getId();
    transaction.commit();

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Product> q = cb.createQuery(Product.class);
    q.select(q.from(Product.class));

    TypedQuery<Product> query = entityManager.createQuery(q);
    List<Product> products = query.getResultList();

    assertThat(products.get(0).getTitle()).isEqualTo("mon produit");

  }
}
