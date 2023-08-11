package kr.or.ddit.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.service.ItemService;
import kr.or.ddit.vo.Item3VO;
import kr.or.ddit.vo.ItemVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("item")
@Controller
public class ItemController {
	
	@Autowired
	ItemService ItemService;
	
	@GetMapping("itemRegist")
	public String itemRegist() {
		
		return "item/itemRegist";
	}
	
	/*
	요청URI: /item/registPost
	요청파라미터: {itemName=태블릿&price=12000&description=어쩌구&pictures=파일객체 }
	요청방식: POST
	*/
	@ResponseBody
	@PostMapping("registPost")
	public String registPost(ItemVO itemVO) {
		log.info("itemVO : {}", itemVO);
		
		int result = this.ItemService.itemRegist(itemVO);
		log.info("registPost->result : {}", result);
		
		return "SUCCESS";
	}
	
	@GetMapping("itemMultiRegist")
	public String itemMultiRegist() {
		
		return "item/itemMultiRegist";
	}
	
	/*
	요청URI: /item/registMultiPost
	요청파라미터: {itemName=태블릿&price=12000&description=어쩌구&pictures=파일객체 }
	요청방식: POST
	*/
	@ResponseBody
	@PostMapping("registerMultiPost")
	public String registerMultiPost(Item3VO item3VO) {
		log.info("item3VO : {}", item3VO);
		
		int result = this.ItemService.registMultiPost(item3VO);
		log.info("registerMultiPost -> result : {}", result);
		
		return "SUCCESS";
		
	}
	
}











