package com.example.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelos.DetallesPedido;
import com.example.repositories.DetallesPedidoRepositorio;

@Service
public class DetalesPedidoServicios {
	
	@Autowired
	private DetallesPedidoRepositorio usu;
	

	
	public Iterable DarProducto() {
		return usu.findAll();
	}
	
	public DetallesPedido findById(int id) {
		
		DetallesPedido us = usu.findById(id);
		
		return us;
	}
	
	public void save(DetallesPedido pedido) {
		usu.save(pedido);
	}
}
