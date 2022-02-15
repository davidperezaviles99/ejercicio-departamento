package com.ejercicio.individual15.services;

import java.util.List;

import com.ejercicio.individual15.entity.Login;

public interface LoginService {
	
	public List<Login> findAll();
	
	public Login findById(Long id);
	
	public Login save(Login login);
	
	public Login delete(Long id);

}
