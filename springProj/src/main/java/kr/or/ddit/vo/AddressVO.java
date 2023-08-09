package kr.or.ddit.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

//자바빈 클래스
@Data
public class AddressVO {
	//문자열이 null이 아니고 trim(공백제거)한 길이가 0보다 커야 함
	@NotBlank(message = "우편번호를 입력해 주세요")
	private String zonecode; //우편번호
	@NotBlank(message = "주소를 입력해 주세요")
	private String address; //주소
	private String buildingName;
}
