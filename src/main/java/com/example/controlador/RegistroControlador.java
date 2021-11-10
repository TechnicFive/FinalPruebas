package com.example.controlador;

import java.util.ArrayList;
import java.util.List;

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

import com.example.modelos.Productos;
import com.example.modelos.Usuarios;
import com.example.servicios.UsuarioServicios;



@Controller
@RequestMapping("/usuarios")
public class RegistroControlador {
	@Autowired
	UsuarioServicios usu;

	@GetMapping("/registro")
	public String Registro() {
		
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String Login(@ModelAttribute Usuarios usuario, Model model, HttpSession sesion, RedirectAttributes redirect) {
		Usuarios u=usu.findByEmail(usuario.getEmail());
		//String pagina="redirect:/usuarios/registro";
		if(u!=null) {
			if(u.getClave().equals(usuario.getClave())) {
				sesion.setAttribute("usuario", u);
				return "redirect:/";
			}else {
				model.addAttribute("mensaje", "El usuario o la contraseña no son correctos");
				return "login/loginForm";
			}
			
		}else {
			model.addAttribute("mensaje", "El usuario o la contraseña no son correctos");
			return "login/loginForm";
		}
		
		
	}
	
	@PostMapping("/registrarse")
	public String Resgistarse(@ModelAttribute Usuarios registro, Model model, HttpSession sesion, RedirectAttributes redirect) {
		Usuarios u= usu.findByEmail(registro.getEmail());
		
		if(u==null) {
			registro.setIdRol(3);
			usu.save(registro);
		}
		
		return "redirect:/usuarios/registro";
		
	}
	
	@GetMapping("/logout")
	public String CerrarSesion(Model model, HttpSession sesion) {
		sesion.invalidate();
		//cerrarCarrito(sesion);
		return "redirect:/";
	}
	
	public void cerrarCarrito(HttpSession sesion) {
		sesion.setAttribute("carrito", null);
		//Cuando compremos se pone null
			
	}
	
	
	@GetMapping("/muestralistado")
	public String muestraListado(Model model, HttpSession sesion) {
		//List<Usuarios> p=usu.DarUsuarios();
		model.addAttribute("usuarioss", usu.DarUsuarios());
		return "muestrasyeliminaciones/accionesUsuarios";
	}
	
	@GetMapping("/eliminar/{n}")
	public String eliminarUsuario(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Usuarios usuario = usu.findById(id);
		usu.eliminar(usuario);
		return "redirect:/usuarios/muestralistado";
	}
	
	@GetMapping("/editar/{n}")
	public String editarPerfil(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Usuarios usuario= usu.findById(id);
		model.addAttribute("usu", usuario);
		return "muestrasyeliminaciones/editarUsuario";
		
	}
	
	@PostMapping("/edicion")
	public String editarPerfil(@ModelAttribute Usuarios registro, Model model, HttpSession sesion, RedirectAttributes redirect) {
		usu.save(registro);
		return "redirect:/usuarios/muestralistado";
	}
	
	@GetMapping("/perfil")
	public String perfil(Model model, HttpSession sesion) {

		return "muestrasyeliminaciones/perfil";
	}
	
	@GetMapping("/editarUno/{n}")
	public String editarPerfilUno(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Usuarios usuario= usu.findById(id);
		model.addAttribute("usu", usuario);
		return "muestrasyeliminaciones/editarUsuarioUnico";
		
	}
	
	@PostMapping("/edicionUno")
	public String editarPerfilUno(@ModelAttribute Usuarios registro, Model model, HttpSession sesion, RedirectAttributes redirect) {
		usu.save(registro);
		sesion.setAttribute("usuario", registro);
		return "redirect:/";
	}
	
	
}
