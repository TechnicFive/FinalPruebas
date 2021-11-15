package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modelos.Proveedores;

@Repository
public interface ProveedoresRepositorio extends JpaRepository<Proveedores, Integer>{
	Proveedores findById(int id);
}
