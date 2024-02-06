package config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharConfig implements Filter{
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//ServletRequest로 받아온 매개변수를 HttpServletRequest로 형변환
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//요청 데이터 한글 인코딩 설정
		request.setCharacterEncoding("utf-8");
		//응답 데이터 한글 인코딩 설정
		response.setContentType("text/html; charset=utf-8");
		//다음 필터나 서블릿으로 보내주기 
		chain.doFilter(request, response);
	}
}

