package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modelos.Pedidos;

@Repository
public interface PedidosRepositorio  extends JpaRepository<Pedidos, Integer>{
	Pedidos findById(int id);
}
