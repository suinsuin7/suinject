package com.suin.merong.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.suin.merong.service.SuinService;
import com.suin.merong.vo.SuinVO;
import com.suin.merong.vo.TestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SuinController {

	@Autowired
	private SuinService suinService;
	
	@GetMapping("/rest/suin")
	public List<SuinVO> getList() {
		return suinService.listSuin();
	}
	
	@GetMapping("/rest/suin/{num}")
	public SuinVO getSuin(@PathVariable int num) {
		SuinVO suinVO = new SuinVO();
		suinVO.setSuinNum(num);
		return suinService.getSuin(suinVO);
	}
	
	/*
	@PostMapping("/rest/suin")
	public String insSuin(@RequestBody SuinVO suinVO) {
		return Integer.toString(suinService.insertSuin(suinVO));
	}
	
	*/
	
	
	@PutMapping("/rest/suin")
	public String upSuin(@RequestBody SuinVO suinVO) {
		return Integer.toString(suinService.updateSuin(suinVO));
	}
	

	@DeleteMapping("/rest/suin/{num}")
	public String delSuin(@PathVariable int num) {
		SuinVO suinVO = new SuinVO();
		suinVO.setSuinNum(num);
		return Integer.toString(suinService.deleteSuin(suinVO));
	}

	@PostMapping("/rest/suin")
    public String insertsuin(SuinVO suinVO) throws Exception {
        // 디비에 저장할 파일경로
        String suinFile = "/kmerong/" + suinVO.getSuinFile2().getOriginalFilename();
        suinVO.setSuinFile(suinFile);
        log.debug("suinVO: " + suinVO);

        // 실제 저장될 물리적 경로
        String savePath = "E:/uploads/" + suinVO.getSuinFile2().getOriginalFilename();
        suinVO.getSuinFile2().transferTo(new File(savePath));
        return String.valueOf(suinService.insertSuin(suinVO));   }
	
	@PostMapping("/rest/suin2")
    public String insFile(MultipartFile fileTest) throws IOException { // formData.append("fileTest", $testFile2[0].files[0]);
        log.debug("filename: {}", fileTest.getOriginalFilename());
        log.debug("filename: {}", String.valueOf(fileTest.getSize()));
        String savePath = "E:/uploads/" + fileTest.getOriginalFilename();
        fileTest.transferTo(new File(savePath));

        String webURL = "/kmerong/" + fileTest.getOriginalFilename();

        return webURL;
    }
	
	//@RequestPart를 써야 하는 경우가 잘 생기지 않음
	// JSON 문자열이 일부 자바객체로 변환이 불완전하게 될 때 사용
	@PostMapping("/rest/susu")
	public String testSusu(@RequestPart ("test") TestVO test, 
							@RequestPart ("susuFile") MultipartFile mFile) {
		log.debug("check: {}", test);
		log.debug("file: {}", mFile);
		return "수수";
	}
		
}