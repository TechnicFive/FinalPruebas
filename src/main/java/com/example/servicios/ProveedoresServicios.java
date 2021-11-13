package com.example.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelos.Proveedores;
import com.example.repositories.ProveedoresRepositorio;

@Service
public class ProveedoresServicios {
	
	@Autowired
	ProveedoresRepositorio proveedores;
	
	public List<Proveedores> DarProveedores() {
		return proveedores.findAll();
	}
	
	public Proveedores findById(int id) {
		return proveedores.findById(id);
	}
	
	public void save(Proveedores proveedor) {
		proveedores.save(proveedor);
	}
	
	public void eliminar(Proveedores proveedor) {
		proveedores.delete(proveedor);
	}
}
