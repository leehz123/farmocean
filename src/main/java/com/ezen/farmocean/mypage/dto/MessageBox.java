package com.ezen.farmocean.mypage.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageBox {
	
	public Integer message_id;
	private String sender_id;
	private String recipient_id;
	private String message_title;
	private String message_contents;
	private Date message_date;
	private String message_check;
	
}
