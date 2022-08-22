package com.ezen.farmocean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.dto.User;
import com.ezen.farmocean.service.UserService;

import lombok.extern.log4j.Log4j;
@Log4j
@RestController
public class UserRestContoller {
	
	@Autowired
	UserService service;
	
	@GetMapping(value= "/user/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<User> listUser() {
		
		return service.getList();
	}
}
