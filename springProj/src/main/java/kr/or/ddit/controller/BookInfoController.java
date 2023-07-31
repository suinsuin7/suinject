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
	@RequestMapping(value = "/bookInfo/listBook", method = RequestMethod.GET)
	public ModelAndView listBook(ModelAndView mav,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage); // 기본 : 1
		map.put("size", size); // 기본10
		
		log.info("listBook->map : " + map);
		
		List<BookInfoVO> data = this.bookinfoService.listBook(map);
		
		log.info("data : " + data);
		
		int total = this.bookinfoService.getBookInfoTotal();
		
		log.info("total={}", total);
		
		//Model
		//페이징 처리한 data : ArticlePage활용
		mav.addObject("data", new ArticlePage<BookInfoVO>(total, currentPage, size, data));
		
		//forwarding
		mav.setViewName("bookInfo/listBook");

		return mav;
	}
}






