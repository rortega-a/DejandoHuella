package com.iesalixar.servidor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.servidor.model.Adoptan;
import com.iesalixar.servidor.repository.AdoptanRepository;

@Service
public class AdoptanServiceImpl implements AdoptanService {

	@Autowired
	AdoptanRepository adoptanRepo;

	@Override
	public Adoptan insertAdoptado(Adoptan adoptado) {

		if (adoptado != null) {
			return adoptanRepo.save(adoptado);
		}

		return null;
	}

}
