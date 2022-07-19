package com.iesalixar.servidor.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(required = false, name = "error") String error, Model model,
			Principal principal) {

		if (error != null) {
			model.addAttribute("error", error);
		}

		if (principal != null) {
			return "redirect:/";
		}

		return "login";
	}

	@RequestMapping("/logoutPage")
	public String logout() {

		return "logout";
	}
}
