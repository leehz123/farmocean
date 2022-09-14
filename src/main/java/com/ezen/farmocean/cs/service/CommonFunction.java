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
	 * 문자열 자르기
	 * @param value 문자
	 * @param maxLen 최대 길이
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
	 * 로그인 체크
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
	 * xss 관련 등록 보이게
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
	 * 값 널체크 문자 길이가 0이어도 false
	 * @param o (기본 클래스, 모델 클래스 만 가능)
	 * @return true(null 아님) false(null 임)
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
	
	
	// 정규식 관련 시작
	
	/**
	 * 비밀번호 (숫자, 문자 포함의 6~12자리 이내)
	 * @param value 비교할 패스워드
	 * @return
	 */
	public boolean chkPatternPassword(String value) {		
		String pattern = "^[A-Za-z0-9]{6,12}$";
		//비밀번호 (숫자, 문자, 특수문자 포함 8~15자리 이내)	^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * 숫자
	 * @param value
	 * @return
	 */
	public boolean chkPatternNumber(String value) {		
		String pattern = "^[0-9]*$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * 영문자
	 * @param value
	 * @return
	 */
	public boolean chkPatternEng(String value) {		
		String pattern = "^[a-zA-Z]*$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * 영+숫자
	 * @param value
	 * @return
	 */
	public boolean chkPatternNumAndEng(String value) {		
		String pattern = "^[a-zA-Z0-9]*$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * 이메일
	 * @param value
	 * @return
	 */
	public boolean chkPatternEmail(String value) {		
		String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * 휴대전화
	 * @param value
	 * @return
	 */
	public boolean chkPatternPhone(String value) {		
		String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";		
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * 파일 확장자
	 * @param value
	 * @return
	 */
	public boolean chkPatternFile(String value) {		
		String pattern = "^\\S+.(?i)(txt|pdf|hwp|xls)$";		
		return Pattern.matches(pattern, value);
	}
	
	// 정규식 관련 끝
	
	/**
	 * 숫자 세자리 마다 , 찍기
	 * @param num
	 * @return
	 */
	public String viewWon(long num) {
		DecimalFormat dcf = new DecimalFormat("###,###,###,###");
		return dcf.format(num);
	}
	
	
	/**
	 * 장애 메세지
	 * @param errCode
	 * @return -1 : 로그인 정보가 없습니다.
	 * 		   -2 : 권한이 없습니다.
	 * 		   -3 : 주어진 값이 정상적이지 않습니다.
	 * 		   -4 : 데이터 수정에 실패했습니다.
	 * 		   -5 : 이미 정보가 있습니다.
	 *         -6 : 정보가 없습니다.
	 *         -7 : 등록에 실패했습니다.
	 */
	public String getErrMessage(Integer errCode) {		
		String result;
		
		switch (errCode){
			case 1:			
				result = "성공";
				break;
			case -1:			
				result = "로그인 정보가 없습니다.";
				break;
			case -2:			
				result = "권한이 없습니다.";
				break;
			case -3:			
				result = "주어진 값이 정상적이지 않습니다.";
				break;
			case -4:			
				result = "데이터 수정에 실패했습니다.";
				break;
			case -5:			
				result = "이미 정보가 있습니다.";
				break;
			case -6:			
				result = "정보가 없습니다.";
				break;
			case -7:			
				result = "구매 등록에 실패했습니다.";
				break;
				
			default:
				result = "장애가 발생했습니다.";
				break;
		}
			
		return result;
	}
	
}
