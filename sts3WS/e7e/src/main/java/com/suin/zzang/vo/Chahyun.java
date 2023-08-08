package com.suin.zzang.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Chahyun {
	private String goodWords;
	private String badWords;
	private MultipartFile suinFile;
}
