package jee.jpa.heritage;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("HDD")
public class DisqueDur extends Stockage {
  @Column(nullable = false)
  private int tourMin;

  public int getTourMin() {
    return tourMin;
  }

  public void setTourMin(int tourMin) {
    this.tourMin = tourMin;
  }
}
