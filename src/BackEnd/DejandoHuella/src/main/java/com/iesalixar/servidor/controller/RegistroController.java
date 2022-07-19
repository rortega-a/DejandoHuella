package com.iesalixar.servidor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesalixar.servidor.dto.RolAdministradorDTO;
import com.iesalixar.servidor.dto.RolCentroDTO;
import com.iesalixar.servidor.dto.RolUsuarioDTO;
import com.iesalixar.servidor.model.Administrador;
import com.iesalixar.servidor.model.CentroAdopcion;
import com.iesalixar.servidor.model.Usuario;
import com.iesalixar.servidor.services.AdministradorServiceImpl;
import com.iesalixar.servidor.services.CentroAdopcionServiceImpl;
import com.iesalixar.servidor.services.UsuarioServiceImpl;

@Controller
public class RegistroController {

	@Autowired
	UsuarioServiceImpl usuarioService;

	@Autowired
	CentroAdopcionServiceImpl centroAdopcionService;

	@Autowired
	AdministradorServiceImpl adminService;

	@RequestMapping("/registroUsuarios")
	public String registroUsuariosGet(@RequestParam(required = false, name = "errorUser") String errorUser,
			@RequestParam(required = false, name = "errorEmail") String errorEmail,
			@RequestParam(required = false, name = "errorDni") String errorDni, Model model) {

		RolUsuarioDTO rolUsuarioDTO = new RolUsuarioDTO();
		model.addAttribute("rolUsuario", rolUsuarioDTO);

		model.addAttribute("errorUser", errorUser);
		model.addAttribute("errorEmail", errorEmail);
		model.addAttribute("errorDni", errorDni);

		return "registroUsuarios";
	}

	@PostMapping("/registroUsuarios")
	public String registroUsuariosPost(@ModelAttribute RolUsuarioDTO rolUsuarioDTO, Model model) {

		Usuario usuarioBD = new Usuario();

		usuarioBD.setUserName(rolUsuarioDTO.getUsuario());
		usuarioBD.setPassword(new BCryptPasswordEncoder(15).encode(rolUsuarioDTO.getPassword()));
		usuarioBD.setDni(rolUsuarioDTO.getDni());
		usuarioBD.setNombre(rolUsuarioDTO.getNombre());
		usuarioBD.setApellido1(rolUsuarioDTO.getApellido1());
		usuarioBD.setApellido2(rolUsuarioDTO.getApellido2());
		usuarioBD.setFecha_nacimiento(rolUsuarioDTO.getFecha_nacimiento());
		usuarioBD.setTelefono(rolUsuarioDTO.getTelefono());
		usuarioBD.setEmail(rolUsuarioDTO.getEmail());
		usuarioBD.setCiudad(rolUsuarioDTO.getCiudad());
		usuarioBD.setDireccion(rolUsuarioDTO.getDireccion());
		usuarioBD.setRole("ROLE_USER");
		usuarioBD.setActivo(true);

		usuarioBD = usuarioService.insertUsuario(usuarioBD);

		if (usuarioBD.getUserName() == null) {
			return "redirect:/registroUsuarios?errorUser=UserName";
		} else if (usuarioBD.getEmail() == null) {
			return "redirect:/registroUsuarios?errorEmail=Email";
		} else if (usuarioBD.getDni() == null) {
			return "redirect:/registroUsuarios?errorDni=Dni";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/registroCentros")
	public String registroCentrosGet(@RequestParam(required = false, name = "errorUser") String errorUser,
			@RequestParam(required = false, name = "errorEmail") String errorEmail,
			@RequestParam(required = false, name = "errorNif") String errorNif, Model model) {

		RolCentroDTO rolCentroDTO = new RolCentroDTO();
		model.addAttribute("rolCentro", rolCentroDTO);

		model.addAttribute("errorUser", errorUser);
		model.addAttribute("errorEmail", errorEmail);
		model.addAttribute("errorNif", errorNif);

		return "registroCentros";
	}

	@PostMapping("/registroCentros")
	public String registroCentrosPost(@ModelAttribute RolCentroDTO rolCentroDTO, Model model) {

		CentroAdopcion centroAdopcionBD = new CentroAdopcion();

		centroAdopcionBD.setUserName(rolCentroDTO.getUsuario());
		centroAdopcionBD.setPassword(new BCryptPasswordEncoder(15).encode(rolCentroDTO.getPassword()));
		centroAdopcionBD.setNif(rolCentroDTO.getNif());
		centroAdopcionBD.setNombre(rolCentroDTO.getNombre());
		centroAdopcionBD.setTelefono(rolCentroDTO.getTelefono());
		centroAdopcionBD.setEmail(rolCentroDTO.getEmail());
		centroAdopcionBD.setCiudad(rolCentroDTO.getCiudad());
		centroAdopcionBD.setDireccion(rolCentroDTO.getDireccion());
		centroAdopcionBD.setRole("ROLE_CENTRO");
		centroAdopcionBD.setActivo(false);

		centroAdopcionBD = centroAdopcionService.insertCentroAdopcion(centroAdopcionBD);

		if (centroAdopcionBD.getUserName() == null) {
			return "redirect:/registroCentros?errorUser=UserName";
		} else if (centroAdopcionBD.getEmail() == null) {
			return "redirect:/registroCentros?errorEmail=Email";
		} else if (centroAdopcionBD.getNif() == null) {
			return "redirect:/registroCentros?errorNif=Nif";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/admin/registroAdmin")
	public String registroAdminGet(@RequestParam(required = false, name = "errorUser") String errorUser,
			@RequestParam(required = false, name = "errorEmail") String errorEmail, Model model) {

		RolAdministradorDTO rolAdminDTO = new RolAdministradorDTO();
		model.addAttribute("rolAdmin", rolAdminDTO);

		model.addAttribute("errorUser", errorUser);
		model.addAttribute("errorEmail", errorEmail);

		return "registroAdmin";
	}

	@PostMapping("/admin/registroAdmin")
	public String registroAdminPost(@ModelAttribute RolAdministradorDTO rolAdminDTO, Model model) {

		Administrador adminBD = new Administrador();

		adminBD.setUserName(rolAdminDTO.getUsuario());
		adminBD.setPassword(new BCryptPasswordEncoder(15).encode(rolAdminDTO.getPassword()));
		adminBD.setEmail(rolAdminDTO.getEmail());
		adminBD.setNombre(rolAdminDTO.getNombre());
		adminBD.setApellido1(rolAdminDTO.getApellido1());
		adminBD.setApellido2(rolAdminDTO.getApellido2());
		adminBD.setRole("ROLE_ADMIN");
		adminBD.setActivo(true);

		adminBD = adminService.insertAdministrador(adminBD);

		if (adminBD.getUserName() == null) {
			return "redirect:/admin/registroAdmin?errorUser=Usuario";
		} else if (adminBD.getEmail() == null) {
			return "redirect:/admin/registroAdmin?errorEmail=Usuario";
		} else {
			return "redirect:/";
		}

	}
}
