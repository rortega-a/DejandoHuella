package com.iesalixar.servidor.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "adoptan")
@IdClass(AdoptanId.class)
public class Adoptan implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_animal_centro")
	private AnimalCentro animalCentro;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column(nullable = false)
	private String fecha_adopcion;

	public Adoptan() {
		// TODO Auto-generated constructor stub
	}

	public Adoptan(AnimalCentro animalCentro, Usuario usuario, String fecha_adopcion) {
		this.animalCentro = animalCentro;
		this.usuario = usuario;
		this.fecha_adopcion = fecha_adopcion;
	}

	public Adoptan(AnimalCentro animalCentro, Usuario usuario) {
		this.animalCentro = animalCentro;
		this.usuario = usuario;
	}

	public AnimalCentro getAnimalCentro() {
		return animalCentro;
	}

	public void setAnimalCentro(AnimalCentro animalCentro) {
		this.animalCentro = animalCentro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getFecha_adopcion() {
		return fecha_adopcion;
	}

	public void setFecha_adopcion(String fecha_adopcion) {
		this.fecha_adopcion = fecha_adopcion;
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
		Adoptan other = (Adoptan) obj;
		return Objects.equals(animalCentro, other.animalCentro) && Objects.equals(usuario, other.usuario);
	}

}
