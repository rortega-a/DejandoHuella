package com.iesalixar.servidor.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.servidor.model.Animal;
import com.iesalixar.servidor.repository.AnimalRepository;

@Service
public class AnimalServiceImpl implements AnimalService {

	@Autowired
	AnimalRepository animalRepo;

	@Override
	public Animal insertAnimal(Animal animal) {

		if (animal != null) {
			return animalRepo.save(animal);
		}

		return null;
	}

	@Override
	public Optional<Animal> findAnimalById(Long id) {

		if (id != null) {
			return animalRepo.findById(id);
		} else {
			return null;
		}
	}

	@Override
	public Animal actualizarAnimal(Animal animal) {

		if (animal == null || animal.getId_animal() == null || animal.getNombre() == null) {
			return null;
		}

		return animalRepo.save(animal);
	}

	@Override
	public void borrarAnimal(Animal animal) {

		if (animal != null && animal.getId_animal() != null) {
			animalRepo.delete(animal);
		}

	}

}
