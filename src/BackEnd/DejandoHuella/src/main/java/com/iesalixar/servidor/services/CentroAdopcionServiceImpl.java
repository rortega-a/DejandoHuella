package com.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.servidor.model.CentroAdopcion;
import com.iesalixar.servidor.repository.CentroAdopcionRepository;

@Service
public class CentroAdopcionServiceImpl implements CentroAdopcionService {

	@Autowired
	CentroAdopcionRepository centroAdopcionRepo;

	@Override
	public CentroAdopcion insertCentroAdopcion(CentroAdopcion centroAdopcion) {

		if (centroAdopcion != null && findCentroByUserName(centroAdopcion.getUserName()) == null
				&& findCentroByEmail(centroAdopcion.getEmail()) == null
				&& findCentroByNif(centroAdopcion.getNif()) == null) {

			return centroAdopcionRepo.save(centroAdopcion);
		}

		if (findCentroByUserName(centroAdopcion.getUserName()) != null) {
			centroAdopcion.setUserName(null);
			return centroAdopcion;
		} else if (findCentroByEmail(centroAdopcion.getEmail()) != null) {
			centroAdopcion.setEmail(null);
			return centroAdopcion;
		} else if (findCentroByNif(centroAdopcion.getNif()) != null) {
			centroAdopcion.setNif(null);
			return centroAdopcion;
		} else {
			return null;
		}
	}

	@Override
	public Optional<CentroAdopcion> findCentroAdopcionById(Long id) {

		if (id != null) {
			return centroAdopcionRepo.findById(id);
		} else {
			return null;
		}
	}

	@Override
	public CentroAdopcion actualizarCentroAdopcion(CentroAdopcion centroAdopcion) {

		if (centroAdopcion == null || centroAdopcion.getId_rol() == null || centroAdopcion.getNombre() == null) {
			return null;
		}

		return centroAdopcionRepo.save(centroAdopcion);
	}

	@Override
	public List<CentroAdopcion> getAllCentrosAdopcion() {

		// Obtengo el resultado a través del repositorio
		List<CentroAdopcion> centrosAdopcionList = centroAdopcionRepo.findAll();

		// Verificando que he obtenido algo
		if (centrosAdopcionList != null && centrosAdopcionList.size() > 0) {

			return centrosAdopcionList;
		}

		// No he obtenido nada devuelvo una lista vacía (para no devolver nulo)
		return new ArrayList<CentroAdopcion>();
	}

	@Override
	public CentroAdopcion findCentroByUserName(String userName) {

		if (userName != null) {
			return centroAdopcionRepo.findByUserName(userName);
		}

		return null;
	}

	@Override
	public CentroAdopcion findCentroByEmail(String email) {

		if (email != null) {
			return centroAdopcionRepo.findByEmail(email);
		}

		return null;
	}

	@Override
	public CentroAdopcion findCentroByNif(String nif) {

		if (nif != null) {
			return centroAdopcionRepo.findByNif(nif);
		}

		return null;
	}

}
