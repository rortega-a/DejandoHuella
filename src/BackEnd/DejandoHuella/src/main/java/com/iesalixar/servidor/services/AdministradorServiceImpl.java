package com.iesalixar.servidor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.servidor.model.Administrador;
import com.iesalixar.servidor.repository.AdministradorRepository;

@Service
public class AdministradorServiceImpl implements AdministradorService {

	@Autowired
	AdministradorRepository administradorRepo;

	@Override
	public Administrador insertAdministrador(Administrador administrador) {

		if (administrador != null && findAdministradorByUserName(administrador.getUserName()) == null
				&& findAdministradorByEmail(administrador.getEmail()) == null) {
			return administradorRepo.save(administrador);
		}

		if (findAdministradorByUserName(administrador.getUserName()) != null) {
			administrador.setUserName(null);
			return administrador;
		} else if (findAdministradorByEmail(administrador.getEmail()) != null) {
			administrador.setEmail(null);
			return administrador;
		} else {
			return null;
		}
	}

	@Override
	public Administrador findAdministradorByUserName(String userName) {

		if (userName != null) {
			return administradorRepo.findByUserName(userName);
		}

		return null;
	}

	@Override
	public Administrador findAdministradorByEmail(String email) {

		if (email != null) {
			return administradorRepo.findByEmail(email);
		}

		return null;
	}

}
