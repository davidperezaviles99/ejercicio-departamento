package com.ejercicio.individual15.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicio.individual15.dao.JefeDao;
import com.ejercicio.individual15.entity.Jefe;

@Service
public class JefeServiceImpl implements JefeService{

	@Autowired
	private JefeDao jefeDao;
	
	@Override
	public List<Jefe> findAll() {
		return (List<Jefe>) jefeDao.findAll();
	}

	@Override
	public Jefe findbyId(Long Id) {
		return jefeDao.findById(Id).orElse(null);
	}

	@Override
	public Jefe save(Jefe jefe) {
		return jefeDao.save(jefe);
	}

	@Override
	public void delete(Long id) {
		jefeDao.deleteById(id);
		
	}

}
