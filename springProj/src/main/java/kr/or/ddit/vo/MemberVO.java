package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//자바빈 클래스
@Data
public class MemberVO {
	private String userId;
	private String userName;
	private String password;
	private int coin;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;
	private String gender;
	//국적
	private String nationality;
	//보유자동차
	private String[] cars;
	//집
	private ArrayList<String> homeList;
	//취미
	private String[] hobbys;
	//개발자 여부
	private String developer;
	//외국인 여부
	private boolean foreigner;
	//주소
	//중첩된 자바빈
	private AddressVO addressVO;
	//보유 카드들
	private List<CardVO> cardVOList;
	//자기소개
	private String introduction;
	
	//파일업로드
	private MultipartFile picture;
	
	//다중파일업로드
	private MultipartFile[] pictures;
}
