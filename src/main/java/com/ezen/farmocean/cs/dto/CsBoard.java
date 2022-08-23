package com.ezen.farmocean.cs.dto;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CsBoard {
	
	private Integer	board_idx;      
	private Integer board_cate;      
	private Integer board_header;      
	private String	board_title;  
	private String	board_memo; 
	private Integer board_count;      
	private String	board_writer;   
	private Date	board_in_date;           
	private String	board_mod_writer;   
	private Date	board_mod_date;    

}
