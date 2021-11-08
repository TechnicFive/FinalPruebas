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
import com.example.modelos.Productos;
import com.example.modelos.Usuarios;
import com.example.servicios.ProductoServicios;


@Controller
@RequestMapping("/carrito")
public class CarritoMuestra {
	
	@Autowired
	ProductoServicios usu;
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
						productos.setTotal(total);
						existe=true;
					}
				}
			}
			
			if(existe==false) {
				
				String prueba=producto.getPrecio()+"";
				Float precio=Float.parseFloat(prueba);
				
				DetallesPedido u = new DetallesPedido(1, producto.getId(),precio , 1, producto.getImpuesto(), producto.getPrecio());
				lista.add(u);
			}
		}
		
		return "redirect:/";
	}
	
	
	@GetMapping("/eliminar/{n}")
	public String eliminarCarrito(@PathVariable("n") int id, Model model, HttpSession sesion) {
		ArrayList <DetallesPedido> lista=(ArrayList <DetallesPedido>)sesion.getAttribute("carrito");
		if(lista!=null) {
			for (DetallesPedido productos : lista) {
				if(productos.getIdProducto()==id)
				{
						Integer mas= productos.getUnidades()-1;
						if(mas<=0) {
							lista.remove(productos.getId());//Como eliminar de session
						}else {
							productos.setUnidades(mas);
							double total = productos.getPrecioUnidad()*mas;
							productos.setTotal(total);
						}

				}
			}
		}
			
		
		return "carrito/compra";
	}
	@GetMapping("/mostrar")
	public String mostrarCarrito(HttpSession sesion, Model model) {
		//Usuarios u = (Usuarios)sesion.getAttribute("usuario");
		//if(u!=null) {
			
			return "carrito/compra";
		//}else return "login/loginForm"; 
	}
}
