package com.example.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelos.Pedidos;
import com.example.repositories.PedidosRepositorio;


@Service
public class PedidosServicios {
	@Autowired
	private PedidosRepositorio usu;
	
	public Iterable darPedidos() {
		return usu.findAll();
	}
	
	public Pedidos save(Pedidos pedido) {
		return usu.save(pedido);
	}
	
	public Pedidos findById(int id) {
		
		Pedidos us = usu.findById(id);
		
		return us;
	}
}

