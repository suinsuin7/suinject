package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

//스프링이 자바빈으로 등록
@Slf4j
@RequestMapping("/member")
@Controller
public class MemberController {
	//모델에 폼 객체를 추가하지 않으면 오류 발생
	//뷰에 전달할 데이터를 위해 모델을 매개변수로 지정
	@GetMapping("/registerForm01")
	public String registerForm01(Model model) {
		//모델의 속성명에 memberVO를 지정하고 폼 객체를 모델에 추가함
		model.addAttribute("memberVO", new MemberVO());
		//forwarding
		return "member/registerForm01";
	}
	
	//모델의 속성명과 스프링 폼 태그의 modelAttribute 속성값이 일치해야 함
	@GetMapping("/registerForm02")
	public String registerForm02(Model model) {
		//모델의 속성명에 memberVO를 지정하고 폼 객체를 모델에 추가함
		//model.addAttribute("suin", new MemberVO()); 이건 오류!
		model.addAttribute("memberVO", new MemberVO());
		//forwarding
		return "member/registerForm02";
	}

	//컨트롤러 메서드의 매개변수로 자바빈즈 객체가 전달이 되면
	//forwarding 시 뷰(registerForm.jsp)로 memberVO를 전달함
	//컨트롤러는 자바빈즈 규칙에 맞는 객체를 뷰로 전달함
	@GetMapping("/registerForm05")
	public String registerForm05(MemberVO memberVO) {
		//폼 객체의 속성명은 직접 지정하지 않으면 : 골뱅이ModelAttribute("속성명")를 생략
		//폼 객체의 클래스명의 맨 처음 문자를
		//소문자로 변환하여 처리함
		//forwarding
		return "member/registerForm05";
	}
}
