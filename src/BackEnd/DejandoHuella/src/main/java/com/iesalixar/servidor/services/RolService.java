package com.iesalixar.servidor.services;

import java.util.Optional;

import com.iesalixar.servidor.model.Rol;

public interface RolService {

	public Optional<Rol> findRolById(Long id);

	public void borrarRol(Rol rol);

}
