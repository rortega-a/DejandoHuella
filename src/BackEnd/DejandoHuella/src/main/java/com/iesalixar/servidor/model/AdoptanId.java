package com.iesalixar.servidor.model;

import java.io.Serializable;
import java.util.Objects;

public class AdoptanId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long animalCentro;
	private Long usuario;

	public AdoptanId() {
		// TODO Auto-generated constructor stub
	}

	public Long getAnimalCentro() {
		return animalCentro;
	}

	public void setAnimalCentro(Long animalCentro) {
		this.animalCentro = animalCentro;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(animalCentro, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdoptanId other = (AdoptanId) obj;
		return Objects.equals(animalCentro, other.animalCentro) && Objects.equals(usuario, other.usuario);
	}

}
