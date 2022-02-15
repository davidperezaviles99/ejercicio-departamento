package com.ejercicio.individual15.services;

import java.util.List;

import com.ejercicio.individual15.entity.Departamento;

public interface DepartamentoService {
	
	public List<Departamento> findAll();
	
	public Departamento findbyId(Long Id);
	
	public Departamento save(Departamento departamento);
	
	public void delete(Long id);

}
