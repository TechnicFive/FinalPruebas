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

import com.example.modelos.Categorias;
import com.example.servicios.CategoriaServicios;

@Controller
@RequestMapping("/categoria")
public class CategoriaControlador {
	@Autowired
	private CategoriaServicios usu;
	
	@GetMapping("/mostrar")
	public String mostrar(Model model, HttpSession sesion) {
		
		model.addAttribute("categorias", usu.darCategorias());
		return "categorias/mostrarTodas";
	}
	
	@GetMapping("/crear")
	public String crear(Model model, HttpSession sesion, RedirectAttributes redirect) {
		return "categorias/crearCategoria";
	}
	
	@PostMapping("/crear")
	public String creando(@ModelAttribute Categorias cate, Model model, HttpSession sesion, RedirectAttributes redirect) {
		usu.save(cate);
		
		sesion.setAttribute("Categorias", usu.darCategorias());
		return "redirect:/categoria/mostrar";
	}
}
