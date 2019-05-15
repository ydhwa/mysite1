package com.cafe24.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//@WebFilter(value="/*", initParams=@WebInitParam(name="encoding", value="utf-8"))
public class EncodingFilter implements Filter {
	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		// web.xml에 정의해둔 설정이 들어온다.
		System.out.println("Encoding Filter Initialized...");
		encoding = fConfig.getInitParameter("encoding");
		
		if(encoding == null) {
			encoding = "utf-8";
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* request 처리 */
		request.setCharacterEncoding(encoding);	// 나중에 필터로 넘어감
		
		chain.doFilter(request, response);
		
		/* response 처리 */
	}
	
	public void destroy() {
	}

}
