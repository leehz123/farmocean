package com.ezen.farmocean.admin.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.cs.service.CommonFunction;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class AdminRestController {
	
	@Autowired
	CommonFunction cf;
	
	@Autowired
	HttpServletRequest req;
	
	/*
	 * ����������
		1) �α��ǰ - �� ���� ���� ����Ʈ 8 ��ǰ ���
		2) �α�Ű���� - ���� ���� �����Ǿ� �ִ� Ű���� �˸� ���� ���� 10�� Ű���� ���
		3) �ֽż�, �α�� ������ ���������� �Խ�
		4) ī�װ�(ä��, ����, ...) �� ��ǰ ��ȸ����
		5) ��ǰ �Խñ� �ۼ�, ����, ����	 * 
	 * 
	 */

}
