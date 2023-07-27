package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.vo.BookInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookInfoController {
	
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
	@RequestMapping(value = "/bookInfo/addBookPost", method = RequestMethod.POST)
	public ModelAndView addBookPost(ModelAndView mav, 
			@ModelAttribute BookInfoVO bookInfoVO) {
		log.info("bookInfoVO : " + bookInfoVO);
		
		//forwarding
		mav.setViewName("bookInfo/detailBook");
		
		return mav;
	}
}
