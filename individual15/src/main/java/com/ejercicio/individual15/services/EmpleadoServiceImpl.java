package com.ejercicio.individual15.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejercicio.individual15.dao.EmpleadoDao;
import com.ejercicio.individual15.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoDao empleadoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return (List<Empleado>) empleadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findbyId(Long Id) {
		return empleadoDao.findById(Id).orElse(null);
	}

	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		return empleadoDao.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		empleadoDao.deleteById(id);
		
	}

}
