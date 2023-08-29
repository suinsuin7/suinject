package com.suin.merong.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SuinVO {
	private int suinNum;
	private String suinName;
	private String suinContent;
	private String suinFile;
	private MultipartFile suinFile2;
}
