package com.iesalixar.servidor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String home() {

		return "index";
	}

	@GetMapping("/linksAmigos")
	public String linksAmigosGet() {

		return "linksAmigos";
	}

	@GetMapping("/aboutUs")
	public String aboutUsGet() {

		return "aboutUs";
	}
}
