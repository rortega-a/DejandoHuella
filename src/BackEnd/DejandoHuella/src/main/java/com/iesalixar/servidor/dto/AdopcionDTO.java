package com.iesalixar.servidor.dto;

public class AdopcionDTO {

	private Long idAnimal;
	private Long idUsuario;
	private String fecha_adopcion;

	public AdopcionDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFecha_adopcion() {
		return fecha_adopcion;
	}

	public void setFecha_adopcion(String fecha_adopcion) {
		this.fecha_adopcion = fecha_adopcion;
	}

}
