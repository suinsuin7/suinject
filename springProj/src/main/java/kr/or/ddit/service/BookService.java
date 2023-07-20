package kr.or.ddit.service;

import kr.or.ddit.vo.BookVO;

//서비스 interface : 비즈니스 로직
public interface BookService {
	//메소드 시그니처
	//책 등록하기
	public int insert(BookVO bookVO);

	//책 상세보기
	public BookVO detail(BookVO bookVO);
	
}
