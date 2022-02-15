package com.ejercicio.individual15.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.individual15.entity.Login;
import com.ejercicio.individual15.services.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	LoginService servicio;
	
	@PostMapping("/login")
	public ResponseEntity<?> login( @RequestParam String usuario, @RequestParam String password){
		Map<String,Object> response = new HashMap<>();
		Boolean entrar = false;
		Boolean user = false;
		
		try{
			List<Login> logins =servicio.findAll();
			for (Login login : logins) {
				if(usuario.equals(login.getUsuario())) {
					user = true;
					if(password.equals(login.getPassword())) {
						entrar = true;
						break;
					}
				}
			}
		} catch(DataAccessException e){
			response.put("mensaje", "Error al realizar consulta a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (entrar) {			
			response.put("mensaje", "Bienvenido. Usuario Correcto");
			response.put("usuario", usuario);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} else if(user) {
			response.put("mensaje", "Contraseña incorrecta");
			response.put("usuario", usuario);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} else {
			response.put("mensaje", "Usuario incorrecto");
			response.put("usuario", usuario);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}		
	}
}