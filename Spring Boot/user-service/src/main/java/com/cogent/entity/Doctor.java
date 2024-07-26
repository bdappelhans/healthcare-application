package com.cogent.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "rating", precision = 2, scale = 1)
    private BigDecimal rating;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    @JsonIgnoreProperties("doctors")
    private Specialty specialty;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnoreProperties("doctors")
    private City city;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, BigDecimal rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }

    public Doctor(String firstName, String lastName, BigDecimal rating, Specialty specialty, City city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.specialty = specialty;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
