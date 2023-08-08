package com.suin.zzang.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString	// 디버깅용, 속성값 한개씩 찍으려면 너무 피곤하다
public class TestVO {
	private String name;
	private String age;
	private String alias;
}
