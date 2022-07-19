package com.iesalixar.servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.servidor.model.Adoptan;

@Repository
public interface AdoptanRepository extends JpaRepository<Adoptan, Long> {

}
