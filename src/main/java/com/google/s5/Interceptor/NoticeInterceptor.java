package com.google.s5.Interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.s5.member.MemberVO;

@Component
public class NoticeInterceptor  extends HandlerInterceptorAdapter {
	//주소를 쳤을 때 form이 나오지 않도록 ->controller 들어가기 전
	
	//controller 들어가기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean check= false;
		MemberVO memberVO=(MemberVO)request.getSession().getAttribute("member");
		if(memberVO != null && memberVO.getId().equals("admin")){
			check=true;
			System.out.println("관리자 o");
		}else {
			System.out.println("관리자x");
			//response.sendRedirect("../member/memberLogin");
			//모델앤뷰를 쓰지 못함. 포워드방법
			request.setAttribute("result", "권한이 없습니다.");
			request.setAttribute("path", "../");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp"); //인터널리절뷰로 바로 가지 못함// 따라서 루트부터  경로 다 써줘야 함
			view.forward(request, response);
			
		}
		return check;
		
		
		//로그인 하지 않고 멤버에 멤버 페이지 주소를 치면 mypage로 가니까 로그인 하지 않으면 못들어가도록 막아줘야 함
		//인터셉터 이름은 MemberInterseptor로 하고 memberPage 요청시 로그인 유무 판단.
		//로그인 안되어 있으면 로그인 폼으로 이동
	}
	
	
	
	
}//end class
