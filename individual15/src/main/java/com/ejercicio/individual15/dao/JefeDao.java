package com.ejercicio.individual15.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.individual15.entity.Jefe;

@Repository
public interface JefeDao extends CrudRepository<Jefe, Long> {

}
