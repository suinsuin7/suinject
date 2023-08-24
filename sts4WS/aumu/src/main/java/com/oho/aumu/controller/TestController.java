package com.oho.aumu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/suin")
public class TestController {

	@GetMapping("/merong")
	public String getSuin(Model model) {
		
		List<String> myList = new ArrayList<>();
		for (int i = 1; i <=5; i++) {
			myList.add("배고파 돈가스 빨리 먹고 싶어" + i);
		}
		
		model.addAttribute("hungryList", myList);
		return "zzang";
	}
}
