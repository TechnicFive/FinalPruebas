package com.example.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repositories.MetodoPagoRepositorio;

@Service
public class MetodosPagoServicios {
	@Autowired
	private MetodoPagoRepositorio usu;
	
	public Iterable DarMetodoPago() {
		return usu.findAll();
	}
}
