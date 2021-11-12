package com.example.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelos.Categorias;
import com.example.repositories.CategoriasRepositorio;


@Service
public class CategoriaServicios {

	@Autowired
	private CategoriasRepositorio usu;
	
	public Iterable darCategorias() {
		return usu.findAll();
	}
	
	public Categorias findById(int id) {
		
		Categorias us = usu.findById(id);
		
		return us;
	}
	
	public void save(Categorias cate) {
		usu.save(cate);
	}
}
