package com.example.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repositories.CategoriasRepositorio;


@Service
public class CategoriaServicios {
	//TODO tengo que hacer la vista de mostrar un foreach con las categorias
	@Autowired
	private CategoriasRepositorio usu;
	
	public Iterable darCategorias() {
		return usu.findAll();
	}
}
