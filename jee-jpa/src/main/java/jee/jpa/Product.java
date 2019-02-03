package jee.jpa;

import javax.persistence.*;

@Entity
@Table(name = "produits")
@NamedQuery(name = "low stock", query = "from Product where stock<=50")
public class Product {

  @Id
  @GeneratedValue
  private long id;

  @Column
  private int stock;

  @Column(name = "titre", length = 50, unique = true)
  private String title;

  @Transient
  private String computedString;

  // { autofold

  public Product() {
    this(0, "");
  }

  public Product(String title) {
    this(0, title);
  }

  public Product(int id, String title) {
    this.id = id;
    this.title = title;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String titre) {
    this.title = titre;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public String getComputedString() {
    return computedString;
  }

  public void setComputedString(String computedString) {
    this.computedString = computedString;
  }

  @Override
  public String toString() {
    return "Product{" +
            "id=" + id +
            ", title='" + title + '\'' +
            '}';
  }

  // }
}
