package com.example.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.servicios.UsuarioServicios;

@Controller
@RequestMapping("/usuarios")
public class RegistroControlador {
	@Autowired
	UsuarioServicios usu;
	
	@GetMapping("/base")
	public void base() {
		usu.cargarUsuarios();
	}

	@GetMapping("/registro")
	public String registro() {
		
		return "loginForm";
	}
	
	
}
