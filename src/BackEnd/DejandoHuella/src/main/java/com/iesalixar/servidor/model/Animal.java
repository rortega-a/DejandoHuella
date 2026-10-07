package com.iesalixar.servidor.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "animal")
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_animal;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String raza;

	@Column(nullable = false)
	private String observaciones;

	@Column(nullable = false)
	private boolean adoptado;

	@Column(nullable = false)
	private String foto;

	public Long getId_animal() {
		return id_animal;
	}

	public void setId_animal(Long id_animal) {
		this.id_animal = id_animal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isAdoptado() {
		return adoptado;
	}

	public void setAdoptado(boolean adoptado) {
		this.adoptado = adoptado;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adoptado, foto, id_animal, nombre, observaciones, raza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return adoptado == other.adoptado && Objects.equals(foto, other.foto)
				&& Objects.equals(id_animal, other.id_animal) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(observaciones, other.observaciones) && Objects.equals(raza, other.raza);
	}

}
