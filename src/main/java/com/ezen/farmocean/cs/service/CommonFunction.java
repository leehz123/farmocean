package com.ezen.farmocean.cs.service;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ezen.farmocean.member.dto.LoginMember;

@Service
public class CommonFunction {
		
	/**
	 * ���ڿ� �ڸ���
	 * @param value ����
	 * @param maxLen �ִ� ����
	 * @return
	 */
	public String cutStr(String value, Integer maxLen) {
		
		String retStr = value;
		
		if(retStr.length() > maxLen) {
			retStr = value.substring(0, maxLen) + "..";
		}
		
		return retStr; 
		
	}
	
	/**
	 * �α��� üũ
	 * @param req
	 * @return
	 */
	public LoginMember loginInfo(HttpServletRequest req) {
		
		LoginMember mInfo = new LoginMember();
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginId") != null) {			
			mInfo = (LoginMember)session.getAttribute("loginId");
		}
		
		return mInfo;
	}
	
	/**
	 * xss ���� ��� ���̰�
	 * @param text
	 * @return
	 */
	public String chgHtml(String text) {
		
		List<String> xssList = new ArrayList<>();
		
		xssList.add("<script>");
		xssList.add("&lt;script&gt;");
		
		String trmText = text.trim();
		
		for(String xss : xssList) {
			if(trmText.trim().contains(xss)) {
				return text;
			}
		}
		
		String html = text;
		
		Map<String, String> tags = new HashMap<>();
		
		tags.put("&lt;", "<");
		tags.put("&gt;", ">");
		tags.put("&nbsp;", " ");
		tags.put("&amp;", "&");
		tags.put("&quot;", "\"");
		
		for(Entry<String, String> tag : tags.entrySet()) {
			html = html.replace(tag.getKey(), tag.getValue());			
		}
		
		return html;
	}
	
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
	
	
	/**
	 * ��� �޼���
	 * @param errCode
	 * @return -1 : �α��� ������ �����ϴ�.
	 * 		   -2 : ������ �����ϴ�.
	 * 		   -3 : �־��� ���� ���������� �ʽ��ϴ�.
	 * 		   -4 : ������ ������ �����߽��ϴ�.
	 * 		   -5 : �̹� ������ �ֽ��ϴ�.
	 *         -6 : ������ �����ϴ�.
	 *         -7 : ��Ͽ� �����߽��ϴ�.
	 */
	public String getErrMessage(Integer errCode) {		
		String result;
		
		switch (errCode){
			case 1:			
				result = "����";
				break;
			case -1:			
				result = "�α��� ������ �����ϴ�.";
				break;
			case -2:			
				result = "������ �����ϴ�.";
				break;
			case -3:			
				result = "�־��� ���� ���������� �ʽ��ϴ�.";
				break;
			case -4:			
				result = "������ ������ �����߽��ϴ�.";
				break;
			case -5:			
				result = "�̹� ������ �ֽ��ϴ�.";
				break;
			case -6:			
				result = "������ �����ϴ�.";
				break;
			case -7:			
				result = "���� ��Ͽ� �����߽��ϴ�.";
				break;
				
			default:
				result = "��ְ� �߻��߽��ϴ�.";
				break;
		}
			
		return result;
	}
	
}
