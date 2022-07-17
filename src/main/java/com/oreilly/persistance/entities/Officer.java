package com.oreilly.persistance.entities;

import java.util.Objects;

public class Officer {
    private Integer id; // wrapper class (and not just int) because JPA wants a nullables primary key
    private Rank rank;
    private String firstName;
    private String lastName;

    // Required for JPA
    public Officer() {
    }

    // NO id, useful for creating a new Officer, ID is set by the db
    public Officer(Rank rank, String firstName, String lastName) {
        this.rank = rank;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Useful for retrieving a row from db and converting it into an Officer
    public Officer(Integer id, Rank rank, String firstName, String lastName) {
        this.id = id;
        this.rank = rank;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Officer officer = (Officer) o;
        return Objects.equals(id, officer.id) && rank == officer.rank && Objects.equals(firstName, officer.firstName) && Objects.equals(lastName, officer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rank, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Officer{" +
                "id=" + id +
                ", rank=" + rank +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
