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

import com.ejercicio.individual15.entity.Jefe;
import com.ejercicio.individual15.services.JefeService;

@RestController
@RequestMapping("/api")
public class JefeController {
	
	@Autowired
	private JefeService servicio;
	
	@GetMapping("/jefes")
	public List<Jefe> jefe(){
		return servicio.findAll();
	}
	
	@GetMapping("/jefes/{id}")
	public ResponseEntity<?> jefeShow(@PathVariable Long id){
		Jefe jefe = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			jefe = servicio.findbyId(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la consulta");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(jefe == null) {
			response.put("mensaje", "El jefe id:".concat(id.toString().concat("No existe")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Jefe>(jefe,HttpStatus.OK);
	}
	
	@PostMapping("/jefes")
	public ResponseEntity<?> save(@RequestBody Jefe jefe){
		Jefe jefeNew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			jefeNew = servicio.save(jefe);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la insercion");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El jefe fue creado con exito.");
		response.put("jefe", jefeNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/jefes/{id}")
	public ResponseEntity<?> updateJefe(@RequestBody Jefe jefe, @PathVariable Long id){
		Jefe jefeActual = servicio.findbyId(id);
		Map<String,Object> response = new HashMap<>();
		
		try {
			jefeActual.setDNI(jefe.getDNI());
			jefeActual.setNombre(jefe.getNombre());
			jefeActual.setSalario(jefe.getSalario());
			jefeActual.setTelefono(jefe.getTelefono());
			jefeActual.setDepartamento(jefe.getDepartamento());
			
			servicio.save(jefeActual);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la actualizacion");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El jefe fue modificado con exito.");
		response.put("jefe", jefeActual);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/jefes/{id}")
	public ResponseEntity<?> deleteshowJefes(@PathVariable Long id) {
		Jefe jefeborrado = servicio.findbyId(id);
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			if(jefeborrado == null) {
				response.put("mensaje", "El jefe id:".concat(id.toString().concat("No existe")));
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			servicio.delete(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al eliminar el jefe");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El jefe fue eliminado con exito.");
		response.put("jefe", jefeborrado);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	}

}
