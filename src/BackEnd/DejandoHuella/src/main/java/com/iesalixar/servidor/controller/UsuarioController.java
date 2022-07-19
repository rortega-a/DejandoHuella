package com.iesalixar.servidor.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iesalixar.servidor.FileNameUtils;
import com.iesalixar.servidor.dto.AnimalPerdidoDTO;
import com.iesalixar.servidor.model.Animal;
import com.iesalixar.servidor.model.AnimalCentro;
import com.iesalixar.servidor.model.AnimalPerdido;
import com.iesalixar.servidor.model.Rol;
import com.iesalixar.servidor.model.Usuario;
import com.iesalixar.servidor.repository.RolRepository;
import com.iesalixar.servidor.services.AnimalCentroServiceImpl;
import com.iesalixar.servidor.services.AnimalPerdidoServiceImpl;
import com.iesalixar.servidor.services.AnimalServiceImpl;
import com.iesalixar.servidor.services.UsuarioServiceImpl;

@Controller
public class UsuarioController {

	@Autowired
	RolRepository rolRepo;

	@Autowired
	UsuarioServiceImpl usuarioService;

	@Autowired
	AnimalServiceImpl animalService;

	@Autowired
	AnimalPerdidoServiceImpl animalPerdidoService;

	@Autowired
	AnimalCentroServiceImpl animalCentroService;

	@RequestMapping("/usuario/modificarCuentaUsuario")
	public String modificarCuentaGet(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();

			Rol rol = rolRepo.findByUserName(currentUserName);
			Long idUser = rol.getId_rol();

			Optional<Usuario> usuario = usuarioService.findUsuarioById(idUser);

			model.addAttribute("usuario", usuario.get());
		}

		return "modificarCuentaUsuario";
	}

	@PostMapping("/usuario/modificarCuentaUsuario")
	public String modificarCuentaPost(@ModelAttribute Usuario usuario) {

		usuario.setPassword(new BCryptPasswordEncoder(15).encode(usuario.getPassword()));

		if (usuarioService.actualizarUsuario(usuario) == null) {
			return "redirect:/usuario/modificarCuentaUsuario";
		}

		return "redirect:/";
	}

	@RequestMapping("/usuario/insertarAnimalPerdido")
	public String insertarAnimalPerdidoGet(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();

			Rol rol = rolRepo.findByUserName(currentUserName);
			Long idUser = rol.getId_rol();

			AnimalPerdidoDTO animalPerdidoDTO = new AnimalPerdidoDTO();
			animalPerdidoDTO.setId_usuario(idUser);
			model.addAttribute("animalPerdido", animalPerdidoDTO);

		}

		return "insertarAnimalPerdido";
	}

	@PostMapping("/usuario/insertarAnimalPerdido")
	public String insertarAnimalPerdidoPost(@ModelAttribute AnimalPerdidoDTO animalPerdido,
			@RequestParam("file") MultipartFile foto) {

		AnimalPerdido animalPerdidoBD = new AnimalPerdido();

		Optional<Usuario> usuario = usuarioService.findUsuarioById(animalPerdido.getId_usuario());

		if (!foto.isEmpty()) {
			// Path directorioFotos = Paths.get("src//main//resources//static/fotos");
			String rutaAbsoluta = "C://Animales//fotos";
			String nombreFoto = FileNameUtils.getFileName(foto.getOriginalFilename());

			try {
				byte[] bytesFoto = foto.getBytes();
				// Path rutaCompleta = Paths.get(rutaAbsoluta + "//" +
				// foto.getOriginalFilename());
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreFoto);
				Files.write(rutaCompleta, bytesFoto);

				// animalPerdidoBD.setFoto(foto.getOriginalFilename());
				animalPerdidoBD.setFoto(nombreFoto);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		animalPerdidoBD.setNombre(animalPerdido.getNombre());
		animalPerdidoBD.setRaza(animalPerdido.getRaza());
		animalPerdidoBD.setObservaciones(animalPerdido.getObservaciones());
		animalPerdidoBD.setCiudad(animalPerdido.getCiudad());
		animalPerdidoBD.setUsuario(usuario.get());
		animalPerdidoBD.setAdoptado(false);

		animalPerdidoBD = animalPerdidoService.insertAnimalPerdido(animalPerdidoBD);

		if (animalPerdidoBD == null) {
			return "redirect:/usuario/insertarAnimalPerdido";
		}

		return "redirect:/";
	}

	@RequestMapping("/usuario/animalesPerdidosUsuario")
	public String animalesPerdidosUsuariooGet(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();

			Rol rol = rolRepo.findByUserName(currentUserName);
			Long idUser = rol.getId_rol();

			Optional<Usuario> usuarioBD = usuarioService.findUsuarioById(idUser);
			model.addAttribute("usuario", usuarioBD.get());
		}

		return "animalesPerdidosUsuario";
	}

	@RequestMapping("/usuario/animalesPerdidos")
	public String animalesPerdidosGet(Model model) {

		List<AnimalPerdido> animalesPerdidosLista = animalPerdidoService.getAllAnimalesPerdidos();

		model.addAttribute("animalesPerdidosLista", animalesPerdidosLista);

		return "animalesPerdidos";
	}

	@RequestMapping("/usuario/animalesAdopcion")
	public String animalesAdopcionGet(Model model) {

		List<AnimalCentro> animalesAdopcionLista = animalCentroService.getAllAnimalesCentro();

		model.addAttribute("animalesAdopcionLista", animalesAdopcionLista);

		return "animalesAdopcion";
	}

	@GetMapping("/usuario/modificarAnimalPerdido")
	public String modificarAnimalPerdidoGet(@RequestParam(name = "animal") String id_animal, Model model) {

		Optional<AnimalPerdido> animalPerdidoBD = animalPerdidoService.findAnimalPerdidoById(Long.parseLong(id_animal));

		model.addAttribute("animalPerdidoBD", animalPerdidoBD.get());

		return "modificarAnimalPerdido";
	}

	@PostMapping("/usuario/modificarAnimalPerdido")
	public String modificarAnimalPerdidoPost(@ModelAttribute AnimalPerdido animalPerdido) {

		animalPerdidoService.actualizarAnimalPerdido(animalPerdido);

		return "redirect:/usuario/animalesPerdidosUsuario";
	}

	@GetMapping("/usuario/borrarAnimalPerdido")
	public String borrarAnimalPerdidoGet(@RequestParam(name = "animal") String idAnimal) {

		Optional<Animal> animalBD = animalService.findAnimalById(Long.parseLong(idAnimal));

		animalService.borrarAnimal(animalBD.get());

		return "redirect:/usuario/animalesPerdidosUsuario";
	}
}
