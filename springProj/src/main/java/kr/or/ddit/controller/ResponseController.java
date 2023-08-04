package kr.or.ddit.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;

//4장. 컨트롤러 응답
@RequestMapping("/resp")
@Slf4j
@Controller
public class ResponseController {
	
	@Inject
	BookService bookService;

	//1) void 타입
	//  호출하는 URL과 동일한 뷰 이름을 나타내기 위해 사용
	
	//요청URI : /resp/goHome0101
	@GetMapping(value = "/goHome0101")
	public void home0101() {
		log.info("home0101");
	}
	
	//요청URI : /resp/goHome0102
	@GetMapping(value = "goHome0102")
	public void home0102() {
		log.info("home0102");
	}
	
	//2. String 타입
	// 뷰 파일의 경로와 파일 이름을 나타내기 위해 사용
	
	//요청URL : /resp/goHome0201
	@GetMapping("/goHome0201")
	public String home0201() {
		log.info("home0201");
		
		//servlet-context.xml
		return "resp/goHome0201";
	}
	
	//요청URL : /resp/goHome0202
	@GetMapping("/goHome0202")
	public String home0202() {
		log.info("home0202");
		
		//servlet-context.xml
		return "resp/goHome0202";
	}
	
	//3) 반환값이 "redirect:"로 시작하면 리다이렉트 방식으로 처리
	@GetMapping("/goHome0301")
	public String home0301() {
		log.info("home0301");
		
		return "redirect:/resp/goHome0202";
	}
	
	//반환값이 "/"로 시작하면 웹 애플리케이션의 컨텍스트 경로에 영향을 받지 않는
	//절대경로를 의미함
	//요청URI : /resp/goHome0302
	@GetMapping("/goHome0302")
	public String home0302() {
		log.info("home0302");
		/*
		 tiles-config.xml의 별/별에 매핑이 안 되어 타일즈 적용이 안 됨
		*/
		return "/resp/goHome0302";
	}
	
	//요청URI : /resp/goHome0303
	//요청방식 : get
	//forwarding
	/*
	@GetMapping("/goHome0303")
	public String home0303() {
		log.info("home0303");
		
		return "resp/goHome0303";
	}
	*/
	@RequestMapping(value = "/goHome0303", method = RequestMethod.GET)
	public ModelAndView goHome0303(ModelAndView mav) {
		mav.setViewName("resp/goHome0303");
		
		return mav;
	}
	
	//3) 자바빈즈 클래스 타입
	// VO -> JSON으로 반환해보자
	
	//ResponseBody를 지정하지 않으면 HTTP404(jsp없음) 오류 발생
	//pom.xml에 의존관계라이브러리인 jackson-databind가 등록되어 있어야 하고
	// jackson-databind가 없으면 HTTP 406 오류가 발생.
	//ResponseBody를 지정해줘야 함
	@ResponseBody
	@GetMapping("/goHome030101")
	public AttachVO home030101() {
		log.info("home030101");
		
		AttachVO attachVO = new AttachVO();
		attachVO.setSeq(1);
		attachVO.setBookId("ISBN1234");
		attachVO.setFilename("수인이.jsp");
		
		return attachVO;
	}
	
	//요청URI : /resp/goHome030102?bookId=1
	//요청방식 : get
	@ResponseBody
	@GetMapping("/goHome030102")
	public BookVO home030102(@RequestParam(value = "bookId",required = false, defaultValue = "1") int bookId
			, BookVO bookVO) {
		log.info("bookId={}", bookId);
		
		bookVO.setBookId(bookId);
		
		bookVO = this.bookService.detail(bookVO);
		
		return bookVO;
	}

	//골뱅이RequestBody : json{"boardId":"1"} -> bookVO{"bookId":"1","title":....}
	//골뱅이ResponseBody : json{"boardId":"1"} <- bookVO{"bookId":"1","title":....}
	@ResponseBody
	@PostMapping("/goHome030103")
	public BookVO home030103(
			@RequestBody BookVO bookVO) {
		log.info("bookVO={}", bookVO);
		
		bookVO = this.bookService.detail(bookVO);
		
		return bookVO;
	}
	
	@ResponseBody
	@PostMapping("/goHome030105")
	public BookVO home030105(
			 @RequestBody BookVO bookVO) {
		log.info("bookVO(전)={}", bookVO);
		
		bookVO = this.bookService.detail(bookVO);
		
		log.info("bookVO(후)={}", bookVO);
		
		return bookVO;
	}
	
}


