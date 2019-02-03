package jee.jpa;

import javax.persistence.*;

public class Main {
  public static void main(String[] args) {
    EntityManagerFactory projet;
    projet = Persistence.createEntityManagerFactory("projet");

    EntityManager entityManager;
    entityManager = projet.createEntityManager();
    entityManager.setFlushMode(FlushModeType.COMMIT);

    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
//    entityManager.persist(...);

//    if (...){
      transaction.commit();
//    } else{
//      transaction.rollback();
//    }
    entityManager.close();
  }
}
