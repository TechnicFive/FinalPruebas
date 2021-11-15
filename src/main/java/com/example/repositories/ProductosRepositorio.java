package com.example.repositories;
import com.example.modelos.Pedidos;
import com.example.modelos.Productos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepositorio extends JpaRepository<Productos, Integer>{
	Productos findById(int id);
	
	@Query("select p from Productos p order by p.precio")
	ArrayList<Productos> findByPrecio();
	
	@Query("select p from Productos p order by p.precio desc")
	ArrayList<Productos> findByPrecioDes();
}
