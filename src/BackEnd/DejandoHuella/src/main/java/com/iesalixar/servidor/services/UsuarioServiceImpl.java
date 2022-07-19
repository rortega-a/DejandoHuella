package com.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.servidor.model.Usuario;
import com.iesalixar.servidor.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepo;

	@Override
	public Usuario insertUsuario(Usuario usuario) {

		if (usuario != null && findUsuarioByUserName(usuario.getUserName()) == null
				&& findUsuarioByEmail(usuario.getEmail()) == null && findUsuarioByDni(usuario.getDni()) == null) {
			return usuarioRepo.save(usuario);
		}

		if (findUsuarioByUserName(usuario.getUserName()) != null) {
			usuario.setUserName(null);
			return usuario;
		} else if (findUsuarioByEmail(usuario.getEmail()) != null) {
			usuario.setEmail(null);
			return usuario;
		} else if (findUsuarioByDni(usuario.getDni()) != null) {
			usuario.setDni(null);
			return usuario;
		} else {
			return null;
		}

	}

	@Override
	public Optional<Usuario> findUsuarioById(Long id) {

		if (id != null) {
			return usuarioRepo.findById(id);
		} else {
			return null;
		}
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {

		if (usuario == null || usuario.getId_rol() == null || usuario.getNombre() == null) {
			return null;
		}

		return usuarioRepo.save(usuario);
	}

	@Override
	public List<Usuario> getAllUsuarios() {

		// Obtengo el resultado a través del repositorio
		List<Usuario> usuariosList = usuarioRepo.findAll();

		// Verificando que he obtenido algo
		if (usuariosList != null && usuariosList.size() > 0) {

			return usuariosList;
		}

		// No he obtenido nada devuelvo una lista vacía (para no devolver nulo)
		return new ArrayList<Usuario>();
	}

	@Override
	public Usuario findUsuarioByUserName(String userName) {

		if (userName != null) {
			return usuarioRepo.findByUserName(userName);
		}

		return null;
	}

	@Override
	public Usuario findUsuarioByEmail(String email) {

		if (email != null) {
			return usuarioRepo.findByEmail(email);
		}

		return null;
	}

	@Override
	public Usuario findUsuarioByDni(String dni) {

		if (dni != null) {
			return usuarioRepo.findByDni(dni);
		}

		return null;
	}

}
