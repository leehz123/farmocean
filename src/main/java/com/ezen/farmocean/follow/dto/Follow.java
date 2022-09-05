package com.ezen.farmocean.follow.dto;

import java.sql.Date;

import com.ezen.farmocean.mypage.dto.MessageBox;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
	
	public Integer follow_id;
	private String follower_id;
	private String followee_id;
	
}
