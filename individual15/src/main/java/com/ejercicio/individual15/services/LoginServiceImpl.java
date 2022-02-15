package com.ejercicio.individual15.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejercicio.individual15.dao.LoginDao;
import com.ejercicio.individual15.entity.Login;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;

	@Override
	@Transactional(readOnly = true)
	public List<Login> findAll() {
		return (List<Login>)loginDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Login findById(Long id) {
		return loginDao.findById(id).orElse(null);
	}

	@Transactional
	public Login save(Login login) {
		return loginDao.save(login);
	}

	@Transactional
	public Login delete(Long id) {
		Login loginDaoBorrado = findById(id);
		loginDao.deleteById(id);
		return 	loginDaoBorrado;
	}

}
