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

import com.example.modelos.DetallesPedido;
import com.example.modelos.Pedidos;
import com.example.modelos.Productos;
import com.example.modelos.Usuarios;
import com.example.servicios.DetalesPedidoServicios;
import com.example.servicios.MetodosPagoServicios;
import com.example.servicios.PedidosServicios;
import com.example.servicios.ProductoServicios;


@Controller
@RequestMapping("/carrito")
public class CarritoMuestra {
	
	@Autowired
	ProductoServicios usu;
	
	@Autowired
	MetodosPagoServicios usa;
	
	@Autowired
	PedidosServicios usPedido;
	
	@Autowired
	DetalesPedidoServicios usDetPed;
	
	
	@GetMapping("/add/{n}")
	public String addCarrito(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Productos producto = usu.findById(id);
		boolean existe=false;
		if(producto!=null)
		{
			ArrayList <DetallesPedido> lista=(ArrayList <DetallesPedido>)sesion.getAttribute("carrito");
			if(lista!=null) {
				for (DetallesPedido productos : lista) {
					if(productos.getIdProducto()==id)
					{
						Integer mas= productos.getUnidades()+1;
						productos.setUnidades(mas);
						double total = productos.getPrecioUnidad()*mas;
						 total=Math.round(total * 100) / 100d;
						productos.setTotal(total);
						existe=true;
					}
				}
			}
			
			if(existe==false) {
				
				String prueba=producto.getPrecio()+"";
				Float precio=Float.parseFloat(prueba);
				
				DetallesPedido u = new DetallesPedido(1, producto.getId(),precio , 1, producto.getImpuesto(), producto.getPrecio(), producto.getNombre(), producto.getImagen());
				lista.add(u);
			}
		}
		
		return "redirect:/";
	}
	
	
	@GetMapping("/eliminar/{n}")
	public String eliminarCarrito(@PathVariable("n") int id, Model model, HttpSession sesion) {
		ArrayList <DetallesPedido> lista=(ArrayList <DetallesPedido>)sesion.getAttribute("carrito");
		double tota=0;
		if(lista!=null) {
			for (DetallesPedido productos : lista) {
				if(productos!=null) {
					if(productos.getIdProducto()==id)
					{
							Integer mas= productos.getUnidades()-1;
							if(mas<=0) {
								lista.remove(productos);//Como eliminar de session
								if(lista.size()>=1) {
									for (DetallesPedido producto : lista) {
										tota = tota+producto.getTotal();
									}
								}
								 tota=Math.round(tota * 100) / 100d;
								model.addAttribute("total", tota);
								return "carrito/compra";
								
							}else {
								productos.setUnidades(mas);
								double total = productos.getPrecioUnidad()*mas;
								 total=Math.round(total * 100) / 100d;
								productos.setTotal(total);
							}

					}
				}
				
			}
			
			
		}
		
		tota=0;
		if(lista.size()>=1) {
			for (DetallesPedido producto : lista) {
				tota = tota+producto.getTotal();
			}
		}
		 tota=Math.round(tota * 100) / 100d;
		model.addAttribute("total", tota);
		return "carrito/compra";
	}
	@GetMapping("/mostrar")
	public String mostrarCarrito(HttpSession sesion, Model model) {
		ArrayList <DetallesPedido> lista=(ArrayList <DetallesPedido>)sesion.getAttribute("carrito");
		double tota=0;
		//if(u!=null) {
		if(lista.size()>=1) {
			for (DetallesPedido producto : lista) {
				tota = tota+producto.getTotal();
			}
		}
		model.addAttribute("total", tota);
			return "carrito/compra";
		//}else return "login/loginForm"; 
	}
	
	
	@GetMapping("/compra")
	public String comprar(HttpSession sesion, Model model) {
		Usuarios usus = (Usuarios)sesion.getAttribute("usuario");
		ArrayList <DetallesPedido> lista=(ArrayList <DetallesPedido>)sesion.getAttribute("carrito");
		if(usus==null) {
			return "login/loginForm";
			
		}else {
			if(lista.size()<=0) {
				return "redirect:/";
			}
			
			double total=0;
			if(lista.size()>=1) {
				for (DetallesPedido producto : lista) {
					total = total+producto.getTotal();
				}
			}
			
			Pedidos pedido= new Pedidos(usus.getId(), null, "Pendiente", null, total);
			sesion.setAttribute("pedidos", pedido);
			model.addAttribute("metodoPago", usa.DarMetodoPago());
			return "carrito/comprando";
		}
		
	}
	
	@PostMapping("/comprado")
	public String pedidos(@ModelAttribute Pedidos pedido, Model model, HttpSession sesion, RedirectAttributes redirect) {
		Pedidos ped = (Pedidos)sesion.getAttribute("pedidos");
		ped.setMetodoPago(pedido.getMetodoPago());
		ArrayList <DetallesPedido> lista=(ArrayList <DetallesPedido>)sesion.getAttribute("carrito");
		Pedidos ped1=usPedido.save(ped);
		for (DetallesPedido p : lista) {
			p.setIdPedido(ped1.getId());
			usDetPed.save(p);
		}
		
		sesion.removeAttribute("pedidos");
		sesion.setAttribute("carrito", null);
		
		return "redirect:/";
	}
	
}
