package com.iesalixar.servidor.services;

import java.util.List;
import java.util.Optional;

import com.iesalixar.servidor.model.Usuario;

public interface UsuarioService {

	public Usuario insertUsuario(Usuario usuario);

	public Optional<Usuario> findUsuarioById(Long id);

	public Usuario actualizarUsuario(Usuario usuario);

	public List<Usuario> getAllUsuarios();

	public Usuario findUsuarioByUserName(String userName);

	public Usuario findUsuarioByEmail(String email);

	public Usuario findUsuarioByDni(String dni);

}
