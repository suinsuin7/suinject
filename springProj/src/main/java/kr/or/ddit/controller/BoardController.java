package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

//스프링이 이 클래스를 자바빈으로 등록하여 관리함
//클래스레벨 요청경로매핑
@RequestMapping("/board")
@Slf4j
@Controller
public class BoardController {
	/*
	 1. 요청 경로 매핑
	  - 요청 경로는 필수
	  - 속성이 하나일 때 속성명 생략 가능
	  - 클래스레벨 요청경로는 기본 경로로 취급
	  - 클래스레벨 요청경로(/board) + 메소드레벨 요청경로(/register) => 최종요청경로(/board/register) 
	*/
	/*
	method생략 : get방식
	리턴타입이 void : return "board/register";
	 				board폴더에 register.jsp를 forwarding
	*/
	//메소드레벨 요청경로매핑
	//value속성에 요청 경로를 설정
	@RequestMapping(value = "/register")
	public void registerForm() {
		log.info("registerForm");
	}
	
	//속성이 하나일 때는 속성명 생략 가능
	@RequestMapping("/modify")
	public void modifyForm() {
		log.info("modifyForm");
		
		// board 폴더의 modify.jsp가 forwarding
	}
	
	/* 2. 경로 패턴 매핑
	 	- 요청 경로를 동적으로 표현 가능
	 	- 경로 변수(path variable)에 해당하는 값을 파라미터 변수에 설정
	 */
	// /board/read/12 요청을 처리해보자
	// board 테이블의 12번글
	// boardNo : 경로(path) 변수(variable)
	//1) /board/read?boardNo=12
	//		=> 골뱅이RequestParam int boardNo
	//2) /board/read/12
	//		=> 골뱅이PathVariable("boardNo") int boardNo
	@GetMapping("/read/{boardNo}")
	public String readForm(@PathVariable int boardNo) {
		log.info("readForm-> boardNo : " + boardNo);
		
		//board 폴더의 read.jsp를 forwarding
		return "board/read";
	}
	
	// 요청URI : /board/update/27
	// 경로변수명 : boardNo
	// board폴더의 update.jsp를 forwarding 해보자
	@GetMapping("/update/{boardNo}")
	public String updateForm(@PathVariable int boardNo) {
		log.info("readForm-> boardNo : " + boardNo);
		
		return "board/update";
	}
	
	//요청URI : /board/get?register
	//요청방식 : get
	//요청파라미터 : register
	@RequestMapping(value="/get", method=RequestMethod.GET, params="register")
	public String register() {
		log.info("register");
		
		return "board/register";
	}
	
	//요청URI : /board/post?register
	//요청방식 : post
	//요청파라미터 : register
	@RequestMapping(value = "/post", method = RequestMethod.POST, params = "register")
	public String registerPost() {
		log.info("registerPost");
		
		return "board/register";
	}
}



