package com.blog.application.Security.JWTAuthentication;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends OncePerRequestFilter{

	public JWTAuthenticationFilter() {
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String tokenheader = request.getHeader("Authorization");
		if(tokenheader!=null && tokenheader.startsWith("Bearer")) {
			String token = null;
			String userName = null;
		}else {
			System.out.println("Invalid Toekn Header");
		}
		
	filterChain.doFilter(request, response);	
	}

}
