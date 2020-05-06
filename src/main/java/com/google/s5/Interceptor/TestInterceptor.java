package com.google.s5.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter {
//HandlerInterceptorAdapter는 추상메서드가 아님
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("Controller 진입 전");
		System.out.println("return이 true면 controller로 이동");
		System.out.println("return이 flase면 controller로 이동");
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
		System.out.println("Controller 나올 때");
		//매개변수로 request, response, modelAndView를 받고 있기 때문에 값을 실험해 볼 수 있다.
	
		
	}
	
	
	@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
				throws Exception {
			
		System.out.println("JSP 렌더링이 끝난 후");
		}
	
	
	
}
