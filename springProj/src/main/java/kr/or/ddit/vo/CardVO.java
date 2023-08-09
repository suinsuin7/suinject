package kr.or.ddit.vo;


import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class CardVO {
	//문자열이 null이 아니고 trim(공백제거)한 길이가 0보다 커야 함
	@NotBlank(message = "카드번호를 입력하세요")
	private String no;
	//유효연월 : 미래 날짜
	@Pattern(regexp="^[0-9]{6}$", message = "연도 4자리와 월 2자리로 입력해 주세요")
	private String validMonth;
}
