package com.ezen.farmocean.prod.rest;


import java.sql.Date;

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
		return prod.insertProd();
	}
}


/*
	
	Integer prod_idx;
	String sell_id; 
	String prod_name;
	String prod_cate;
	String prod_img;
	String prod_info;
	Integer prod_price;
	String prod_from;
	String prod_sell;
	Date prod_sell_deadline;//java.sql.Date
	
 */

