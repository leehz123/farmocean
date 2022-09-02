package com.ezen.farmocean.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EtcMapper {

	public String getMemberImageById(@Param("member_id") String member_id);
}
