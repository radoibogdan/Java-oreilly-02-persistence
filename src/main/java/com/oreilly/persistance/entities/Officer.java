package com.oreilly.persistance.entities;

import javax.persistence.*;
import java.util.Objects;


@Entity // JPA : This class represents a database table
@Table(name = "officers") // JPA : Our tables is officers not officer so we need to use @Tables
public class Officer {
    @Id // JPA Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA
    // JPA VIP! wrapper class Integer (and not just int) because JPA wants a nullables primary key
    private Integer id;
    // JPA, default is ORDINAL which will sale the index of the rank
    @Enumerated(EnumType.STRING)
    private Rank rank;
    private String firstName;
    private String lastName;

    // Required for JPA
    public Officer() {}

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
        if (!(o instanceof Officer)) return false;

        Officer officer = (Officer) o;

        if (!id.equals(officer.id)) return false;
        if (rank != officer.rank) return false;
        if (firstName != null ? !firstName.equals(officer.firstName) : officer.firstName != null) return false;
        return lastName.equals(officer.lastName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + rank.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", rank, firstName, lastName);
    }
}
