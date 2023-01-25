package com.ezen.farmocean.prod.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.dto.Product;
import com.ezen.farmocean.prod.mapper.ProdMapper;

@Service
public class ProdServiceImpl implements ProdService {

	@Autowired
	ProdMapper productMapper;
	
	@Override
	public List<Product> getProductList() {
		return productMapper.getProductList();
	}

	@Override
	public Product getProductById(Integer prod_idx) {
		return productMapper.getProductById(prod_idx);
	}	

	@Override
	public List<Product> getProductsByCate(Integer cate_idx) {
		return productMapper.getProductsByCate(cate_idx);
	}	
	
	@Override
	public List<Product> getProductsByMemberId(String member_id) {
		return productMapper.getProductsByMemberId(member_id);
	}
	
	@Override
	public List<Product> getProductsByName(String prod_name) {
		return productMapper.getProductsByName(prod_name);
	}
	
	@Override
	public Integer deleteProductById(Integer prod_idx) {
		return productMapper.deleteProductById(prod_idx);
	}

	@Override
	public Integer insertProduct(String member_id, String prod_name, String prod_info, Integer cate_idx,
			String prod_sell, Integer prod_price, Timestamp prod_sell_deadline, Integer prod_stock, Integer prod_delete,
			Integer prod_heartnum, Timestamp prod_written_date) {
		return productMapper.insertProduct(member_id, prod_name, prod_info, cate_idx, prod_sell, prod_price, prod_sell_deadline, prod_stock, prod_delete, prod_heartnum, prod_written_date);
	}

	@Override
	public Integer updateProduct(Integer prod_idx, String prod_name, String prod_info, Integer cate_idx, String prod_sell,
			Integer prod_price, Timestamp prod_sell_deadline, Integer prod_stock, Integer prod_delete, Timestamp prod_written_date) {
		return productMapper.updateProduct(prod_idx, prod_name, prod_info, cate_idx, prod_sell, prod_price, prod_sell_deadline, prod_stock, prod_delete, prod_written_date);
	}

	@Override
	public Integer updateProductDeleteToZeroByProdIdx(Integer prod_idx) {
		return productMapper.updateProductDeleteToZeroByProdIdx(prod_idx);
	}

	@Override
	public Integer expireDeadline(Integer prod_idx) {
		return productMapper.expireDeadline(prod_idx);
	}

	
	//새로 추가, 수정된 메서드들 (22.09.16)__________________________________________________________________________________________________________________________________
	
	@Override
	public List<Product> getproductListForManager() {
		return productMapper.getproductListForManager();
	}

	@Override
	public List<Product> getProductListForSeller(String member_id) {
		return productMapper.getProductListForSeller(member_id);
	}

	@Override
	public Integer updateProductStatusHide(Integer prod_idx) {
		return productMapper.updateProductStatusHide(prod_idx);
	}

	@Override
	public Integer updateProductStatusDelete(Integer prod_idx) {
		return productMapper.updateProductStatusDelete(prod_idx);
	}

	@Override
	public Integer addProduct(String member_id, String prod_name, String prod_info, Integer cate_idx,
			Integer prod_price, Timestamp prod_sell_deadline, Integer prod_stock, Integer prod_delete,
			Integer prod_heartnum, Timestamp prod_written_date) {
		return productMapper.addProduct(member_id, prod_name, prod_info, cate_idx, prod_price, prod_sell_deadline, prod_stock, prod_delete, prod_heartnum, prod_written_date);
	}

	@Override
	public Integer editProduct(Integer prod_idx, String prod_name, String prod_info, Integer cate_idx,
			Integer prod_price, Timestamp prod_sell_deadline, Integer prod_stock, Integer prod_delete, Timestamp prod_written_date) {
		return productMapper.editProduct(prod_idx, prod_name, prod_info, cate_idx, prod_price, prod_sell_deadline, prod_stock, prod_delete, prod_written_date);
	}

	@Override
	public Integer getProdIdxByIdAndDate(String member_id, Timestamp prod_written_date) {
		return productMapper.getProdIdxByIdAndDate(member_id, prod_written_date); 
	}

	@Override
	public String getSellerIdByProdIdx(Integer prod_idx) {
		return productMapper.getSellerIdByProdIdx(prod_idx);
	}

}
