package com.suin.zzang.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper	// 명시적 표현 DAO에 해당
public interface Susu {
	
	public int insertDate();
}
