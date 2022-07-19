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
import com.iesalixar.servidor.dto.AdopcionDTO;
import com.iesalixar.servidor.dto.AnimalCentroDTO;
import com.iesalixar.servidor.model.Adoptan;
import com.iesalixar.servidor.model.Animal;
import com.iesalixar.servidor.model.AnimalCentro;
import com.iesalixar.servidor.model.CentroAdopcion;
import com.iesalixar.servidor.model.Rol;
import com.iesalixar.servidor.model.Usuario;
import com.iesalixar.servidor.repository.RolRepository;
import com.iesalixar.servidor.services.AdoptanServiceImpl;
import com.iesalixar.servidor.services.AnimalCentroServiceImpl;
import com.iesalixar.servidor.services.AnimalServiceImpl;
import com.iesalixar.servidor.services.CentroAdopcionServiceImpl;
import com.iesalixar.servidor.services.UsuarioServiceImpl;

@Controller
public class CentroAdopcionController {

	@Autowired
	RolRepository rolRepo;

	@Autowired
	UsuarioServiceImpl usuarioService;

	@Autowired
	CentroAdopcionServiceImpl centroService;

	@Autowired
	AnimalServiceImpl animalService;

	@Autowired
	AnimalCentroServiceImpl animalCentroService;

	@Autowired
	AdoptanServiceImpl adoptanService;

	@GetMapping("/centro/modificarCuentaCentro")
	public String modificarCuentaCentroGet(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();

			Rol rol = rolRepo.findByUserName(currentUserName);
			Long idCentro = rol.getId_rol();

			Optional<CentroAdopcion> centro = centroService.findCentroAdopcionById(idCentro);

			model.addAttribute("centro", centro.get());
		}

		return "modificarCuentaCentro";
	}

	@PostMapping("/centro/modificarCuentaCentro")
	public String modificarCuentaCentroPost(@ModelAttribute CentroAdopcion centroAdopcion) {

		centroAdopcion.setPassword(new BCryptPasswordEncoder(15).encode(centroAdopcion.getPassword()));

		if (centroService.actualizarCentroAdopcion(centroAdopcion) == null) {
			return "redirect:/centro/modificarCuentaCentro";
		}

		return "redirect:/";
	}

	@RequestMapping("/centro/insertarAnimalAdopcion")
	public String insertarAnimalCentroGet(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();

			Rol rol = rolRepo.findByUserName(currentUserName);
			Long idCentro = rol.getId_rol();

			AnimalCentroDTO animalCentroDTO = new AnimalCentroDTO();
			animalCentroDTO.setId_centro(idCentro);
			model.addAttribute("animalCentro", animalCentroDTO);

		}

		return "insertarAnimalCentro";
	}

	@PostMapping("/centro/insertarAnimalAdopcion")
	public String insertarAnimalCentroPost(@ModelAttribute AnimalCentroDTO animalCentro,
			@RequestParam("file") MultipartFile foto) {

		AnimalCentro animalCentroBD = new AnimalCentro();

		Optional<CentroAdopcion> centro = centroService.findCentroAdopcionById(animalCentro.getId_centro());

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
				animalCentroBD.setFoto(nombreFoto);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		animalCentroBD.setNombre(animalCentro.getNombre());
		animalCentroBD.setRaza(animalCentro.getRaza());
		animalCentroBD.setObservaciones(animalCentro.getObservaciones());
		animalCentroBD.setAdoptado(false);
		animalCentroBD.setCaracter(animalCentro.getCaracter());
		animalCentroBD.setCostes_adopcion(animalCentro.getCostes_adopcion());
		animalCentroBD.setFecha_nacimiento(animalCentro.getFecha_nacimiento());
		animalCentroBD.setHistoria(animalCentro.getHistoria());
		animalCentroBD.setPeso(animalCentro.getPeso());
		animalCentroBD.setTamano(animalCentro.getTamano());
		animalCentroBD.setCentroAdopcion(centro.get());

		animalCentroBD = animalCentroService.insertAnimalCentro(animalCentroBD);

		if (animalCentroBD == null) {
			return "redirect:/centro/insertarAnimalAdopcion";
		}

		return "redirect:/";

	}

	@RequestMapping("/centro/animalesAdopcionCentro")
	public String animalesAdopcionCentroGet(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();

			Rol rol = rolRepo.findByUserName(currentUserName);
			Long idCentro = rol.getId_rol();

			Optional<CentroAdopcion> centroBD = centroService.findCentroAdopcionById(idCentro);
			model.addAttribute("centro", centroBD.get());

		}

		return "animalesAdopcionCentro";
	}

	@GetMapping("/centro/modificarAnimalCentro")
	public String modificarAnimalCentroGet(@RequestParam(name = "animal") String id_animal, Model model) {

		Optional<AnimalCentro> animalCentroBD = animalCentroService.findAnimalCentroById(Long.parseLong(id_animal));

		model.addAttribute("animalCentroBD", animalCentroBD.get());

		return "modificarAnimalCentro";
	}

	@PostMapping("/centro/modificarAnimalCentro")
	public String modificarAnimalCentroPost(@ModelAttribute AnimalCentro animalCentro) {

		animalCentroService.actualizarAnimalCentro(animalCentro);

		return "redirect:/centro/animalesAdopcionCentro";
	}

	@RequestMapping("/centro/darAdopcion")
	public String darAdopcionGet(@RequestParam(name = "animal") String id_animal, Model model) {

		List<Usuario> usuariosBD = usuarioService.getAllUsuarios();
		Optional<AnimalCentro> animalCentroBD = animalCentroService.findAnimalCentroById(Long.parseLong(id_animal));
		AdopcionDTO adopcionDTO = new AdopcionDTO();

		adopcionDTO.setIdAnimal(Long.parseLong(id_animal));

		model.addAttribute("usuariosLista", usuariosBD);
		model.addAttribute("animalCentroBD", animalCentroBD.get());
		model.addAttribute("adopcionDTO", adopcionDTO);

		return "darAdopcion";
	}

	@PostMapping("/centro/darAdopcion")
	public String darAdopcionPost(@ModelAttribute AdopcionDTO adopcionDTO) {

		Adoptan adopcionBD = new Adoptan();
		Optional<AnimalCentro> animalCentroBD = animalCentroService.findAnimalCentroById(adopcionDTO.getIdAnimal());
		Optional<Usuario> usuarioBD = usuarioService.findUsuarioById(adopcionDTO.getIdUsuario());

		adopcionBD.setAnimalCentro(animalCentroBD.get());
		adopcionBD.setUsuario(usuarioBD.get());
		adopcionBD.setFecha_adopcion(adopcionDTO.getFecha_adopcion());

		adopcionBD = adoptanService.insertAdoptado(adopcionBD);

		if (adopcionBD == null) {
			return "redirect:/centro/darAdopcion";
		}

		animalCentroBD.get().setAdoptado(true);
		animalCentroService.actualizarAnimalCentro(animalCentroBD.get());

		return "redirect:/centro/animalesAdopcionCentro";
	}

	@GetMapping("/centro/borrarAnimalCentro")
	public String borrarAnimalPerdidoGet(@RequestParam(name = "animal") String idAnimal) {

		Optional<Animal> animalBD = animalService.findAnimalById(Long.parseLong(idAnimal));

		animalService.borrarAnimal(animalBD.get());

		return "redirect:/centro/animalesAdopcionCentro";
	}
}
