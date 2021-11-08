package com.example.repositories;
import com.example.modelos.Productos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepositorio extends JpaRepository<Productos, Integer>{
	Productos findById(int id);
}
