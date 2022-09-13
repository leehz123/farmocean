package com.ezen.farmocean.admin.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Banner {
	
	private Integer idx;
	private String cate;
	private Integer target;
	private String title;
	private String link;
    private Date regdate;
    private String filename;
    private Integer use;
    
    public Banner(Integer idx, String cate, Integer target, String title, String link, String filename) {
    	
    	this.idx = idx;    	
    	this.cate = cate;
    	this.target = target;
    	this.title = title;
    	this.link = link;
    	this.filename = filename;
    	this.use = 1;
    	
    }

}
