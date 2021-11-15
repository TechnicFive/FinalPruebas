package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modelos.DetallesPedido;

@Repository
public interface DetallesPedidoRepositorio extends JpaRepository<DetallesPedido, Integer>{
	DetallesPedido findById(int id);
}
