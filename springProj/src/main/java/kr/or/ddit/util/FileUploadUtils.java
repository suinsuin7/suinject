package kr.or.ddit.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.ItemAttachVO;

import java.nio.file.Files;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
public class FileUploadUtils {
	//1) 업로드 폴더 정보
	public static String uploadFolder = "E:\\eGovFrame3.10.0\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
	
	//2) 연월일 폴더 생성
	public static String getFolder() {
		//간단날짜형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
		Date date = new Date();
		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}
	
	//3) 이미지인지 판단
	public static boolean checkImageType(File file) {
        String contentType = null;
        try {
            contentType = Files.probeContentType(file.toPath());

            log.info("contentType={}", contentType);

            return contentType.startsWith("image"); //MIME 타입 정보가 image로 시작하는지 true/false로 리턴
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
	//4) 다중업로드 처리
	public static List<ItemAttachVO> multiUpload(MultipartFile[] pictures) {
		//upload까지의 경로, 년월일
		File uploadPath = new File(FileUploadUtils.uploadFolder, FileUploadUtils.getFolder());
		log.info("uploadPath : {}", uploadPath);
		//만약 연/월/일 해당 폴더가 없으면 생성
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
		
		List<ItemAttachVO> itemAttachVOList
					= new ArrayList<ItemAttachVO>();
		
		//파일객체 배열로부터 파일을 하나씩 꺼내옴
		//파일 웹경로
		String result="";
		for(MultipartFile picture : pictures) {
			ItemAttachVO itemAttachVO = new ItemAttachVO();
			
			log.info("----------------------");
			log.info("파일명 : {}", picture.getOriginalFilename());
			log.info("파일크기 : {}", picture.getSize());
			log.info("MINE타입 : {}", picture.getContentType());
			
			String uploadFileName = picture.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);

			//--------같은 날 같은 이미지를 업로드 시 파일 중복 방지
			UUID uuid = UUID.randomUUID(); // 임의의 값을 생성
			
			//원래 파일 이름과 구분하기 위해 _
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			//--------같은 날 같은 이미지를 업로드 시 파일 중복 방지 끝
			
			//File 객체 설계(복사할 대상 경로, 파일명)
			//...upload\\2023\\08\\08\\
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				//원본파일객체.transferTo(설계)
				//파일 복사가 일어남
				picture.transferTo(saveFile);
				
				result = "\\" + FileUploadUtils.getFolder() + "\\" + uploadFileName;
				result = result.replace("\\", "/");
				
				itemAttachVO.setFullname(result);
				
				//--------썸네일 처리 시작--------
				//이미지인지 체킹
				if(checkImageType(saveFile)) {
					//설계
					FileOutputStream thumbnail = new FileOutputStream(
							new File(uploadPath, "s_" + uploadFileName)
							);
					//썸네일 생성(원본,설계,가로크기,세로크기)
					Thumbnailator.createThumbnail(picture.getInputStream(),thumbnail,100,100);
					thumbnail.close();
				}
				
				//--------썸네일 처리 끝----------
			
				itemAttachVOList.add(itemAttachVO);
			}catch (IllegalStateException e) {
				log.error(e.getMessage());
				return null;
			}catch (IOException e) {
				log.error(e.getMessage());
				return null;
			}
			
		}//end for
		
		return itemAttachVOList;
	}
	
	//5) 단일업로드 처리
	public static String singleUpload(MultipartFile picture) {
		//upload까지의 경로, 년월일
		File uploadPath = new File(FileUploadUtils.uploadFolder, FileUploadUtils.getFolder());
		log.info("uploadPath : {}", uploadPath);
		//만약 연/월/일 해당 폴더가 없으면 생성
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
		
			log.info("----------------------");
			log.info("파일명 : {}", picture.getOriginalFilename());
			log.info("파일크기 : {}", picture.getSize());
			log.info("MINE타입 : {}", picture.getContentType());
			
			String uploadFileName = picture.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			
			//--------같은 날 같은 이미지를 업로드 시 파일 중복 방지
			UUID uuid = UUID.randomUUID(); // 임의의 값을 생성
			
			//원래 파일 이름과 구분하기 위해 _
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			//--------같은 날 같은 이미지를 업로드 시 파일 중복 방지 끝
			
			//File 객체 설계(복사할 대상 경로, 파일명)
			//...upload\\2023\\08\\08\\
			File saveFile = new File(uploadPath, uploadFileName);
			//파일 웹경로
			String result="";
			try {
				//원본파일객체.transferTo(설계)
				//파일 복사가 일어남
				picture.transferTo(saveFile);
				
				result = "\\" + FileUploadUtils.getFolder() + "\\" + uploadFileName;
				result = result.replace("\\", "/");
				
				//--------썸네일 처리 시작--------
				//이미지인지 체킹
				if(checkImageType(saveFile)) {
					//설계
					FileOutputStream thumbnail = new FileOutputStream(
							new File(uploadPath, "s_" + uploadFileName)
							);
					//썸네일 생성(원본,설계,가로크기,세로크기)
					Thumbnailator.createThumbnail(picture.getInputStream(),thumbnail,100,100);
					thumbnail.close();
				}
				
				//--------썸네일 처리 끝----------
				
			}catch (IllegalStateException e) {
				log.error(e.getMessage());
				return "0";
			}catch (IOException e) {
				log.error(e.getMessage());
				return "0";
			}
		
		return result;
	}
	
	public static String getFileUrl(MultipartFile picture) {
		
		String uploadFileName = picture.getOriginalFilename();
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
		
		//--------같은 날 같은 이미지를 업로드 시 파일 중복 방지
		UUID uuid = UUID.randomUUID(); // 임의의 값을 생성
		
		//원래 파일 이름과 구분하기 위해 _
		uploadFileName = uuid.toString() + "_" + uploadFileName;
		
		String result = "\\" + FileUploadUtils.getFolder() + "\\" + uploadFileName;
		result = result.replace("\\", "/");
		
		return result;
	}

}
