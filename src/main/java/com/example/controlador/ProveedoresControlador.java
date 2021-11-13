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

import com.example.modelos.Proveedores;
import com.example.servicios.ProveedoresServicios;

@Controller
@RequestMapping("/proveedor")
public class ProveedoresControlador {
	@Autowired
	ProveedoresServicios proveedor;
	@GetMapping("/mostrar")
	public String mostrar(Model model, HttpSession sesion) {
		ArrayList<Proveedores> prov = (ArrayList<Proveedores>) proveedor.DarProveedores();
		
		model.addAttribute("proveedores", prov);
		return "proveedores/mostrarProveedores";
	}
	
	@GetMapping("/crear")
	public String crear(Model model, HttpSession sesion, RedirectAttributes redirect) {
		return "proveedores/crearProveedores";
	}
	
	@PostMapping("/crear")
	public String crearlo(@ModelAttribute Proveedores prov, Model model, HttpSession sesion, RedirectAttributes redirect) {
		proveedor.save(prov);
		return "redirect:/proveedor/mostrar";
	}
	
	@GetMapping("/editar/{n}")
	public String editar(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Proveedores este = (Proveedores) proveedor.findById(id);
		model.addAttribute("proveedor", este);
		return "proveedores/editarProveedores";
	}
	
	@PostMapping("/edicion")
	public String edicion(@ModelAttribute Proveedores config, Model model, HttpSession sesion, RedirectAttributes redirect) {
		Proveedores uno= proveedor.findById(config.getId());
		proveedor.save(config);
		return "redirect:/proveedor/mostrar";
	}
	
	@GetMapping("/eliminar/{n}")
	public String eliminar(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Proveedores este = (Proveedores) proveedor.findById(id);
		proveedor.eliminar(este);
		return "redirect:/proveedor/mostrar";
	}
}
