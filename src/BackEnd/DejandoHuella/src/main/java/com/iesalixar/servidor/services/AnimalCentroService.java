package com.iesalixar.servidor.services;

import java.util.List;
import java.util.Optional;

import com.iesalixar.servidor.model.AnimalCentro;

public interface AnimalCentroService {

	public AnimalCentro insertAnimalCentro(AnimalCentro animalCentro);

	public List<AnimalCentro> getAllAnimalesCentro();

	public Optional<AnimalCentro> findAnimalCentroById(Long id);

	public AnimalCentro actualizarAnimalCentro(AnimalCentro animalCentro);

}
