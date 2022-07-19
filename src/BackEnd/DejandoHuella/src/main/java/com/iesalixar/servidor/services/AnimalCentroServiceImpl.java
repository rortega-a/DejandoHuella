package com.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.servidor.model.AnimalCentro;
import com.iesalixar.servidor.repository.AnimalCentroRepository;

@Service
public class AnimalCentroServiceImpl implements AnimalCentroService {

	@Autowired
	AnimalCentroRepository animalCentroRepo;

	@Override
	public AnimalCentro insertAnimalCentro(AnimalCentro animalCentro) {

		if (animalCentro != null) {
			return animalCentroRepo.save(animalCentro);
		}

		return null;
	}

	@Override
	public List<AnimalCentro> getAllAnimalesCentro() {

		// Obtengo el resultado a través del repositorio
		List<AnimalCentro> animalesCentroList = animalCentroRepo.findAll();

		// Verificando que he obtenido algo
		if (animalesCentroList != null && animalesCentroList.size() > 0) {

			return animalesCentroList;
		}

		// No he obtenido nada devuelvo una lista vacía (para no devolver nulo)
		return new ArrayList<AnimalCentro>();

	}

	@Override
	public Optional<AnimalCentro> findAnimalCentroById(Long id) {

		if (id != null) {
			return animalCentroRepo.findById(id);
		} else {
			return null;
		}
	}

	@Override
	public AnimalCentro actualizarAnimalCentro(AnimalCentro animalCentro) {

		if (animalCentro == null || animalCentro.getId_animal() == null || animalCentro.getNombre() == null) {
			return null;
		}

		return animalCentroRepo.save(animalCentro);
	}

}
