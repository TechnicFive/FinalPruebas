package com.example.controlador;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
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
import com.example.servicios.ProveedoresServicios;
import com.example.servicios.UsuarioServicios;


@Controller
@RequestMapping("")
public class MostrarProductos {
	
	Base64 base64 = new Base64(); //Esto es para cifrar contraseñas
	@Autowired
	ProductoServicios usu;
	@Autowired
	CategoriaServicios usa;
	
	@Autowired
	ProveedoresServicios proveedores;
	
	@Autowired
	UsuarioServicios usuario;
	
	@GetMapping("")
	public String Productos(Model model, HttpSession sesion) {
		comprobarAdministrador();
		if(sesion.getAttribute("carrito")==null) {
			crearCarrito(sesion);
		}
		sesion.setAttribute("Categorias", usa.darCategorias());
		model.addAttribute("Productos", usu.DarProducto());
		return "index";
	}
	
	//Aqui hacemos la comprobacion de que si no existe admin lo cree
	private void comprobarAdministrador() {
		String correo="admin@admin";
		Usuarios u = usuario.findByEmail(correo);
		if(u==null) {
			String contrasena="admin";
			String contrasenaEncriptada = new String(base64.encode(contrasena.getBytes()));
			Usuarios admin= new Usuarios(1, "admin", contrasenaEncriptada, correo);
			usuario.save(admin);
		}
		
	}



	@GetMapping("/volver")
	public String volverProductos(Model model, HttpSession sesion) {
		return "redirect:/";
	}
	@GetMapping("/nuevospro")
	public String ruta(Model model, HttpSession sesion) {
		
		//TODO aqui va el model.addAtribute de Categorias
		model.addAttribute("categorias", usa.darCategorias());
		model.addAttribute("proveedores", proveedores.DarProveedores());
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
	
	@GetMapping("/bajo")
	public String precioBajo(Model model, HttpSession sesion) {
		ArrayList<Productos> p= usu.ordenarAsc();
		model.addAttribute("Productos", p);
		return "index";
	}
	
	@GetMapping("/alto")
	public String precioAlto(Model model, HttpSession sesion) {
		ArrayList<Productos> p= usu.ordenarDes();
		model.addAttribute("Productos", p);
		return "index";
	}
	
}
