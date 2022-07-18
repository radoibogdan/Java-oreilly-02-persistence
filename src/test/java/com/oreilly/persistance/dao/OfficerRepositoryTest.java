package com.oreilly.persistance.dao;

import com.oreilly.persistance.entities.Officer;
import com.oreilly.persistance.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// @Transaction - each query executed will be rolled back at the end of each test
@SpringBootTest
@Transactional
public class OfficerRepositoryTest {

    @Autowired
    private OfficerRepository dao;

    @Autowired
    private JdbcTemplate template;

    // Retrieve the current ids in the database
    private List<Integer> getIdsFromDatabase() {
        return template.query("SELECT id FROM officers", (resultSet, rowNumber) -> resultSet.getInt("id"));
    }

    @Test
    void  findAllByRankAndLastNameContainingTest() {
        List<Officer> officers = dao.findAllByRankAndLastNameContaining(Rank.CAPTAIN, "i");
        officers.forEach(System.out::println);
    }

    @Test
    public void save() {
        Officer officer = new Officer(Rank.ENSIGN, "Wesley", "Crusher");
        officer = dao.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    public void findByKnownOfficerId() {
        getIdsFromDatabase().forEach(id -> {
            Optional<Officer> officer = dao.findById(id);
            assertTrue(officer.isPresent());
            assertEquals(id, officer.get().getId());
        });
    }

    @Test
    public void findByUnknownOfficerId() {
        Optional<Officer> officer = dao.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    public void count() {
        assertEquals(dao.count(), 5);
    }

    @Test
    public void findAll() {
        List<String> dbNames = dao.findAll().stream()
                .map(Officer::getLastName)
                .collect(Collectors.toList());
        assertThat(dbNames).contains("Kirk", "Picard", "Sisko", "Janeway", "Archer");
    }

    @Test
    public void delete() {
        getIdsFromDatabase().forEach(id -> {
            Optional<Officer> officer = dao.findById(id);
            assertTrue(officer.isPresent());
            dao.delete(officer.get());
        });
        assertEquals(0, dao.count());
    }

    @Test
    public void allTheIdsCreatedAtDBCreationExist() {
        IntStream.rangeClosed(1, 5).forEach(id -> assertTrue(dao.existsById(id)));
    }
}
