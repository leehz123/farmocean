package com.ezen.farmocean.mainPage.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.service.JsonProdService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class AdminRestController {
	
	@Autowired
	JsonProdService service;
	
	@Autowired
	HttpServletRequest req;
	
	/*
	 * ����������
		1) �α��ǰ - �� ���� ���� ����Ʈ 8 ��ǰ ���
		2) �α�Ű���� - ���� ���� �����Ǿ� �ִ� Ű���� �˸� ���� ���� 10�� Ű���� ���
		3) �ֽż�, �α�� ������ ���������� �Խ�	[22.08.29] - ��ǰ DTO ���� �ؾ���
		4) ī�װ�(ä��, ����, ...) �� ��ǰ ��ȸ���� [22.08.29]
		5) ��ǰ �Խñ� �ۼ�, ����, ����	 * 
	 * 
	 */
	
	/**
	 * 3) �ֽż�, �α�� ������ ���������� �Խ�
	 * @param type N :  �ֽż� P : �α��
	 * @return
	 */
	
	// �̰Ÿ� a�±׷� ī��ó�� ������ �Ǵ� �� �ƴѰ�?
	@GetMapping(value = "/prod/prodjsonlist/{type}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> getProdNewList(@PathVariable char type){
		
		log.info(type);
		
		List<Product> prodList = new ArrayList<>();
		
		if(type == 'N' || type == 'n') {
			prodList = service.getProcNewList();
		}else {
			prodList = service.getProcPopList();
		}
		
		return prodList;
	}
	
	/**
	 * 4) ī�װ�(ä��, ����, ...) �� ��ǰ ��ȸ���� []
	 * @param prod_cate
	 * @return
	 */
	@GetMapping(value = "/prod/prodjsonlist/cate/{prod_cate}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> getProdCateList(@PathVariable String prod_cate){
		
		List<Product> prodList = new ArrayList<>();
		
		prodList = service.getProcCateList(prod_cate);
		
		return prodList;
	}
	
	/**
	 * 1) �α��ǰ - �� ���� ���� ����Ʈ 8 ��ǰ ���
	 * @return
	 */
	@GetMapping(value = "/prod/prodjsonlist/bids", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> getProdBidsList(){
		
		List<Product> prodList = new ArrayList<>();
		
		prodList = service.getProcBidsList();
		
		return prodList;
	}
	
}
