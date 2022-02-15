package com.ejercicio.individual15.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicio.individual15.dao.DepartamentoDao;
import com.ejercicio.individual15.entity.Departamento;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {
	
	@Autowired
	private DepartamentoDao departamentoDao;

	@Override
	public List<Departamento> findAll() {
		return (List<Departamento>) departamentoDao.findAll();
	}

	@Override
	public Departamento findbyId(Long Id) {
		return departamentoDao.findById(Id).orElse(null);
	}

	@Override
	public Departamento save(Departamento departamento) {
		return departamentoDao.save(departamento);
	}

	@Override
	public void delete(Long id) {
		departamentoDao.deleteById(id);
		
	}
	

}
