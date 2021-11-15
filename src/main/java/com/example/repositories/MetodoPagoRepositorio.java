package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modelos.metodosPago;

@Repository
public interface MetodoPagoRepositorio extends JpaRepository<metodosPago, Integer> {
	metodosPago findById(int id);
}
