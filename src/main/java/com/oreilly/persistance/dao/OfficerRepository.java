package com.oreilly.persistance.dao;

import com.oreilly.persistance.entities.Officer;
import com.oreilly.persistance.entities.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {

    // Using JPA Palette (from JPA Buddy) you can generate other working methods using VERBS and Columns of the entity
    List<Officer> findAllByRankAndLastNameContaining(Rank rank, String string);

    List<Officer> findByRank(Rank rank);

    List<Officer> findByRankAndFirstNameContains(Rank rank, String firstName);

}
