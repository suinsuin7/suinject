package kr.or.ddit.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookInfoVO;
import kr.or.ddit.vo.BookVO;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BookService bookService;
	/*
	 * 4. params 매핑
	 * - 요청 파라미터를 매핑 조건으로 지정하는 경우 params 속성을 사용
	 * <button, <a href~ 에 따라 호출할 메서드를 바꿔야 할 때 사용함
	 * */
	
    //void: return "board/register"; //forwarding
    @GetMapping("/register")
    public void registerForm() {
        log.info("registerForm");
    }

    @GetMapping("/modify")
    public void modyfyForm() {
        log.info("modifyForm");
    }

    //board테이블의 12번글 (/board/read/12)
    //boardNo: 경로 변수: path variable
    @GetMapping("/read/{no}")
    public String read(@PathVariable("no") int boardNo){
        log.info("no={}", boardNo);

        return "board/read";
    }

    @GetMapping("/update/{boardNo}")
    public String update(@PathVariable int boardNo) {
        return "board/update";
    }

    @PostMapping(value = "/post", params = "register")
    public String register(String bookId) {
        log.info("register");
        return "board/register";
    }

    @PostMapping(value = "/post", params = "update")
    public String updatePost(String bookId){
            log.info("update");
            return "board/register";
    }

    @PostMapping(value = "/post", params = "delete")
    public String deletePost(String bookId){
            log.info("delete");
            return "board/register";
    }

    @PostMapping(value = "/post", params = "list")
    public String listPost(String bookId){
            log.info("list");

            return "board/register";
    }
    
    @GetMapping(value = "/get", params = "remove")
    public String removeGet() {
    		log.info("remove");
    		
    		return "board/register";
    }
    
    @PostMapping(value = "/post", params = "remove")
    public String removePost() {
    	log.info("remove");
    	
    	return "board/register";
    }
    
    @GetMapping(value = "/get", params = "read")
    public String readGet() {
    		log.info("read");
    		
    		return "board/register";
    }
    
    
    /*
     5. Headers 매핑
     HttpEntity 클래스를 상속받아 구현한 클래스 : RequestEntity, ResponseEntity. 
     
	요청URI : /board/10
	PathVariable : boardNo
	요청방식 : post
	
	//도서상세정보(/detail?bookId=1)
	//요청URI : /board/9
	//pathVariable: boardNo -> bookId로 처리예정
	//요청방식 : get
	//data(응답타입) : JSON
    */
    @GetMapping(value = "/{bookId}")
    public ResponseEntity<BookVO> modifyPost(@PathVariable("bookId") int bookId,
    		BookVO bookVO) {
    	log.info("modifyPost");
    	
    	//bookVO{bookId=1,...}
    	bookVO.setBookId(bookId);
    	
    	bookVO = this.bookService.detail(bookVO);
    	
    	ResponseEntity<BookVO> entity 
    		= new ResponseEntity<BookVO>(bookVO, HttpStatus.OK);
    	
    	return entity;
    }

    
	//요청URI : /board/detail/ISBN1234
	//pathVariable : bookId
	//data : bookId, name, unitPrice, author, totalPages
	//제이슨 데이터를 받으려면 골뱅이RequestBody
    //요청방식 : post
	//응답데이터 : SUCCESS
    @PostMapping(value = "/detail/{bookId}")
    public ResponseEntity<String> detailJSONPost(@PathVariable("bookId") String bookId, 
    		@RequestBody BookInfoVO bookInfoVO) {
    	log.info("detailJSONPost");
    	log.info("bookId={}", bookId);
    	log.info("bookInfoVO={}", bookInfoVO);
    	
    	
    	ResponseEntity<String> entity
    		= new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    
    	return entity;
    }
    
    
    //요청URL : /board/book
  	//JSON 데이터 : {"title":"제목","category":"소설","price":"12000","content":"내용"}
    //요청방식 : post
    @PostMapping(value = "/book")
    public ResponseEntity<String> bookInsert(@RequestBody BookVO bookVO) {
    	log.info("bookVO={}", bookVO);
    	
    	int result = this.bookService.insert(bookVO);
    	log.info("bookInsert->result={}", result);
    	
    	ResponseEntity<String> entity
    		= new ResponseEntity<String>(bookVO.getBookId()+"", HttpStatus.OK);
    	
    	return entity;
    }
}
