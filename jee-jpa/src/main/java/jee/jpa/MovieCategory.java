package jee.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
public class MovieCategory {
    @Id
    private int id;

    @ManyToMany
    @JoinTable(
            name="MOV_CAT",
            joinColumns=@JoinColumn(name="cat_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="mov_id", referencedColumnName="id"))
    private List<Movie> movies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
