package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "School")
public class School {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "school_number")
    private int schoolNumber;

    @OneToOne
    @JoinColumn(name = "principal_id", referencedColumnName = "id")
    private Principal director;

    public School() {

    }

    public School(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public Principal getDirector() {
        return director;
    }

    public void setDirector(Principal director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolNumber=" + schoolNumber +
                ", director=" + director +
                '}';
    }
}
