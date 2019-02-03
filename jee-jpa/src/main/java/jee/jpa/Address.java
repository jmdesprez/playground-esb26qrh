package jee.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
  @Id
  private long id;
}
