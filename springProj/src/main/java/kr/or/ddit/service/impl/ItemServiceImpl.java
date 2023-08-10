package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.mapper.ItemMapper;
import kr.or.ddit.service.ItemService;
import kr.or.ddit.util.FileUploadUtils;
import kr.or.ddit.vo.Item3VO;
import kr.or.ddit.vo.ItemAttachVO;
import kr.or.ddit.vo.ItemVO;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemMapper itemMapper;
	
	//아이템 등록
	@Override
	public int itemRegist(ItemVO itemVO) {
		
		MultipartFile picture = itemVO.getPictures();
		
		String pictureUrl = FileUploadUtils.singleUpload(picture);
		
		itemVO.setPictureUrl(pictureUrl);
		
		int result =  this.itemMapper.itemRegist(itemVO);
		
		return result;
	}
	
	//아이템 등록 + 다중파일 등록
	@Override
	public int registMultiPost(Item3VO item3VO) {
		//1) item3VO ITEM3 테이블에 인서트
		int result = this.itemMapper.registMultiPost(item3VO);
		log.info("registMultiPost->result : " + result);
		
		MultipartFile[] pictures = item3VO.getPictures();
		
		//파일업로드
		List<ItemAttachVO> itemAttachVOList
			= FileUploadUtils.multiUpload(pictures);
		
		//ITEM_ATTACH테이블을 위한 itemId=0을 ITEM3테이블에 insert된 itemId값으로 보정
		for (ItemAttachVO vo : itemAttachVOList) {
			vo.setItemId(item3VO.getItemId());
		}
		
		item3VO.setItemAttachVOList(itemAttachVOList);
		
		//2) ItemAttach(fullname, itemId, regdate)->ITEM_ATTACH 테이블에 인서트
		result += this.itemMapper.registMultiAttach(itemAttachVOList);
		
		return result;
	}
	
}
