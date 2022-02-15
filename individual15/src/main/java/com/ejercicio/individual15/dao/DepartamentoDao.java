package com.ejercicio.individual15.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.individual15.entity.Departamento;

@Repository
public interface DepartamentoDao extends CrudRepository<Departamento, Long>{

}
