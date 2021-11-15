package com.example.servicios;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelos.Productos;
import com.example.repositories.ProductosRepositorio;

@Service
public class ProductoServicios {
	
	Logger logger= LogManager.getLogger(ProductoServicios.class);
	
	@Autowired
	private ProductosRepositorio usu;
	

	
	public Iterable DarProducto() {
		return usu.findAll();
	}
	
	public Productos findById(int id) {
		
		Productos us = usu.findById(id);
		if(us!=null) {
			logger.info("El Producto con la id existe");
			return us;
		}else {
			logger.info("El Producto con id no exite");
			return null;
		}
	}
	
	public void save(Productos producto) {
		usu.save(producto);
		logger.info("Se ha guardado el usuario");
	}
	
	public void eliminar(Productos producto) {
		usu.delete(producto);
		logger.info("Se ha eliminado el usuario");
	}
	
	public ArrayList<Productos> ordenarAsc(){
		ArrayList<Productos> us=usu.findByPrecio();
		if(us!=null) {
			logger.info("El Array list esta ordenador por precio y existe");
			return us;
		}else {
			logger.info("El Array list ordenado por precio no existe");
			return null;
		}
		
	}
	
	public ArrayList<Productos> ordenarDes(){
		ArrayList<Productos> us=usu.findByPrecioDes();
		if(us!=null) {
			logger.info("El Array list esta ordenador por precio y existe");
			return us;
		}else {
			logger.info("El Array list ordenado por precio no existe");
			return null;
		}
	}
}
