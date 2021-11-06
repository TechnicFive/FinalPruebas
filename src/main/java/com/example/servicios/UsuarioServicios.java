package com.example.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelos.Usuarios;
import com.example.repositories.UsuariosRepositorio;

@Service
public class UsuarioServicios {
	
	@Autowired
	private UsuariosRepositorio usu;
	
	public void cargarUsuarios() {
		Usuarios u = new Usuarios("pepe", "1234", "pepe@tiendaonline.es");
		usu.save(u);
	}
	
	public Iterable DarUsuarios() {
		return usu.findAll();
	}
	
	public Usuarios findById(int id) {
		
		Usuarios us = usu.findById(id);
		
		return us;
	}
	
	public Usuarios findByEmail(String email) {
		
		Usuarios us = usu.findByEmail(email);
		
		return us;
	}
	
	
}
