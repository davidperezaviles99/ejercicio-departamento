package com.ejercicio.individual15.services;

import java.util.List;

import com.ejercicio.individual15.entity.Empleado;

public interface EmpleadoService {
	
	public List<Empleado> findAll();
	
	public Empleado findbyId(Long Id);
	
	public Empleado save(Empleado empleado);
	
	public void delete(Long id);

}
