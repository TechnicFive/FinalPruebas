package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modelos.Configuracion;

@Repository
public interface ConfiguracionRepositorio extends JpaRepository<Configuracion, Integer>{
	Configuracion findById(int id);
	Configuracion findByClave(String clave);
}
