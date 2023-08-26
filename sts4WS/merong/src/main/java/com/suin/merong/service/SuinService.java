package com.suin.merong.service;

import java.util.List;

import com.suin.merong.vo.SuinVO;

public interface SuinService {
	// 읽기 메소드
	List<SuinVO> listSuin();
	SuinVO getSuin(SuinVO suinVO);
	
	//쓰기 메소드(mutation(변경/변형) 메소드)
	int insertSuin(SuinVO suinVO);
	int updateSuin(SuinVO suinVO);
	int deleteSuin(SuinVO suinVO);
}
