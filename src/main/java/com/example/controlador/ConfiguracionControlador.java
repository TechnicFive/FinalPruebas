package com.example.controlador;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.modelos.Configuracion;
import com.example.servicios.ConfiguracionServicios;

@Controller
@RequestMapping("/configuracion")
public class ConfiguracionControlador {
	
	@Autowired
	private ConfiguracionServicios usu;
	
	@GetMapping("/mostrar")
	public String mostrar(Model model, HttpSession sesion) {
		ArrayList<Configuracion> confg = (ArrayList<Configuracion>) usu.darConfiguracion();
		
		model.addAttribute("configuracion", confg);
		return "configuracion/mostrarTodos";
	}
	
	@GetMapping("/crear")
	public String crear(Model model, HttpSession sesion, RedirectAttributes redirect) {
		return "configuracion/crearConfiguracion";
	}
	
	@PostMapping("/crear")
	public String crearlo(@ModelAttribute Configuracion config, Model model, HttpSession sesion, RedirectAttributes redirect) {
		Configuracion p= usu.findByClave(config.getClave());
		if(p==null) {
			usu.save(config);
		}
		return "redirect:/configuracion/mostrar";
	}
	
	@GetMapping("/editar/{n}")
	public String editar(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Configuracion este = (Configuracion) usu.findById(id);
		model.addAttribute("configuracion", este);
		return "/configuracion/editar";
	}
	
	@PostMapping("/edicion")
	public String edicion(@ModelAttribute Configuracion config, Model model, HttpSession sesion, RedirectAttributes redirect) {
		Configuracion uno= usu.findById(config.getId());
		config.setClave(uno.getClave());
		config.setTipo(uno.getTipo());
		usu.save(config);
		return "redirect:/configuracion/mostrar";
	}
	
	@GetMapping("/eliminar/{n}")
	public String eliminar(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Configuracion este = (Configuracion) usu.findById(id);
		usu.eliminar(este);
		return "redirect:/configuracion/mostrar";
	}
}
