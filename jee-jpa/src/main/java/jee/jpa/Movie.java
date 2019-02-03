package jee.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Movie {
    @Id
    private int id;

    @ManyToMany(mappedBy = "movies")
    private List<MovieCategory> categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<MovieCategory> categories) {
        this.categories = categories;
    }
}
