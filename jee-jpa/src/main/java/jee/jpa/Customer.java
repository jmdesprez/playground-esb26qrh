package jee.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer",
        indexes = @Index(
                name = "idx_customer_first_last_name",
                columnList = "firstname, lastname"))
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToOne(fetch=FetchType.LAZY, cascade = {
          CascadeType.ALL
  })
  private Address address;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname", nullable = false)
  private String lastname;

//  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
  @OneToMany()
  @JoinTable (
          name="CUSTOMER_ORDERS",
          joinColumns={ @JoinColumn(name="cust_id", referencedColumnName="id") },
          inverseJoinColumns={ @JoinColumn(name="order_id", referencedColumnName="id", unique=true) } )
  private List<Order> orders;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }
}
