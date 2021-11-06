package com.example.repositories;
import com.example.modelos.Usuarios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuarios, Integer>{
	Usuarios findById(int id);
	Usuarios findByEmail(String email);
}
