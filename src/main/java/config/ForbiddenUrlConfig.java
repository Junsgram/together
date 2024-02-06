package config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ForbiddenUrlConfig implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		System.out.println("uri : " + req.getRequestURI());
		System.out.println("url : " + req.getRequestURL());
		//index.jsp만 허용
		if(req.getRequestURI().equals("/Greenbooks/index.jsp") 
				|| req.getRequestURI().equals("/Greenbooks/")) {
			chain.doFilter(req, response);
		}
		else {
			PrintWriter out = response.getWriter();
			out.print("잘못된 접근입니다.");
			out.flush();
			out.close();
		}
	}
}
	
//	@Overriade
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
//		throws IOException, ServletException {
//			
//			HttpServletRequest req = (HttpServletRequest) request;
//			
//			System.out.println("uri : " + req.getRequestURI());
//			System.out.println("url : " + req.getRequestURL());
//			//index.jsp만 허용
//			if(req.getRequestURI().equals("/Greenbooks/index.jsp") 
//					|| req.getRequestURI().equals("/Greenbooks/")) {
//				chain.doFilter(req, response);
//			}
//			else {
//				PrintWriter out = response.getWriter();
//				out.print("잘못된 접근입니다.");
//				out.flush();
//				out.close();
//			}

