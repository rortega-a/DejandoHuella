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
@Table(name = "usuario")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Usuario extends Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(unique = true, length = 9, nullable = false)
	private String dni;

	@Column(nullable = false)
	private String apellido1;

	@Column(nullable = false)
	private String apellido2;

	@Column(nullable = false)
	private String fecha_nacimiento;

	@Column(nullable = false)
	private String telefono;

	@Column(nullable = false)
	private String ciudad;

	@Column(nullable = false)
	private String direccion;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Adoptan> adoptados = new HashSet<>();

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AnimalPerdido> animalesPerdidos = new HashSet<>();

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
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

	public Set<Adoptan> getAdoptados() {
		return adoptados;
	}

	public void setAdoptados(Set<Adoptan> adoptados) {
		this.adoptados = adoptados;
	}

	public Set<AnimalPerdido> getAnimalesPerdidos() {
		return animalesPerdidos;
	}

	public void setAnimalesPerdidos(Set<AnimalPerdido> animalesPerdidos) {
		this.animalesPerdidos = animalesPerdidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(apellido1, apellido2, ciudad, direccion, dni, fecha_nacimiento, telefono);
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
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido1, other.apellido1) && Objects.equals(apellido2, other.apellido2)
				&& Objects.equals(ciudad, other.ciudad) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(dni, other.dni) && Objects.equals(fecha_nacimiento, other.fecha_nacimiento)
				&& Objects.equals(telefono, other.telefono);
	}

}
