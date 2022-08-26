package com.ezen.farmocean.prod.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/product")
@Controller
public class prodController {

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String product_detail(Locale locale, Model model) {

		return "product/product_detail";
	}
	
}
