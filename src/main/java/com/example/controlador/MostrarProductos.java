package com.example.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.servicios.ProductoServicios;


@Controller
@RequestMapping("")
public class MostrarProductos {
	@Autowired
	ProductoServicios usu;
	
	@GetMapping("")
	public String Productos(Model model) {
		model.addAttribute("Productos", usu.DarProducto());
		return "index";
	}
}
