package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookInfoService;
import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.vo.BookInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookInfoController {
	//DI / IoC
	@Autowired
	BookInfoService bookinfoService;
	
	//요청URI : /bookInfo/addBook
	@RequestMapping(value = "/bookInfo/addBook", method = RequestMethod.GET)
	public ModelAndView addBook(ModelAndView mav) {
		//forwarding
		//tiles-config.xml에서 <definition name="			*/  *"
		//										bookInfo/addBook
		//						/WEB-INF/views/{1}		/{2}	.jsp
		mav.setViewName("bookInfo/addBook");
		
		return mav;
	}
	
	/**
	 요청URL : /bookInfo/addBookPost
	요청파라미터 : {bookId=ISBN1234,name=...}
	요청방식 : post 
	 */
	@ResponseBody
	@RequestMapping(value = "/bookInfo/addBookPost", method = RequestMethod.POST)
	public int addBookPost(ModelAndView mav, 
			@ModelAttribute BookInfoVO bookInfoVO) {
		log.info("bookInfoVO : " + bookInfoVO);
		
		int result = bookinfoService.addBookPost(bookInfoVO);
		log.info("addBookPost->result : " + result);
		
		//forwarding
		//mav.setViewName("bookInfo/detailBook");
		
		//return mav;
		return result;
	}
	
	/** 도서코드 자동생성
	 *  요청url:"/bookInfo/getBookId",
		요청방식:"post"
	 */
	@ResponseBody
	@RequestMapping(value = "/bookInfo/getBookId", method = RequestMethod.POST)
	public String getBookid() {
		//도서코드 자동생성
		String bookId = this.bookinfoService.getBookId();
		
		log.info("bookId : " + bookId);
		
		return bookId;
	}
	
	//도서 목록
	//요청URI : /bookInfo/listBook?currentPage=3&size=10
	//요청URI : /bookInfo/listBook
	//요청URI : /bookInfo/listBook?currentPage=3
	//요청URI : /bookInfo/listBook?size=10
	//vo : 골뱅이ModelAttribute
	//map, String..: 골뱅이 RequestParam
	/* 검색 
	요청URI : /bookInfo/listBook?size=10&keyword=개똥이
	요청파라미터(HTTP파라미터=QueryString) : size=10&keyword=개똥이
	요청방식 : get
	*/
	@RequestMapping(value = "/bookInfo/listBook", method = RequestMethod.GET)
	public ModelAndView listBook(ModelAndView mav,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage); // 기본 : 1
		map.put("size", size); // 기본10
		map.put("keyword", keyword); //기본 ""
		
		log.info("listBook->map : " + map);
		//listBook->map : {size=25, currentPage=1, keyword=수인}
		
		List<BookInfoVO> data = this.bookinfoService.listBook(map);
		
		log.info("data : " + data);
		
		int total = this.bookinfoService.getBookInfoTotal(map);
		
		log.info("total={}", total);
		
		//Model
		//페이징 처리한 data : ArticlePage활용
		mav.addObject("data", new ArticlePage<BookInfoVO>(total, currentPage, size, data));
		
		//forwarding
		mav.setViewName("bookInfo/listBook");

		return mav;
	}
	
	
	/*
	 * <!-- controller에서 요청URI를 받아서 요청파라미터 정보를 log.info로 출력해보자. forwarding은
	 * bookInfo/detailBook.jsp --> <!-- <a
	 * href="/bookInfo/detailBook?bookId=${bookInfoVO.bookId}">${bookInfoVO.name}</
	 * a> -->
	 */
	@RequestMapping(value = "/bookInfo/detailBook", method = RequestMethod.GET)
	public ModelAndView detailBook(ModelAndView mav, 
			String bookId) {
		
		log.info("bookId : " + bookId);
		
		//SELECT문 실행 결과를 처리해보자
		BookInfoVO bookInfoVO = this.bookinfoService.detailBook(bookId);
		log.info("bookInfoVO : " + bookInfoVO);
		
		mav.addObject("data", bookInfoVO);
		
		//forwarding
		mav.setViewName("bookInfo/detailBook");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/bookInfo/updatePost", method = RequestMethod.POST)
	public ModelAndView updateBookPost(ModelAndView mav, BookInfoVO bookInfoVO) {
		log.info("" + bookInfoVO);
		
		int result = this.bookinfoService.updateBookPost(bookInfoVO);
		log.info("updateBookPost->result : " + result);
		
		//redirect
		mav.setViewName("redirect:/bookInfo/detailBook?bookId="+bookInfoVO.getBookId());
		
		return mav;
		
	}
	
	//요청URL : bookInfo/deleteBookPost
	//요청파라미터 : {bookId=,name=}
	//요청방식 : POST
	//BookInfoController에서 해당 URI를 받아서 
	//log.info("bookId : " + bookInfoVO.getBookId());
	//도서 목록으로 redirect
	@RequestMapping(value = "bookInfo/deleteBookPost", method = RequestMethod.POST)
	public ModelAndView deleteBookPost(ModelAndView mav, BookInfoVO bookInfoVO) {
		log.info("bookId 삭제 : " + bookInfoVO.getBookId());
		
		int result = this.bookinfoService.deleteBookPost(bookInfoVO);
		log.info("deleteBookPost->result : " + result);
		
		//redirect
		mav.setViewName("redirect:/bookInfo/listBook");
		
		return mav;
	}
}



