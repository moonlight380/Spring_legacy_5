package com.google.s5.Interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.s5.member.MemberVO;
@Component
public class MemberInterseptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean check=false;
		//MemberVO memberVO=(MemberVO)request.getSession().getAttribute("member"); //데이터를 꺼내올 것이 아니면 오브젝트로 받아도 됨
		Object obj=request.getSession().getAttribute("member");
		if(obj !=null ) {
			check= true;
			System.out.println("멤버임");
		}else{
			System.out.println("멤버가 아님");
			
			request.setAttribute("result", "회원가입을 해주세요.");
			request.setAttribute("path", "../member/memberJoin");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp"); //인터널리절뷰로 바로 가지 못함// 따라서 루트부터  경로 다 써줘야 함
			view.forward(request, response);
		}
		return check;
	}

}
