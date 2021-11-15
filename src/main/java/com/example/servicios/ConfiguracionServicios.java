package com.example.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelos.Configuracion;
import com.example.repositories.ConfiguracionRepositorio;

@Service
public class ConfiguracionServicios {
	
	@Autowired
	private ConfiguracionRepositorio usu;
	
	public Iterable darConfiguracion() {
		return usu.findAll();
	}
	
	public Configuracion findByClave(String clave) {
		
		Configuracion us = usu.findByClave(clave);
		
		return us;
	}
	
	public Configuracion findById(int id) {
		
		Configuracion us = usu.findById(id);
		
		return us;
	}
	
	public void save(Configuracion config) {
		usu.save(config);
	}
	
	public void eliminar(Configuracion config) {
		usu.delete(config);
	}
}
