package com.iesalixar.servidor.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "animal_perdido")
@PrimaryKeyJoinColumn(name = "id_animal_perdido")
public class AnimalPerdido extends Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String ciudad;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public AnimalPerdido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(ciudad);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimalPerdido other = (AnimalPerdido) obj;
		return Objects.equals(ciudad, other.ciudad);
	}

}
