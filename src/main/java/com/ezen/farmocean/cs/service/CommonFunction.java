package com.ezen.farmocean.cs.service;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class CommonFunction {
	
	
	
	/**
	 * �� ��üũ ���� ���̰� 0�̾ false
	 * @param o (�⺻ Ŭ����, �� Ŭ���� �� ����)
	 * @return true(null �ƴ�) false(null ��)
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
	
	// ���Խ� ���� ����
	
	/**
	 * ��й�ȣ (����, ���� ������ 6~12�ڸ� �̳�)
	 * @param value ���� �н�����
	 * @return
	 */
	public boolean chkPatternPassword(String value) {		
		String pattern = "^[A-Za-z0-9]{6,12}$";
		//��й�ȣ (����, ����, Ư������ ���� 8~15�ڸ� �̳�)	^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * ����
	 * @param value
	 * @return
	 */
	public boolean chkPatternNumber(String value) {		
		String pattern = "^[0-9]*$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * ������
	 * @param value
	 * @return
	 */
	public boolean chkPatternEng(String value) {		
		String pattern = "^[a-zA-Z]*$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * �ѱ�
	 * @param value
	 * @return
	 */
	public boolean chkPatternKor(String value) {		
		String pattern = "^[��-�R]*";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * ��+����
	 * @param value
	 * @return
	 */
	public boolean chkPatternNumAndEng(String value) {		
		String pattern = "^[a-zA-Z0-9]*$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * �̸���
	 * @param value
	 * @return
	 */
	public boolean chkPatternEmail(String value) {		
		String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * �޴���ȭ
	 * @param value
	 * @return
	 */
	public boolean chkPatternPhone(String value) {		
		String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * ���� Ȯ����
	 * @param value
	 * @return
	 */
	public boolean chkPatternFile(String value) {		
		String pattern = "^\\S+.(?i)(txt|pdf|hwp|xls)$";		
		return Pattern.matches(pattern, value);
	}
	
	// ���Խ� ���� ��
	
	/**
	 * ���� ���ڸ� ���� , ���
	 * @param num
	 * @return
	 */
	public String viewWon(long num) {
		DecimalFormat dcf = new DecimalFormat("###,###,###,###");
		return dcf.format(num);
	}
	
	
	
}
