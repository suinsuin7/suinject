package com.suin.zzang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suin.zzang.vo.SusuVO;

//@Service, 인터페이스는 new로 생성할 수 없음
//요기에 붙이면 여러사람 오류 남
public interface Susu {
	// 읽기 리스트(여러 줄)
	public List<SusuVO> listData();
	
	// 읽기 한줄
	public SusuVO oneData(SusuVO susuVO);
	
	// 입력
	public int insertData(SusuVO susuVO);
	
	// 수정
	public int updateData(SusuVO susuVO);
	
	// 삭제
	public int deleteData(SusuVO susuVO);
}
