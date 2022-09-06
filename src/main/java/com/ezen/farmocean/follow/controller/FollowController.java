package com.ezen.farmocean.follow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.follow.dto.Follow;
import com.ezen.farmocean.follow.mapper.FollowMapper;
import com.ezen.farmocean.follow.service.FollowService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class FollowController {
	
	@Autowired
	FollowService service;
	
	private FollowMapper mapper;
	
	public FollowController(FollowMapper mapper) {
		this.mapper = mapper;
	}
	
	@PostMapping(value="/follow/follow", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer insert(@RequestBody Follow Follow) {
		int row =service.insert(Follow);
		return row;
	}
	
	@DeleteMapping(value="/follow/following", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer delete(@RequestBody Follow Follow) {
		int row =service.delete(Follow);
		return row;
	}
	
}
