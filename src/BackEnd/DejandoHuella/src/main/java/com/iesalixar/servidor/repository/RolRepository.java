package com.iesalixar.servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.servidor.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

	public Rol findByUserName(String userName);

}
