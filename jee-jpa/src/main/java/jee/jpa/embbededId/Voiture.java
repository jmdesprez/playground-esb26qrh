package jee.jpa.embbededId;

import javax.persistence.*;

@Entity
public class Voiture {
    @EmbeddedId
    private VoitureId id;

    @Column(name = "nom")
    private String name;

    public VoitureId getId() {
        return id;
    }

    public void setId(VoitureId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
