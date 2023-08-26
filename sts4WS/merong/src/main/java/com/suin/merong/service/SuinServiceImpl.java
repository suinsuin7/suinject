package com.suin.merong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suin.merong.mapper.SuinMapper;
import com.suin.merong.vo.SuinVO;

@Service
public class SuinServiceImpl implements SuinService {

	@Autowired // 서비스는 매퍼를 불러야 함
	private SuinMapper suinMapper;
	
	@Override
	public List<SuinVO> listSuin() {
		return suinMapper.listSuin();
	}

	@Override
	public SuinVO getSuin(SuinVO suinVO) {
		return suinMapper.getSuin(suinVO);
	}

	@Override
	public int insertSuin(SuinVO suinVO) {
		return suinMapper.insertSuin(suinVO);
	}

	@Override
	public int updateSuin(SuinVO suinVO) {
		return suinMapper.updateSuin(suinVO);
	}

	@Override
	public int deleteSuin(SuinVO suinVO) {
		return suinMapper.deleteSuin(suinVO);
	}

}
