package com.google.s5.transfer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Card {
	@AfterReturning("execution(* com.google.s5.transfer.Transfer.*())")
	public void cardAfter() {
		System.out.println("------------");
		System.out.println("카드 결제");
	}
	
	@Around("execution(* com.google.s5.transfer.Transfer.*(Integer,..))")
	public Object cardCheck(ProceedingJoinPoint join)throws Throwable {
		System.out.println("--------------------------");
		System.out.println("카드 check in");
		Object[] ar= join.getArgs();
		for(Object o:ar) {
			System.out.println(o);
		}
		Object obj =join.proceed();
		
		System.out.println("카드 check out");
		
		return obj;
	}
	
	
}//end class
