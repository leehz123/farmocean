package com.ezen.farmocean.prod.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.prod.service.ProdServiceImpl;


@RestController
public class ProdRestController {

	@Autowired
	ProdServiceImpl prod;
	
	@GetMapping(value="/sample/prod/insert", produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer insertProd(){
		return prod.insertProdExceptCate();
	}

	
}

