package com.ezen.farmocean.mainPage.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.dto.ProductView;
import com.ezen.farmocean.mainPage.mapper.ProductListMapper;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.prod.dto.ProdImg;
import com.ezen.farmocean.prod.service.ProdImgServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductListServiceImpl implements ProductListService{
	
	@Autowired
	private ProductListMapper prodListMapper;
	
	@Autowired
	ProdImgServiceImpl iService;
	@Autowired
	ProductListService service;
	
	@Autowired
	ProductListServiceImpl prodservice;
	
	@Autowired
	HttpSession session;

	// »óÇ° + ¸â¹ö ÃÖ½Å¼ø
	@Override
	public List<ProductView> getProcNewList(String member_id) {
		
		List<ProductView> imgList = prodListMapper.getProcNewList(member_id);
		
		for(ProductView p : imgList) {
			List<ProdImg> iList = iService.getImgsByProdIdx(p.getProd_idx());
//			log.info(p.getProd_idx());
			if (iList.size() > 0) {
				p.setImg_url(iList.get(0).getImg_url());
//				log.info(p.getImg_url());
				
			}
		}
		return imgList;
	}
	
	// »óÇ° + ¸â¹ö ÀÎ±â¼ø
	@Override
	public List<ProductView> getProcPopList(String member_id) {
		
		List<ProductView> imgList = prodListMapper.getProcPopList(member_id);
		
		for(ProductView p : imgList) {
			List<ProdImg> iList = iService.getImgsByProdIdx(p.getProd_idx());
//			log.info(p.getProd_idx());
			if (iList.size() > 0) {
				p.setImg_url(iList.get(0).getImg_url());
//				log.info(p.getImg_url());
				
			}
		}
		return imgList;
	}
	
	// »óÇ° + ¸â¹ö Âò
	@Override
	public List<ProductView> getProcBidsList(String member_id) {
		
		List<ProductView> imgList = prodListMapper.getProcBidsList(member_id);
		
		for(ProductView p : imgList) {
			List<ProdImg> iList = iService.getImgsByProdIdx(p.getProd_idx());
//			log.info(p.getProd_idx());
			if (iList.size() > 0) {
				p.setImg_url(iList.get(0).getImg_url());
//				log.info(p.getImg_url());
				
			}
		}
		return imgList;
	}
	
	@Override
	public List<Member> getProfileImg(String member_id) {
		
		return prodListMapper.getProfileImg(member_id);
	}
	
	@Override
	public List<Member> getMember(String member_id) {
		
		List<Member> imgList = prodListMapper.getMember(member_id);
		
		for(Member p : imgList) {
			List<Member> iList = prodservice.getProfileImg(p.getMember_id());
			log.info(p.getMember_id());
			if (iList.size() > 0) {
				p.setMember_image(iList.get(0).getMember_image());
				log.info(p.getMember_image());
				
			}
		}
		return imgList;
	}
	
}
