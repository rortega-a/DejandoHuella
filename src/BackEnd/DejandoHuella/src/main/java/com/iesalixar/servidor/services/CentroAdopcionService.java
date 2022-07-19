package com.iesalixar.servidor.services;

import java.util.List;
import java.util.Optional;

import com.iesalixar.servidor.model.CentroAdopcion;

public interface CentroAdopcionService {

	public CentroAdopcion insertCentroAdopcion(CentroAdopcion centroAdopcion);

	public Optional<CentroAdopcion> findCentroAdopcionById(Long id);

	public CentroAdopcion actualizarCentroAdopcion(CentroAdopcion centroAdopcion);

	public List<CentroAdopcion> getAllCentrosAdopcion();

	public CentroAdopcion findCentroByUserName(String userName);

	public CentroAdopcion findCentroByEmail(String email);

	public CentroAdopcion findCentroByNif(String nif);

}
