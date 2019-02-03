package jee.jpa.heritage;

import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("SSD")
public class SSD extends Stockage {
  private String nbTransistor;

  public String getNbTransistor() {
    return nbTransistor;
  }

  public void setNbTransistor(String nbTransistor) {
    this.nbTransistor = nbTransistor;
  }
}
