package com.google.s5.transfer;

import org.springframework.stereotype.Component;

@Component
public class Transfer {

	public void bus(Integer money){
		System.out.println("=============================");
		System.out.println("버스타기");
		System.out.println("핸드폰 보기");
		System.out.println("=============================");
		
	}
	public void subway(Integer money,String line){
		System.out.println("=============================");
		System.out.println("지하철타기");
		System.out.println("신문 보기");
		System.out.println("=============================");
	}
	public void taxi() {
		System.out.println("택시 타기");
	}
	
	
	
}//end class
