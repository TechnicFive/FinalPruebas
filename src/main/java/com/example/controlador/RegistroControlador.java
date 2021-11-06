package com.example.controlador;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.modelos.Usuarios;
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
	
	@PostMapping("/login")
	public String login(@ModelAttribute Usuarios usuario, Model model, HttpSession sesion, RedirectAttributes redirect) {
		System.out.println("Email: " + usuario.getEmail() + " Clave: " + usuario.getClave());
		Usuarios u=usu.findByEmail(usuario.getEmail());
		System.out.println("probando");
		//String pagina="redirect:/usuarios/registro";
		if(u!=null) {
			if(u.getClave().equals(usuario.getClave())) {
				System.out.println("probando");
				sesion.setAttribute("usuario", u);
				return "holaUsuario";
			}else return "redirect:/usuarios/registro";
			
		}else return "redirect:/usuarios/registro";
		
		
	}
	
	
	
}
