package com.iesalixar.servidor.services;

import java.util.List;
import java.util.Optional;

import com.iesalixar.servidor.model.AnimalPerdido;

public interface AnimalPerdidoService {

	public AnimalPerdido insertAnimalPerdido(AnimalPerdido animalPerdido);

	public List<AnimalPerdido> getAllAnimalesPerdidos();

	public Optional<AnimalPerdido> findAnimalPerdidoById(Long id);

	public AnimalPerdido actualizarAnimalPerdido(AnimalPerdido animalPerdido);

}
