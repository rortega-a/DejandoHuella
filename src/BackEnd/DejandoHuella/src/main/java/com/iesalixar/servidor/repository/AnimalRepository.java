package com.iesalixar.servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.servidor.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
