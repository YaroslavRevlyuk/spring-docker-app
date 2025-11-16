package com.example.demo.repository;
import com.example.demo.entity.Breed;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class BreedRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Breed> findAll() {
        String jpql = "SELECT b FROM Breed b";
        TypedQuery<Breed> query = entityManager.createQuery(jpql, Breed.class);
        return query.getResultList();
    }

    public Optional<Breed> findById(Long id){
        Breed breed = entityManager.find(Breed.class, id);
        return Optional.ofNullable(breed);
    }

    @Transactional
    public Breed save(Breed breed) {
        if (breed.getId() == null) {
            entityManager.persist(breed);
            return breed;
        } else {
            return entityManager.merge(breed);
        }

    }

    @Transactional
    public boolean deleteById(Long id) {
        Breed breed = entityManager.find(Breed.class, id);
        if (breed != null) {
            entityManager.remove(breed);
            return true;
        }
        return false;
    }

    public List<Breed> findByName(String breed_name)
    {
        String jpql = "SELECT b FROM b Breed WHERE b.breed_name=:breed_name";
        TypedQuery<Breed> query = entityManager.createQuery(jpql, Breed.class);
        query.setParameter("breed_name", breed_name);
        return query.getResultList();
    }

    public List<Breed> findByWeight(float standard_weight)
    {
        String jpql = "SELECT c FROM c Cat WHERE c.standard_weight>:standard_weight";
        TypedQuery<Breed> query = entityManager.createQuery(jpql, Breed.class);
        query.setParameter("standard_weight", standard_weight);
        return query.getResultList();
    }
}
