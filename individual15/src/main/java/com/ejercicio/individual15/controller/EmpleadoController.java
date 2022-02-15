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

import com.ejercicio.individual15.entity.Empleado;
import com.ejercicio.individual15.services.EmpleadoService;

@RestController
@RequestMapping("/api")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService servicio;
	
	@GetMapping("/empleados")
	public List<Empleado> empleado(){
		return servicio.findAll();
	}
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> empleadoShow(@PathVariable Long id){
		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleado = servicio.findbyId(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la consulta");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(empleado == null) {
			response.put("mensaje", "El empleado id:".concat(id.toString().concat("No existe")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Empleado>(empleado,HttpStatus.OK);
	}
	
	@PostMapping("/empleados")
	public ResponseEntity<?> save(@RequestBody Empleado empleado){
		Empleado empleadoNew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			empleadoNew = servicio.save(empleado);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la insercion");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El empleado fue creado con exito.");
		response.put("empleado", empleadoNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/empleados/{id}")
	public ResponseEntity<?> updateEmpleado(@RequestBody Empleado empleado, @PathVariable Long id){
		Empleado empleadoActual = servicio.findbyId(id);
		Map<String,Object> response = new HashMap<>();
		
		try {
			empleadoActual.setDNI(empleado.getDNI());
			empleadoActual.setNombre(empleado.getNombre());
			empleadoActual.setSalario(empleado.getSalario());
			empleadoActual.setTelefono(empleado.getTelefono());
			empleadoActual.setDepartamento(empleado.getDepartamento());
			
			servicio.save(empleadoActual);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la actualizacion");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El empleado fue modificado con exito.");
		response.put("empleado", empleadoActual);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<?> deleteshowEmpleado(@PathVariable Long id) {
		Empleado empleadoborrado = servicio.findbyId(id);
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			if(empleadoborrado == null) {
				response.put("mensaje", "El empleado id:".concat(id.toString().concat("No existe")));
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			servicio.delete(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al eliminar el empleado");
			response.put("Error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El empleado fue eliminado con exito.");
		response.put("empleado", empleadoborrado);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	}

}
