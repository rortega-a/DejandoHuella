package com.iesalixar.servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.servidor.model.AnimalPerdido;

@Repository
public interface AnimalPerdidoRepository extends JpaRepository<AnimalPerdido, Long> {

}
