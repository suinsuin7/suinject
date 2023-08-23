package com.suin.zzang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suin.zzang.mapper.SusuMapper;
import com.suin.zzang.vo.SusuVO;

@Service
public class SusuImpl implements Susu {
	
	// 서비스impl에서 매퍼 연결
	@Autowired
	private SusuMapper SusuMapper;
	
	@Override
	public List<SusuVO> listData() {
		return SusuMapper.listData();
	}

	@Override
	public SusuVO oneData(SusuVO susuVO) {
		return SusuMapper.oneData(susuVO);
	}

	@Override
	public int insertData(SusuVO susuVO) {
		return SusuMapper.insertData(susuVO);
	}

	@Override
	public int updateData(SusuVO susuVO) {
		return SusuMapper.updateData(susuVO);
	}

	@Override
	public int deleteData(SusuVO susuVO) {
		return SusuMapper.deleteData(susuVO);
	}

}
