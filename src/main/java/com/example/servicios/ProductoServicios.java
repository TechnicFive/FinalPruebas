package com.example.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelos.Productos;
import com.example.repositories.ProductosRepositorio;

@Service
public class ProductoServicios {
	
	@Autowired
	private ProductosRepositorio usu;
	

	
	public Iterable DarProducto() {
		return usu.findAll();
	}
	
	public Productos findById(int id) {
		
		Productos us = usu.findById(id);
		
		return us;
	}
	
	public void save(Productos producto) {
		usu.save(producto);
	}
}
