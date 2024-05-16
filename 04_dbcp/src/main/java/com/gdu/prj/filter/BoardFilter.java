package com.gdu.prj.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class BoardFilter extends HttpFilter implements Filter {
       

	public void doFilter(jakarta.servlet.ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	  
	  //HttpServletRequest / HttpServletResponse
	  HttpServletRequest req = (HttpServletRequest) request;
	  HttpServletResponse res = (HttpServletResponse) response;
	  
	  //요청 UTF-8 인코딩
	  req.setCharacterEncoding("UTF-8");
	  
	  //요청 방식 확인 + 요청 주소 확인
	  System.out.print(String.format("%-4s", req.getMethod()));
	  System.out.print(" | ");
	  System.out.print(req.getRequestURI());
	  
	  //요청 파라미터 확인
	  Map<String, String[]> params = request.getParameterMap();
	  for(Entry<String, String[]> param : params.entrySet()) {
	    System.out.println(String.format("%7s", ""));
	    System.out.print(String.format("%-10s", param.getKey())+":");
	    System.out.println(Arrays.toString(param.getValue()));
	  }
	  
		chain.doFilter(request, response);
	}


}
