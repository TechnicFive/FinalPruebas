package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modelos.Categorias;


@Repository
public interface CategoriasRepositorio extends JpaRepository<Categorias, Integer>{
	Categorias findById(int id);
	
}
