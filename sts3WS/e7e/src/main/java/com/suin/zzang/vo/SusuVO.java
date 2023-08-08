package com.suin.zzang.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 시작 때만 테이블과 1:1로 만들고, 추후 필요에 의해 확장!
 */
@Getter
@Setter
@ToString
public class SusuVO {
	private String title;
	private String scont;
	private String sname;
}
