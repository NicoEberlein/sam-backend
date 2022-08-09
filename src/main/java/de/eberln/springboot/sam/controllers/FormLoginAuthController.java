package de.eberln.springboot.sam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormLoginAuthController {

	@GetMapping("/subscribers/login")
	public String login() {
		return "login";
	}
	
}
