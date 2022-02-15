package com.ejercicio.individual15.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.individual15.entity.Departamento;
import com.ejercicio.individual15.entity.Empleado;
import com.ejercicio.individual15.services.DepartamentoService;

@RestController
@RequestMapping("/api")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService servicio;
	
	@GetMapping("/departamentos")
	public List<Departamento> departamento(){
		return servicio.findAll();
	}
	
	@GetMapping("/departamentos/{id}")
	public ResponseEntity<?> departamentoshow(@PathVariable Long id){
		Departamento departamento = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			departamento = servicio.findbyId(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la consulta");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(departamento == null) {
			response.put("mensaje", "El departamento id:".concat(id.toString().concat("No existe")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Departamento>(departamento,HttpStatus.OK);
	}
	
	@PostMapping("/departamentos")
	public ResponseEntity<?> save(@RequestBody Departamento departamento){
		Departamento departamentoNew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			departamentoNew = servicio.save(departamento);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la insercion");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El departamento fue creado con exito.");
		response.put("departamento", departamentoNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/departamentos/{id}")
	public ResponseEntity<?> updateDepartamento(@RequestBody Departamento departamento, @PathVariable Long id){
		Departamento departamentoActual = servicio.findbyId(id);
		Map<String,Object> response = new HashMap<>();
		
		try {
			departamentoActual.setNombre(departamento.getNombre());
			departamentoActual.setUbicacion(departamento.getUbicacion());
			
			servicio.save(departamentoActual);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la actualizacion");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El departamento fue modificado con exito.");
		response.put("departamento", departamentoActual);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/departamentos/{id}")
	public ResponseEntity<?> deleteshowDepartamento(@PathVariable Long id) {
		Departamento departamentoborrado = servicio.findbyId(id);
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			if(departamentoborrado == null) {
				response.put("mensaje", "El departamento id:".concat(id.toString().concat("No existe")));
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			servicio.delete(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al eliminar el departamento");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El departamemto fue eliminado con exito.");
		response.put("departamento", departamentoborrado);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	}

}
