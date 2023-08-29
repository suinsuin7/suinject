package com.suin.merong.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestVO {
	private String name;
	private int age;
	private List<String> friends;
	private MultipartFile susuFile;
}
