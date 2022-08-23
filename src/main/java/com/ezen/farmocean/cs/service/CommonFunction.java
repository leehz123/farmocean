package com.ezen.farmocean.cs.service;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class CommonFunction {
	
	
	
	/**
	 * °ª ³ÎÃ¼Å© ¹®ÀÚ ±æÀÌ°¡ 0ÀÌ¾îµµ false
	 * @param o (±âº» Å¬·¡½º, ¸ğµ¨ Å¬·¡½º ¸¸ °¡´É)
	 * @return true(null ¾Æ´Ô) false(null ÀÓ)
	 */
	public boolean chkNull(Object o) {
		
		if(o == null) {
			return true;
		}
		
		String[] arrEnt = o.getClass().getTypeName().toString().split("\\.");
		
		if(arrEnt.length > 1) {
			
			if(arrEnt[0].equals("java") && arrEnt[1].equals("lang")) {
				
				String s = String.valueOf(o);
				return s.trim().length() == 0;
				
			}else {
				for(Field f : o.getClass().getDeclaredFields()) {
					
					f.setAccessible(true);
					
					try {
						if(f.get(o) == null || f.get(o).toString().trim().length() == 0) {
							return true;
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
						return true;
					}
				}
			}
			
			return false;

		}else {
			return true;
		}
	}
	
	// Á¤±Ô½Ä °ü·Ã ½ÃÀÛ
	
	/**
	 * ºñ¹Ğ¹øÈ£ (¼ıÀÚ, ¹®ÀÚ Æ÷ÇÔÀÇ 6~12ÀÚ¸® ÀÌ³»)
	 * @param value ºñ±³ÇÒ ÆĞ½º¿öµå
	 * @return
	 */
	public boolean chkPatternPassword(String value) {		
		String pattern = "^[A-Za-z0-9]{6,12}$";
		//ºñ¹Ğ¹øÈ£ (¼ıÀÚ, ¹®ÀÚ, Æ¯¼ö¹®ÀÚ Æ÷ÇÔ 8~15ÀÚ¸® ÀÌ³»)	^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * ¼ıÀÚ
	 * @param value
	 * @return
	 */
	public boolean chkPatternNumber(String value) {		
		String pattern = "^[0-9]*$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * ¿µ¹®ÀÚ
	 * @param value
	 * @return
	 */
	public boolean chkPatternEng(String value) {		
		String pattern = "^[a-zA-Z]*$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * ÇÑ±Û
	 * @param value
	 * @return
	 */
	public boolean chkPatternKor(String value) {		
		String pattern = "^[°¡-ÆR]*";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * ¿µ+¼ıÀÚ
	 * @param value
	 * @return
	 */
	public boolean chkPatternNumAndEng(String value) {		
		String pattern = "^[a-zA-Z0-9]*$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * ÀÌ¸ŞÀÏ
	 * @param value
	 * @return
	 */
	public boolean chkPatternEmail(String value) {		
		String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * ÈŞ´ëÀüÈ­
	 * @param value
	 * @return
	 */
	public boolean chkPatternPhone(String value) {		
		String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * ÆÄÀÏ È®ÀåÀÚ
	 * @param value
	 * @return
	 */
	public boolean chkPatternFile(String value) {		
		String pattern = "^\\S+.(?i)(txt|pdf|hwp|xls)$";		
		return Pattern.matches(pattern, value);
	}
	
	// Á¤±Ô½Ä °ü·Ã ³¡
	
	/**
	 * ¼ıÀÚ ¼¼ÀÚ¸® ¸¶´Ù , Âï±â
	 * @param num
	 * @return
	 */
	public String viewWon(long num) {
		DecimalFormat dcf = new DecimalFormat("###,###,###,###");
		return dcf.format(num);
	}
	
	
	
}
