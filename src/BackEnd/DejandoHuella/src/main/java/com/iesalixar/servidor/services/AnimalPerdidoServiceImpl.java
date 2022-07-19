package com.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.servidor.model.AnimalPerdido;
import com.iesalixar.servidor.repository.AnimalPerdidoRepository;

@Service
public class AnimalPerdidoServiceImpl implements AnimalPerdidoService {

	@Autowired
	AnimalPerdidoRepository animalPerdidoRepo;

	@Override
	public AnimalPerdido insertAnimalPerdido(AnimalPerdido animalPerdido) {

		if (animalPerdido != null) {
			return animalPerdidoRepo.save(animalPerdido);
		}

		return null;
	}

	@Override
	public List<AnimalPerdido> getAllAnimalesPerdidos() {

		// Obtengo el resultado a través del repositorio
		List<AnimalPerdido> animalesPerdidosList = animalPerdidoRepo.findAll();

		// Verificando que he obtenido algo
		if (animalesPerdidosList != null && animalesPerdidosList.size() > 0) {

			return animalesPerdidosList;
		}

		// No he obtenido nada devuelvo una lista vacía (para no devolver nulo)
		return new ArrayList<AnimalPerdido>();
	}

	@Override
	public Optional<AnimalPerdido> findAnimalPerdidoById(Long id) {

		if (id != null) {
			return animalPerdidoRepo.findById(id);
		} else {
			return null;
		}
	}

	@Override
	public AnimalPerdido actualizarAnimalPerdido(AnimalPerdido animalPerdido) {

		if (animalPerdido == null || animalPerdido.getId_animal() == null || animalPerdido.getNombre() == null) {
			return null;
		}

		return animalPerdidoRepo.save(animalPerdido);
	}

}
