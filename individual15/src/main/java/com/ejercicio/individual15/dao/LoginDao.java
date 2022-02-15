package com.ejercicio.individual15.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.individual15.entity.Login;

@Repository
public interface LoginDao extends CrudRepository<Login, Long>{

}
