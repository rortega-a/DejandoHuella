package com.iesalixar.servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.servidor.model.CentroAdopcion;

@Repository
public interface CentroAdopcionRepository extends JpaRepository<CentroAdopcion, Long> {

	public CentroAdopcion findByUserName(String userName);

	public CentroAdopcion findByEmail(String email);

	public CentroAdopcion findByNif(String nif);
}
