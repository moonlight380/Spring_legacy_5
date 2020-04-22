package com.google.s5.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	//1. 폴더 생성
	//2.저장할 파일명 생성
	//3.파일 HDD 저장
	

	//파일명 생성해주는 메서드 3가지 방법

	//1.FileCopyUtils 클래스 사용
	public String saveByUtils(MultipartFile file,String path) throws Exception{
		//1.path 경로로된 폴더가 있는지 없는지 확인
		File f = new File(path);
		//System.out.println(f.exists()); 
		//존재하지 않다면 해당정보로된 폴더로 만들어줘
		if(!f.exists()){
			//resource/memberUpload
			//resource/upload/member
			//f.mkdirs(); //부모폴더인 upload 까지 만들고 싶을 때 mkdirs
			f.mkdirs();
			}
		
		//a.
		//long l = this.makeNameByTime(file.getOriginalFilename());
		//System.out.println(1);
		
		//aa.저장할 파일명 생성
		String fileName=this.makeNameByUUID(file.getOriginalFilename());
		
		f= new File(f, fileName);
		//b. HDD에 저장
		FileCopyUtils.copy(file.getBytes(), f);
		
		return fileName;
	}
	//2.MultipartFile
	public String saveByTransfer(MultipartFile file, String path) throws Exception{
		
		File f = new File(path);
		
		if(!f.exists()){	
			f.mkdirs();}
		String fileName = this.makeNameByUUID(file.getOriginalFilename());
		f = new File(f, fileName);
		file.transferTo(f);
		return fileName;  //이네임을 가지고 디비에 저장
		
	}
	
	
	//3.OutputStream
	public String saveByStream(MultipartFile file, String path) throws Exception {
		File f = new File(path);
		
		if(!f.exists()){	
			f.mkdirs();}
		String fileName = this.makeNameByUUID(file.getOriginalFilename());
		f = new File(f,fileName);
		
		//저장하는 형식을 자바에서 이용하는 string 객체 
		FileOutputStream fs = new FileOutputStream(f);
		fs.write(file.getBytes());
		fs.close();
		
		return fileName;
		
		
		
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////
	
	//aa.
	private String makeNameByUUID(String name) {
		
		//static 메서드가 있음
		String result=UUID.randomUUID().toString();
		result=result+"_"+name;
		return result;
	}
	//aa.
	private String makeNameByTime(String name) {
		Calendar ca = Calendar.getInstance();
		Long l=ca.getTimeInMillis();
		System.out.println(name);
		String result= name.substring(0, name.indexOf("."));
		result = result+"_"+l;
		result= result+name.substring(name.lastIndexOf("."));
		System.out.println(result);
		
		
		
		return result;
	}
////////////////////////////////////////////////////////////////////////	
	//file Delete
	public int deleteFile(String fileName, String path) throws Exception{
		File file = new File(path, fileName);
		boolean check = false;
		int result=0;
		if(file.exists()) {
			check=file.delete();
		}
		
		if(check) {
			result=1;
		}
		return result;
	}
	
}
