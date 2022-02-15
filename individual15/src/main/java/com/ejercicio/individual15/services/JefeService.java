package com.ejercicio.individual15.services;

import java.util.List;

import com.ejercicio.individual15.entity.Jefe;

public interface JefeService {
	
	public List<Jefe> findAll();
	
	public Jefe findbyId(Long Id);
	
	public Jefe save(Jefe jefe);
	
	public void delete(Long id);

}
