package com.iesalixar.servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.servidor.model.AnimalCentro;

@Repository
public interface AnimalCentroRepository extends JpaRepository<AnimalCentro, Long> {

}
