package com.ejercicio.individual15.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.individual15.entity.Empleado;

@Repository
public interface EmpleadoDao extends CrudRepository<Empleado, Long> {

}
