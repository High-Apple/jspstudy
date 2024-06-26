package pkg07_Cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class SaveCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // 요청 및 요청 파라미터
	  request.setCharacterEncoding("UTF-8");
	  String name = request.getParameter("name");
	  String email = request.getParameter("email");
	  
	  // 쿠기 만들기(쿠기 이름, 유지 시간, 저장 경로) 쿠키는 각 클라이언트의 개인pc에 저장됨
	  
	  // 쿠키 이름과 값
    Cookie cookie1 = new Cookie("name", URLEncoder.encode(name, "UTF-8"));     // 쿠키 값은 String 타입이다. 공백 등 쿠키 값으로 사용할 수 없는 문자가 있어서 인코딩 후 저장한다.(쿠키값만!)
    Cookie cookie2 = new Cookie("email", URLEncoder.encode(email, "UTF-8"));
	  
	  // 쿠키 유지 시간
    cookie1.setMaxAge(60 * 60 * 24 * 15); // 15일간 유지되는 cookie1
    //cookie2.setMaxAge(60);               // 생략하면 세션 쿠키가 된다. (브라우저를 닫으면 지워지는 쿠키)(세션과 라이프사이클이 같은 쿠키)
	  
    // 쿠키 저장(응답)
    response.addCookie(cookie1);
    response.addCookie(cookie2);
	  
	  // ReadCookie 서블릿으로 이동하기
	  response.sendRedirect(request.getContextPath() + "/readCookie");
	  
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
