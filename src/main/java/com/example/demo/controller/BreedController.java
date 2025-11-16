package com.example.demo.controller;

import com.example.demo.entity.Breed;
import com.example.demo.repository.BreedRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/breeds")
public class BreedController{
    @Autowired
    private BreedRepository breedRepository;

    @GetMapping
    public List<Breed> getAllbreeds() {
       return breedRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Breed> getBreedById(@PathVariable Long id) {
        Optional<Breed> breed = breedRepository.findById(id);
        if(breed.isPresent()){
            return ResponseEntity.ok(breed.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Breed createBreed(@RequestBody Breed breed) {
        return breedRepository.save(breed);
    }

    @GetMapping("/search")
    public List<Breed> getBreedByName(@RequestParam String breed_name) {
        return breedRepository.findByName(breed_name);
    }

    @GetMapping("/older_than")
    public List<Breed> getBreedByWeight(@RequestParam int standard_weight) {
        return breedRepository.findByWeight(standard_weight);
    }
    
}