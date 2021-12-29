package com.example.controlador;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.modelos.Configuracion;
import com.example.modelos.DetallesPedido;
import com.example.modelos.Pedidos;
import com.example.modelos.Usuarios;
import com.example.servicios.ConfiguracionServicios;
import com.example.servicios.DetalesPedidoServicios;
import com.example.servicios.PedidosServicios;
import com.example.servicios.UsuarioServicios;

@Controller
@RequestMapping("/pedidos")
public class PedidosControlador {
	@Autowired
	PedidosServicios usu;
	
	@Autowired
	DetalesPedidoServicios usuDeta;
	
	@Autowired
	UsuarioServicios Usuario;
	
	@Autowired
	ConfiguracionServicios config;
	
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
	
	@GetMapping("/enviados")
	public String mostrarPedidosEnviados(Model model, HttpSession sesion) {
		ArrayList <Pedidos> p = (ArrayList <Pedidos>) usu.darPedidos();
		ArrayList <Pedidos> usado = new ArrayList<Pedidos>();
		if(p!=null) {
			for (Pedidos pedidos : p) {
				if(pedidos.getEstado().equalsIgnoreCase("Enviado")) {
					usado.add(pedidos);
				}
			}
		}
		model.addAttribute("Pedidos", usado);
		return "muestrasyeliminaciones/editarPedidosEnviado";
	}
	
	
	@GetMapping("/cancelados")
	public String mostrarPedidosCancelados(Model model, HttpSession sesion) {
		ArrayList <Pedidos> p = (ArrayList <Pedidos>) usu.darPedidos();
		ArrayList <Pedidos> usado = new ArrayList<Pedidos>();
		if(p!=null) {
			for (Pedidos pedidos : p) {
				if(pedidos.getEstado().equalsIgnoreCase("Cancelado")) {
					usado.add(pedidos);
				}
			}
		}
		model.addAttribute("Pedidos", usado);
		return "muestrasyeliminaciones/editarPedidosCancelados";
	}
	
	@GetMapping("/enviar/{n}")
	public String enviarPedido(@PathVariable("n") int id ,Model model, HttpSession sesion) {
		Pedidos us = usu.findById(id);
		Configuracion conf = config.findByClave("Factura");
		us.setNumFactura(conf.getValor());
		us.setEstado("Enviado");
		Integer numFactura = Integer.parseInt(conf.getValor());
		String valor = (numFactura+1)+"";
		conf.setValor(valor);
		config.save(conf);
		usu.save(us);
		//crearPDF(us, conf);
		return "redirect:/pedidos/muestrapedidos";
	}
	
	/*private void crearPDF(Pedidos us, Configuracion conf) {
		Usuarios usus = Usuario.findById(us.getIdUsuario());
		ArrayList<DetallesPedido> deta = (ArrayList<DetallesPedido>) usuDeta.DarProducto();
		ArrayList<DetallesPedido> nuevo= new ArrayList<DetallesPedido>();
		if(deta!=null) {
			for (DetallesPedido de : deta) {
				if(de.getIdPedido()==us.getId()) {
					nuevo.add(de);
				}
			}
		}
	}*/

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
		Pedidos ped = usu.findById(id);
		ArrayList<DetallesPedido> deta = (ArrayList<DetallesPedido>) usuDeta.DarProducto();
		ArrayList<DetallesPedido> nuevo= new ArrayList<DetallesPedido>();
		if(deta!=null) {
			for (DetallesPedido de : deta) {
				if(de.getIdPedido()==id) {
					nuevo.add(de);
				}
			}
		}
		
		model.addAttribute("pedidos", ped);
		model.addAttribute("Productos", nuevo);
		return "usuario/Productos";
	}
	
	@GetMapping("/eliminarLinea/{n}")
	public String eliminarLinea(@PathVariable("n") int id ,Model model, HttpSession sesion) {
		DetallesPedido detped= usuDeta.findById(id);
		Pedidos ped = usu.findById(detped.getIdPedido());
		String elimina="Cancelar linea";
		
		detped.setCancelarLinea(1);
		usuDeta.save(detped);
		ped.setEstado(elimina);
		usu.save(ped);
	
		return "redirect:/pedidos/muestrapedidosUsuario";
	}
	
	@GetMapping("/linea/{n}")
	public String eliminarLineaAdmin(@PathVariable("n") int id ,Model model, HttpSession sesion) {
		ArrayList<DetallesPedido> deta = (ArrayList<DetallesPedido>) usuDeta.DarProducto();
		ArrayList<DetallesPedido> nuevo= new ArrayList<DetallesPedido>();
		if(deta!=null) {
			for (DetallesPedido de : deta) {
				if(de.getIdPedido()==id) {
					nuevo.add(de);
				}
			}
		}
		
		model.addAttribute("Productos",nuevo);
		return "muestrasyeliminaciones/cancelarLineaPedidoAdmin";
	}
	
	@GetMapping("/eliminarLineaAdmin/{n}")
	public String eliminarLineasAdmin(@PathVariable("n") int id ,Model model, HttpSession sesion) {
		DetallesPedido detped= usuDeta.findById(id);
		Pedidos ped = usu.findById(detped.getIdPedido());
		String elimina="Pendiente";
		double nuevoprecio= ped.getTotal()-detped.getTotal();
		nuevoprecio=Math.round(nuevoprecio * 100) / 100d;
		ped.setTotal(nuevoprecio);
		usuDeta.eliminar(detped);
		ped.setEstado(elimina);
		usu.save(ped);
	
		return "redirect:/pedidos/muestrapedidos";
	}
}
