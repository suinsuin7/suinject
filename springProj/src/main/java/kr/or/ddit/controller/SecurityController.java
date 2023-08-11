package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SecurityController {
	//웹화면 접근 정책
	
	//요청URI : /freeboard/list
	//회원게시판의 목록
	//누구나 접근 가능
	@GetMapping("/freeboard/list")
	public String freeboardList() {
		//forwarding
		return "freeboard/list";
	}
	
	//요청URI : /freeboard/register
	//회원게시판의 등록
	//로그인한 회원만 접근 가능
	@GetMapping("/freeboard/register")
	public String freeboardRegister() {
		
		return "freeboard/register";
	}
	
	//요청URI : /notice/list
	//공지사항 게시판의 목록
	//누구나 접근 가능
	@GetMapping("/notice/list")
	public String noticeList() {
		
		return "notice/list";
	}
	
	//요청URI : /notice/register
	//공지사항 게시판의 등록
	//로그인한 관리자만 접근 가능
	@GetMapping("/notice/register")
	public String noticeregister() {
		
		return "notice/register";
	}

}



