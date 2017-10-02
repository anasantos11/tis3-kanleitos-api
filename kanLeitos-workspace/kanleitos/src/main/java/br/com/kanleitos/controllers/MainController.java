package br.com.kanleitos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping(value = "/teste", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public @ResponseBody String homepage() {
		return "index";
	}

}
