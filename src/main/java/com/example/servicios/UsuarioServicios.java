package com.example.servicios;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelos.Usuarios;
import com.example.repositories.UsuariosRepositorio;

@Service
public class UsuarioServicios {
	
	Logger logger= LogManager.getLogger(UsuarioServicios.class);
	
	@Autowired
	private UsuariosRepositorio usu;
	
	public List<Usuarios> DarUsuarios() {
		return usu.findAll();
	}
	
	public Usuarios findById(int id) {
		
		Usuarios us = usu.findById(id);
		if(us!=null) {
			logger.info("El usuario con la id existe");
			return us;
		}else {
			logger.info("El usuario con id no exite");
			return null;
		}
	}
	
	public Usuarios findByEmail(String email) {
		
		Usuarios us = usu.findByEmail(email);
		if(us!=null) {
			logger.info("El usuario con el email existe");
			return us;
		}else {
			logger.info("El usuario con email no exite");
			return null;
		}
	}
	
	public void save(Usuarios usuario) {
		usu.save(usuario);
		logger.info("Se ha guardado el usuario");
	}
	
	public void eliminar(Usuarios usuario) {
		usu.delete(usuario);
		logger.info("Se ha eliminado el usuario");
	}
	
}
