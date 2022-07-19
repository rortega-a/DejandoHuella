package com.iesalixar.servidor.dto;

public class AnimalCentroDTO {

	private String nombre;
	private String raza;
	private String observaciones;
	private String caracter;
	private Integer costes_adopcion;
	private String fecha_nacimiento;
	private String historia;
	private Integer peso;
	private String tamano;
	private Long id_centro;

	public AnimalCentroDTO() {
		// TODO Auto-generated constructor stub
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

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public Integer getCostes_adopcion() {
		return costes_adopcion;
	}

	public void setCostes_adopcion(Integer costes_adopcion) {
		this.costes_adopcion = costes_adopcion;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	public Long getId_centro() {
		return id_centro;
	}

	public void setId_centro(Long id_centro) {
		this.id_centro = id_centro;
	}

}
