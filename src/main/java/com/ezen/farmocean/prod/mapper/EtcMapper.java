package com.ezen.farmocean.prod.mapper;

import org.apache.ibatis.annotations.Param;

public interface EtcMapper {

	public String getMemberImageById(@Param("member_id") String member_id);

	public String getMemberIdByNickname(@Param("member_nickname") String member_nickname);
	
}
