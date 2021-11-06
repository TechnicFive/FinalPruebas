package com.example.controlador;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.modelos.Usuarios;
import com.example.modelos.Productos;
import com.example.servicios.ProductoServicios;


@Controller
@RequestMapping("")
public class MostrarProductos {
	@Autowired
	ProductoServicios usu;
	
	@GetMapping("")
	public String Productos(Model model, HttpSession sesion) {
		crearCarrito(sesion);
		model.addAttribute("Productos", usu.DarProducto());
		return "index";
	}

	
	public void crearCarrito(HttpSession sesion) {
		ArrayList <Productos> lista= new ArrayList <Productos>();
		
		sesion.setAttribute("carrito", lista);
		//Cuando compremos se pone null
			
	}
}
