package org.example;


import org.example.calculate.CalCulator;
import org.example.calculate.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);
	private ServletConfig config;

//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		log.info("init");
//		this.config = config;
//		
//	}//implements Servlet

//	@Override
//	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
//		log.info("service");
//		
//		//v1 . service 로직 실행
//		int a = Integer.parseInt(request.getParameter("a"));
//		String operator = request.getParameter("operator");
//		int b = Integer.parseInt(request.getParameter("b"));
//		int result = CalCulator.calculateInterface2(new PositiveNumber(a), operator, new PositiveNumber(b));
//		
//		PrintWriter writer = response.getWriter();
//		writer.println(result); // http://localhost:8080/servlet-pratice/calculate?a=10&operator=*&b=10 -> 결과 100 이 출력
//	}//extends GenericServlet

//	@Override
//	public void destroy() {
//		log.info("destroy");
//		// 자원 해제 
//		
//	}//implements Servlet
//
//	@Override
//	public ServletConfig getServletConfig() {
//		return this.config;
//	}//implements Servlet
//	
//	@Override
//	public String getServletInfo() {
//		// TODO Auto-generated method stub
//		return null;
//	}//implements Servlet
//	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//v 2 . service 로직 실행
		int a = Integer.parseInt(request.getParameter("a"));
		String operator = request.getParameter("operator");
		int b = Integer.parseInt(request.getParameter("b"));
		int result = CalCulator.calculateInterface2(new PositiveNumber(a), operator, new PositiveNumber(b));
		
		PrintWriter writer = response.getWriter();
		writer.println(result); 
		// http://localhost:8080/servlet-pratice/calculate?a=10&operator=*&b=10 -> 결과 100 이 출력
		//http://localhost:8080/servlet-pratice/calculate?a=10&operator=%2b&b=10 -> 결과 20이 출력
		// + 는 url 로 예약어를 의미하기 떄문에 인코딩이 필요하다.
	}//extends HttpServlet
	

}
