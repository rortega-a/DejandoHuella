package com.iesalixar.servidor.services;

import com.iesalixar.servidor.model.Administrador;

public interface AdministradorService {

	public Administrador insertAdministrador(Administrador administrador);

	public Administrador findAdministradorByUserName(String userName);

	public Administrador findAdministradorByEmail(String email);

}
