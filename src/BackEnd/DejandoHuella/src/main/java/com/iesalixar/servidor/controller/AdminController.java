package com.iesalixar.servidor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesalixar.servidor.model.Animal;
import com.iesalixar.servidor.model.AnimalPerdido;
import com.iesalixar.servidor.model.CentroAdopcion;
import com.iesalixar.servidor.model.Rol;
import com.iesalixar.servidor.model.Usuario;
import com.iesalixar.servidor.services.AnimalPerdidoServiceImpl;
import com.iesalixar.servidor.services.AnimalServiceImpl;
import com.iesalixar.servidor.services.CentroAdopcionServiceImpl;
import com.iesalixar.servidor.services.RolServiceImpl;
import com.iesalixar.servidor.services.UsuarioServiceImpl;

@Controller
public class AdminController {

	@Autowired
	RolServiceImpl rolService;

	@Autowired
	CentroAdopcionServiceImpl centroService;

	@Autowired
	UsuarioServiceImpl usuarioService;

	@Autowired
	AnimalServiceImpl animalService;

	@Autowired
	AnimalPerdidoServiceImpl animalPerdidoService;

	@RequestMapping("/admin/activarCentros")
	public String activarCentrosGet(Model model) {

		List<CentroAdopcion> centrosAdopcionLista = centroService.getAllCentrosAdopcion();

		model.addAttribute("centrosAdopcionLista", centrosAdopcionLista);

		return "activarCentros";
	}

	@GetMapping("/admin/confirmarCentro")
	public String confirmarCentroGet(@RequestParam(name = "centro") String idCentro) {

		Optional<CentroAdopcion> centroBD = centroService.findCentroAdopcionById(Long.parseLong(idCentro));

		if (centroBD.get().isActivo() == false) {
			centroBD.get().setActivo(true);

			centroService.actualizarCentroAdopcion(centroBD.get());
		}

		return "redirect:/admin/activarCentros";
	}

	@GetMapping("/admin/gestionarCentrosUsuarios")
	public String gestionarCentrosUsuariosGet(Model model) {

		List<Usuario> usuariosLista = usuarioService.getAllUsuarios();
		List<CentroAdopcion> centrosLista = centroService.getAllCentrosAdopcion();

		model.addAttribute("usuariosLista", usuariosLista);
		model.addAttribute("centrosLista", centrosLista);

		return "gestionarCentrosUsuarios";
	}

	@GetMapping("/admin/desactivarCentro")
	public String desactivarCentroGet(@RequestParam(name = "centro") String idCentro) {

		Optional<CentroAdopcion> centroBD = centroService.findCentroAdopcionById(Long.parseLong(idCentro));

		if (centroBD.get().isActivo() == true) {
			centroBD.get().setActivo(false);

			centroService.actualizarCentroAdopcion(centroBD.get());
		}

		return "redirect:/admin/gestionarCentrosUsuarios";
	}

	@GetMapping("/admin/activarUsuario")
	public String activarUsuarioGet(@RequestParam(name = "usuario") String idUsuario) {

		Optional<Usuario> usuarioBD = usuarioService.findUsuarioById(Long.parseLong(idUsuario));

		if (usuarioBD.get().isActivo() == false) {
			usuarioBD.get().setActivo(true);

			usuarioService.actualizarUsuario(usuarioBD.get());
		}

		return "redirect:/admin/gestionarCentrosUsuarios";
	}

	@GetMapping("/admin/desactivarUsuario")
	public String desactivarUsuarioGet(@RequestParam(name = "usuario") String idUsuario) {

		Optional<Usuario> usuarioBD = usuarioService.findUsuarioById(Long.parseLong(idUsuario));

		if (usuarioBD.get().isActivo() == true) {
			usuarioBD.get().setActivo(false);

			usuarioService.actualizarUsuario(usuarioBD.get());
		}

		return "redirect:/admin/gestionarCentrosUsuarios";
	}

	@GetMapping("/admin/borrarCentro")
	public String borrarCentroGet(@RequestParam(name = "centro") String idCentro) {

		Optional<Rol> centroBD = rolService.findRolById(Long.parseLong(idCentro));

		rolService.borrarRol(centroBD.get());

		return "redirect:/admin/gestionarCentrosUsuarios";
	}

	@GetMapping("/admin/borrarUsuario")
	public String borrarUsuarioGet(@RequestParam(name = "usuario") String idUsuario) {

		Optional<Rol> usuarioBD = rolService.findRolById(Long.parseLong(idUsuario));

		rolService.borrarRol(usuarioBD.get());

		return "redirect:/admin/gestionarCentrosUsuarios";
	}

	@GetMapping("/admin/gestionarPerdidos")
	public String gestionarPerdidosGet(Model model) {

		List<AnimalPerdido> animalPerdidoLista = animalPerdidoService.getAllAnimalesPerdidos();

		model.addAttribute("animalPerdidoLista", animalPerdidoLista);

		return "gestionarPerdidos";
	}

	@GetMapping("/admin/activarPerdido")
	public String activarPerdidoGet(@RequestParam(name = "animal") String idAPerdido, Model model) {

		Optional<AnimalPerdido> aPerdidoBD = animalPerdidoService.findAnimalPerdidoById(Long.parseLong(idAPerdido));

		if (aPerdidoBD.get().isAdoptado() == false) {
			aPerdidoBD.get().setAdoptado(true);

			animalPerdidoService.actualizarAnimalPerdido(aPerdidoBD.get());
		}

		return "redirect:/admin/gestionarPerdidos";
	}

	@GetMapping("/admin/desactivarPerdido")
	public String desactivarPerdidoGet(@RequestParam(name = "animal") String idAPerdido, Model model) {

		Optional<AnimalPerdido> aPerdidoBD = animalPerdidoService.findAnimalPerdidoById(Long.parseLong(idAPerdido));

		if (aPerdidoBD.get().isAdoptado() == true) {
			aPerdidoBD.get().setAdoptado(false);

			animalPerdidoService.actualizarAnimalPerdido(aPerdidoBD.get());
		}

		return "redirect:/admin/gestionarPerdidos";
	}

	@GetMapping("/admin/borrarPerdido")
	public String borrarPerdidoGet(@RequestParam(name = "animal") String idAnimal) {

		Optional<Animal> animalBD = animalService.findAnimalById(Long.parseLong(idAnimal));

		animalService.borrarAnimal(animalBD.get());

		return "redirect:/admin/gestionarPerdidos";
	}
}
