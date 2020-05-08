package org.htk.bot.controller;

import org.htk.bot.models.UserHtk;
import org.htk.bot.pojo.MessengerResponse;
import org.htk.bot.pojo.dto.UserDTO;
import org.htk.bot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Users")
@RequestMapping("api/v1")
public class UserHtkController {
	
	
	@Autowired
	private UserService userService;
	
	
	@ApiOperation(value="cria um usuário")
	@PostMapping(value="/create",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> createUser(@RequestBody UserDTO user){
		
		
		if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
			
			MessengerResponse m = new MessengerResponse("400 BAD REQUEST", "Parametros incorretos nulos ou incorretos.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
		}else {
		
			UserHtk user1= new UserHtk(user.getUsername(),user.getPassword(),user.getEmail());
			boolean result = this.userService.createUser(user1);
			if(result) {
				MessengerResponse m = new MessengerResponse("201 ACCEPTED", "Usuario criado com sucesso.");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(m);
			}else {
				MessengerResponse m = new MessengerResponse("403 FORBIDDEN", "Já existe um usuário com este username");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(m);
			}
		}
		
	}
	
	
	
	

}
