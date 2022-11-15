package com.mycoffee.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

/**
 * Spring framework용 파일 업로더
 * @author wj.jeong
 *
 */
@Log4j
public class FileUploader {

	private File uploadPath;
	private String realFileName;
	
	/**
	 * 파일 저장 경로를 지정하는 생성자
	 * @param uploadPath 파일 저장 경로
	 */
	public FileUploader(String uploadPath) {
		this.uploadPath = new File(uploadPath);
	}
	
	/**
	 * 생성자에 지정한 폴더에 이미지 파일을 저장<br>
	 * 이미지 파일은 추가로 썸네일 생성
	 * @param multipartFile
	 * @return 파일 사이즈가 0이거나 저장에 실패할 경우 false 반환
	 */
	public boolean saveFile(MultipartFile multipartFile) {
		System.out.println("uploaded file name: " + multipartFile.getOriginalFilename());
		System.out.println("uploaded file size: " + multipartFile.getSize());
		// 첨부 파일이 없으면 종료
		if (multipartFile.getSize() == 0) {
			return false;
		}
		
		// 폴더가 없을 경우 생성
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		UUID uuid = UUID.randomUUID();
		realFileName = multipartFile.getOriginalFilename();
		realFileName = uuid.toString() + "_" + realFileName;
		File saveFile = new File(uploadPath, realFileName);
		log.info(saveFile.getAbsolutePath());
		
		// 파일 저장
		try {
			multipartFile.transferTo(saveFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return false;
		}
		
		// Thumbnail 생성/저장
		if (checkImageType(saveFile)) {
			try (FileOutputStream thumbnail
					= new FileOutputStream(new File(uploadPath, "s_" + realFileName))){
				Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/*
	 * 서버에 저장되는 실제 파일명
	 */
	public String getRealFileName() {
		return realFileName;
	}
	
	/**
	 * 파일삭제<br>
	 * 권고: 업로드전에 수동으로 기존 파일을 수동으로 삭제
	 * @param filename
	 * @return
	 */
	public boolean deleteOldFile(String filename) {
		File target = new File(uploadPath, filename);
		if(target.exists()) {
			return target.delete();
		}
		return false;
	}
	
	/**
	 * 이미지파일 판정
	 * @param file
	 * @return
	 */
	private boolean checkImageType(File file) {
		String contentType = "";
		try {
			contentType = Files.probeContentType(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentType.startsWith("image");
	}

}
