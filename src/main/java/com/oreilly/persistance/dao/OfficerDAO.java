package com.oreilly.persistance.dao;

import com.oreilly.persistance.entities.Officer;

import java.util.List;
import java.util.Optional;

public interface OfficerDAO {
    // The officer returned will have and assigned primary key
    Officer save(Officer officer);

    // Optiona because we will get = Empty Optional or an Optional<Officer>
    Optional<Officer> findById(Integer id);

    List<Officer> findAll();

    long count();

    void delete(Officer officer);

    boolean existsById(Integer id);
}
