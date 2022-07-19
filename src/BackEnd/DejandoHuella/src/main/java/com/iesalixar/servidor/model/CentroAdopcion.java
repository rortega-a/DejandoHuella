package com.iesalixar.servidor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "centro_adopcion")
@PrimaryKeyJoinColumn(name = "id_centro_adopcion")
public class CentroAdopcion extends Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(unique = true, length = 9, nullable = false)
	private String nif;

	@Column(nullable = false)
	private String telefono;

	@Column(nullable = false)
	private String ciudad;

	@Column(nullable = false)
	private String direccion;

	@OneToMany(mappedBy = "centroAdopcion", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AnimalCentro> animalesCentro = new HashSet<>();

	public CentroAdopcion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Set<AnimalCentro> getAnimalesCentro() {
		return animalesCentro;
	}

	public void setAnimalesCentro(Set<AnimalCentro> animalesCentro) {
		this.animalesCentro = animalesCentro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(ciudad, direccion, nif, telefono);
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
		CentroAdopcion other = (CentroAdopcion) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(nif, other.nif) && Objects.equals(telefono, other.telefono);
	}

}
