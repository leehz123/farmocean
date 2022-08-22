package com.ezen.farmocean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.dto.User;
import com.ezen.farmocean.service.UserService;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class UserRestContoller {

	@Autowired
	UserService service;

	
	// db연결 테스트 
	@GetMapping(value = "/user/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<User> listUser() {

		return service.getList();
	}

	
	// 회원가입
	@PostMapping(value = "/insert/user", produces = MediaType.TEXT_PLAIN_VALUE)
	public  ResponseEntity<User> insertUser(@RequestBody User user) {

		if(user.getId() == null || user.getId().trim().equals("") || 
				user.getName() == null||
				user.getEmail() == null ) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			service.insert(user);
			
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}

	}
}
