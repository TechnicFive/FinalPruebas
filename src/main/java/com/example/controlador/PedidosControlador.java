package com.example.controlador;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.modelos.DetallesPedido;
import com.example.modelos.Pedidos;
import com.example.modelos.Usuarios;
import com.example.servicios.DetalesPedidoServicios;
import com.example.servicios.PedidosServicios;

@Controller
@RequestMapping("/pedidos")
public class PedidosControlador {
	@Autowired
	PedidosServicios usu;
	
	@Autowired
	DetalesPedidoServicios usuDeta;
	
	@GetMapping("/muestrapedidos")
	public String mostrarPedidos(Model model, HttpSession sesion) {
		ArrayList <Pedidos> p = (ArrayList <Pedidos>) usu.darPedidos();
		ArrayList <Pedidos> usado = new ArrayList<Pedidos>();
		if(p!=null) {
			for (Pedidos pedidos : p) {
				if(pedidos.getEstado().equalsIgnoreCase("Enviado") || pedidos.getEstado().equalsIgnoreCase("Cancelado")) {
					
				}else {
					usado.add(pedidos);
				}
			}
		}
		model.addAttribute("Pedidos", usado);
		return "muestrasyeliminaciones/editarPedidosAdmin";
	}
	
	@GetMapping("/enviar/{n}")
	public String enviarPedido(@PathVariable("n") int id ,Model model, HttpSession sesion) {
		Pedidos us = usu.findById(id);
		us.setEstado("Enviado");
		usu.save(us);
		return "redirect:/pedidos/muestrapedidos";
	}
	
	@GetMapping("/cancelar/{n}")
	public String cancelarPedido(@PathVariable("n") int id ,Model model, HttpSession sesion) {
		Pedidos us = usu.findById(id);
		us.setEstado("Cancelado");
		usu.save(us);
		return "redirect:/pedidos/muestrapedidos";
	}
	
	@GetMapping("/muestrapedidosUsuario")
	public String mostrarPedidosUsuario(Model model, HttpSession sesion) {
		ArrayList <Pedidos> p = (ArrayList <Pedidos>) usu.darPedidos();
		Usuarios usuario = (Usuarios) sesion.getAttribute("usuario");
		ArrayList <Pedidos> usado = new ArrayList<Pedidos>();
		if(p!=null) {
			for (Pedidos pedidos : p) {
				if(pedidos.getIdUsuario()== usuario.getId()) {
					usado.add(pedidos);
				}
			}
		}
		model.addAttribute("Pedidos", usado);
		return "pedidos/mostrarPedidos";
	}
	
	@GetMapping("/CancelarPedido/{n}")
	public String pedirCancelacion(@PathVariable("n") int id ,Model model, HttpSession sesion) {
		Pedidos us = usu.findById(id);
		us.setEstado("Solicitud Cancelacion");
		usu.save(us);
		return "redirect:/pedidos/muestrapedidosUsuario";
	}
	
	@GetMapping("/mostrarProductos/{n}")
	public String mostrarProductos(@PathVariable("n") int id ,Model model, HttpSession sesion) {
		ArrayList<DetallesPedido> deta = (ArrayList<DetallesPedido>) usuDeta.DarProducto();
		ArrayList<DetallesPedido> nuevo= new ArrayList<DetallesPedido>();
		if(deta!=null) {
			for (DetallesPedido de : deta) {
				if(de.getIdPedido()==id) {
					nuevo.add(de);
				}
			}
		}
		model.addAttribute("Productos", nuevo);
		return "usuario/Productos";
	}
}
