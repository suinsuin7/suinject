package com.suin.merong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.suin.merong.vo.SuinVO;

@Mapper
public interface SuinMapper {
	  // Get List
	  List<SuinVO> listSuin();
	  // Get One
	  SuinVO getSuin(SuinVO suinVO);
	  // insert
	  int  insertSuin(SuinVO suinVO);
	  // update
	  int  updateSuin(SuinVO suinVO);
	  // delete
	  int  deleteSuin(SuinVO suinVO);
}
