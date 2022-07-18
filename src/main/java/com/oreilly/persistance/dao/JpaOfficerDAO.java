package com.oreilly.persistance.dao;

import com.oreilly.persistance.entities.Officer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("JpaQlInspection")
@Repository // JPA - does the exception translation + Autowiring
public class JpaOfficerDAO  implements OfficerDAO {

    // @PersistanceContext => Spring automatically plugs in an EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    // The officer returned will have and assigned primary key
    @Override
    public Officer save(Officer officer) {
        entityManager.persist(officer);
        return officer;
    };

    // Optiona because we will get = Empty Optional or an Optional<Officer>
    @Override
    public Optional<Officer> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Officer.class, id));
    }

    // JPQL Java Persistence Query Language
    @Override
    public List<Officer> findAll() {
        return entityManager
                .createQuery("SELECT o FROM Officer o", Officer.class)
                .getResultList();
    }

    @Override
    public long count() {
        return entityManager
                .createQuery("SELECT COUNT(o.id) FROM Officer o", Long.class)
                .getSingleResult();
    }

    @Override
    public void delete(Officer officer) {
        entityManager.remove(officer);
    }

    @Override
    public boolean existsById(Integer id) {
        Object result = entityManager
                .createQuery("SELECT 1 FROM Officer o WHERE o.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        return result != null;
    }
}
