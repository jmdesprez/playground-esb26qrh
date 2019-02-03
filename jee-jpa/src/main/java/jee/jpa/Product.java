// { autofold
package jee.jpa;
// }

public class Product {

  private long id;

  private int stock;

  private String title;

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
