package com.iesalixar.servidor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "animal_centro")
@PrimaryKeyJoinColumn(name = "id_animal_centro")
public class AnimalCentro extends Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String historia;

	@Column(nullable = false)
	private String caracter;

	@Column(nullable = false)
	private String fecha_nacimiento;

	@Column(nullable = false)
	private String tamano;

	@Column(nullable = false)
	private Integer peso;

	@Column(nullable = false)
	private Integer costes_adopcion;

	@OneToMany(mappedBy = "animalCentro", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Adoptan> adoptados = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "id_centro_adopcion")
	private CentroAdopcion centroAdopcion;

	public AnimalCentro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Integer getCostes_adopcion() {
		return costes_adopcion;
	}

	public void setCostes_adopcion(Integer costes_adopcion) {
		this.costes_adopcion = costes_adopcion;
	}

	public Set<Adoptan> getAdoptados() {
		return adoptados;
	}

	public void setAdoptados(Set<Adoptan> adoptados) {
		this.adoptados = adoptados;
	}

	public CentroAdopcion getCentroAdopcion() {
		return centroAdopcion;
	}

	public void setCentroAdopcion(CentroAdopcion centroAdopcion) {
		this.centroAdopcion = centroAdopcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(caracter, costes_adopcion, fecha_nacimiento, historia, peso, tamano);
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
		AnimalCentro other = (AnimalCentro) obj;
		return Objects.equals(caracter, other.caracter) && Objects.equals(costes_adopcion, other.costes_adopcion)
				&& Objects.equals(fecha_nacimiento, other.fecha_nacimiento) && Objects.equals(historia, other.historia)
				&& Objects.equals(peso, other.peso) && Objects.equals(tamano, other.tamano);
	}

}
