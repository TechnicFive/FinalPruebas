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

import com.example.modelos.Categorias;
import com.example.modelos.DetallesPedido;
import com.example.modelos.Productos;
import com.example.modelos.Usuarios;
import com.example.servicios.CategoriaServicios;
import com.example.servicios.ProductoServicios;


@Controller
@RequestMapping("")
public class MostrarProductos {
	@Autowired
	ProductoServicios usu;
	@Autowired
	CategoriaServicios usa;
	
	@GetMapping("")
	public String Productos(Model model, HttpSession sesion) {
		ArrayList<Categorias> categoria = (ArrayList<Categorias>) usa.darCategorias();
		if(sesion.getAttribute("carrito")==null) {
			crearCarrito(sesion);
		}
		sesion.setAttribute("Categorias", categoria);
		model.addAttribute("Productos", usu.DarProducto());
		return "index";
	}
	
	@GetMapping("/volver")
	public String volverProductos(Model model, HttpSession sesion) {
		if(sesion.getAttribute("carrito")==null) {
			crearCarrito(sesion);
		}
		model.addAttribute("Productos", usu.DarProducto());
		return "index";
	}
	@GetMapping("/nuevospro")
	public String ruta(Model model, HttpSession sesion) {
		
		//TODO aqui va el model.addAtribute de Categorias
		model.addAttribute("categorias", usa.darCategorias());
		return "muestrasyeliminaciones/formProductos";
	}
	
	
	@PostMapping("/nuevoProducto")
	public String nuevoProducto(@ModelAttribute Productos producto, Model model, HttpSession sesion, RedirectAttributes redirect) {
		usu.save(producto);
		return "redirect:/";
	}
	
	public void crearCarrito(HttpSession sesion) {
		ArrayList <DetallesPedido> lista= new ArrayList <DetallesPedido>();
		
		sesion.setAttribute("carrito", lista);
		//Cuando compremos se pone null
			
	}
	
	@GetMapping("/muestralistado")
	public String muestraListado(Model model, HttpSession sesion) {
		model.addAttribute("Productos", usu.DarProducto());
		return "muestrasyeliminaciones/accionesProductos";
	}
	
	
	@GetMapping("/eliminar/{n}")
	public String eliminarCarrito(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Productos producto = usu.findById(id);
		usu.eliminar(producto);
		return "redirect:/muestralistado";
	}
	

	@GetMapping("/producto/{n}")
	public String detallesProducto(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Productos producto = usu.findById(id);
		model.addAttribute("producto", producto);
		return "muestrasyeliminaciones/detallesProducto";
	}
	
	@GetMapping("/editar/{n}")
	public String editarProducto(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Productos producto= usu.findById(id);
		model.addAttribute("pro", producto);
		return "muestrasyeliminaciones/editarProducto";
	}
	
	@PostMapping("/edicion")
	public String editarProducto(@ModelAttribute Productos producto, Model model, HttpSession sesion, RedirectAttributes redirect) {
		usu.save(producto);
		return "redirect:/muestralistado";
	}
	
	@GetMapping("/categorias/{n}")
	public String mostrarDatosCategorias(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Categorias categoria = (Categorias)usa.findById(id);
		ArrayList<Productos> producto = (ArrayList<Productos>) usu.DarProducto();
		ArrayList<Productos> mostrarProductos = new ArrayList<Productos>();
		if (producto!=null) {
			for (Productos pro : producto) {
				if(pro.getIdCategoria()==id) {
					mostrarProductos.add(pro);
				}
			}
		}
		model.addAttribute("categoria", categoria.getNombre());
		model.addAttribute("Productos", mostrarProductos);
		return "porCategorias/porCategorias";
	}
	
}
