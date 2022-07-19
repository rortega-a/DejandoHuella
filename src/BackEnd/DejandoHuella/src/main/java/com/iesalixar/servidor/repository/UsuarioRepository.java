package com.iesalixar.servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.servidor.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByUserName(String userName);

	public Usuario findByEmail(String email);

	public Usuario findByDni(String dni);

}
