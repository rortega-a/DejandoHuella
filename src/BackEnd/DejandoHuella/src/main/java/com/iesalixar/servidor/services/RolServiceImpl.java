package com.iesalixar.servidor.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.servidor.model.Rol;
import com.iesalixar.servidor.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	RolRepository rolRepo;

	@Override
	public Optional<Rol> findRolById(Long id) {

		if (id != null) {
			return rolRepo.findById(id);
		} else {
			return null;
		}
	}

	@Override
	public void borrarRol(Rol rol) {

		if (rol != null && rol.getId_rol() != null) {
			rolRepo.delete(rol);
		}

	}

}
