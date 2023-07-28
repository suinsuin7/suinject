package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BookInfoVO;

public interface BookInfoService {
	//메소드 시그니처
	//도서 정보 등록(첨부파일도 함께 등록)
	public int addBookPost(BookInfoVO bookInfoVO);
	
	//도서코드 자동생성
	public String getBookId();

	//도서목록
	public List<BookInfoVO> listBook();

}
