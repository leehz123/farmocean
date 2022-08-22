package com.ezen.farmocean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	private String id;
	private String pw;
	private String name;
	private String email;
	 
}
