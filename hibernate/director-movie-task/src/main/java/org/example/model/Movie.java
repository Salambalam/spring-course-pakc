package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_production")
    private int yearOfProduction;

    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Director creator;

    public Movie() {
    }

    public Movie(Director director, String name, int yearOfProduction) {
        this.name = name;
        this.yearOfProduction = yearOfProduction;
        this.creator = director;
    }


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Director getCreator() {
        return creator;
    }

    public void setCreator(Director creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.yearOfProduction;
    }
}
