package jee.jpa.heritage;

import javax.persistence.*;

@Entity
//@DiscriminatorColumn(name = "type_stockage")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Stockage {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private int capacite;

  @PreRemove
  public void updateTotalQuantity() {
    //
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCapacite() {
    return capacite;
  }

  public void setCapacite(int capacite) {
    this.capacite = capacite;
  }
}
