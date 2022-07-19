package com.iesalixar.servidor.services;

import java.util.Optional;

import com.iesalixar.servidor.model.Animal;

public interface AnimalService {

	public Animal insertAnimal(Animal animal);

	public Optional<Animal> findAnimalById(Long id);

	public Animal actualizarAnimal(Animal animal);

	public void borrarAnimal(Animal animal);

}
