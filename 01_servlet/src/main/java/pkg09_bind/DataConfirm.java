package pkg09_bind;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DataConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			

    // ServletContext 데이터 확인
    System.out.println(request.getServletContext().getAttribute("a"));
    
    // HttpSession 데이터 확인
    System.out.println(request.getSession().getAttribute("b"));
    
    // HttpServletRequest 확인
    System.out.println(request.getAttribute("c"));
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
