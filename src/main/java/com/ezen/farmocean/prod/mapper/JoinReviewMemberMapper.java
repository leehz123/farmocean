package com.ezen.farmocean.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.JoinReviewMember;

public interface JoinReviewMemberMapper {

	public List<JoinReviewMember> getReviewMemberListByProdIdx(@Param("prod_idx") Integer prod_idx);
}
