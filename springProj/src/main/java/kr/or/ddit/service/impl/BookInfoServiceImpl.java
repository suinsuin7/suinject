package kr.or.ddit.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.dao.BookInfoDAO;
import kr.or.ddit.service.BookInfoService;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookInfoVO;
import lombok.extern.slf4j.Slf4j;


//서비스 클래스 : 비즈니스 로직
//스프링 MVC 구조에서 Controller와 DAO를 연결하는 역할
@Slf4j
@Service
public class BookInfoServiceImpl implements BookInfoService {
	//데이터베이스 접근을 위해 BookInfoDao 인스턴스를 주입받자
	//DI / IoC
	@Autowired
	BookInfoDAO bookInfoDAO;
	
	// 1. 도서 정보 등록
	@Override
	public int addBookPost(BookInfoVO bookInfoVO) {
		
		//도서 정보 등록
		int result = bookInfoDAO.addBookPost(bookInfoVO); // 부모
		
		//첨부파일 등록 bookId, filename 필요함
		AttachVO attachVO = new AttachVO();
		
		attachVO.setBookId(bookInfoVO.getBookId()); //참조무결성
		
		///////////// 첨부파일 처리 시작 //////////////////////
		String uploadFolder = 
			"E:\\eGovFrame3.10.0\\workspace\\springProj\\src\\main\\webapp\\resources\\images";
		
		//연/월/일 폴더 생성
		//2023-07-28 형식(format) 지정
		//간단한 날짜 형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//날짜 객체 생성(java.util 패키지)
		Date date = new Date();
		//2023-07-28 => ..s\\images\\2023\\07\\28
		String str = sdf.format(date);
		File uploadPath = new File(uploadFolder, str.replace("-", File.separator));
		log.info("uploadPath : " + uploadPath);
		
		//만약 연/월/일 해당 폴더가 없으면 생성
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
		//파일객체(업로드 대상)
		MultipartFile multipartFile = bookInfoVO.getBookImage();
		
		log.info("파일명 : " + multipartFile.getOriginalFilename());
		log.info("파일 크기 : " + multipartFile.getSize());
		log.info("MIME타입 : " + multipartFile.getContentType());
		//원래 파일명. 개똥이.jpg
		String uploadFileName = multipartFile.getOriginalFilename();
		
		//같은 날 같은 이미지를 업로드 시 파일 중복되는 것을 방지해보자
		//java.util.UUID => 랜덤값 생성
		UUID uuid = UUID.randomUUID();
		//원래의 파일 이름과 구분하기 위해서 _를 붙임(ASFGDADS_개똥이.jpg)
		uploadFileName = uuid.toString() + "_" + uploadFileName;
		
		//File객체 복사 설계(복사할 대상 경로, 파일명)
		File saveFile = new File(uploadPath, uploadFileName);
		
		//설계대로 파일 복사 실행
		try {
			multipartFile.transferTo(saveFile);
			//str : ...2023-07-28
			//최종 : /2023/07/28/ASFGDADS_개똥이.jpg 이 세팅됨
			attachVO.setFilename("/" 
			+ str.replace("-", File.separator).replace("\\", "/") 
			+ "/" +  uploadFileName);
			
			result += this.bookInfoDAO.addAttach(attachVO); //자식
			
			log.info("최종result : " + result);
			
			return result;
		} catch (IllegalStateException | IOException e) {
			log.error(e.getMessage());
			return 0;
		}
		///////////// 첨부파일 처리 끝 //////////////////////
	}
	
	//<!-- 도서코드 자동 생성 -->
	//<select id="getBookId" resultType="String">
	@Override
	public String getBookId() {
			return this.bookInfoDAO.getBookId();
	}
	
	
	//도서목록
	@Override
	public List<BookInfoVO> listBook(){
		return this.bookInfoDAO.listBook();
	}
	
}