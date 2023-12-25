package com.blog.application.Security.JWTAuthentication;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blog.application.Bean.EXNUserBean;
import com.blog.application.Service.EXNUserServiceLayer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	JWTTokenHelper jwtTokenHelper;
	@Autowired
	UserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String tokenheader = request.getHeader("Authorization");
		if(tokenheader!=null && tokenheader.startsWith("Bearer")) {
			String token = null;
			String userName = null;
			 token = tokenheader.substring(7);
			 if(StringUtils.hasText(token) && jwtTokenHelper.validateToken(token)) {
			 userName = jwtTokenHelper.getUsername(token);
			 if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
				 UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(userName);
				 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loadUserByUsername, null,loadUserByUsername.getAuthorities());
				 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			 }
			 }else {
				 System.out.println("Invalid Token");
			 }
			 
		}else {
			System.out.println("Invalid Toekn Header");
		}
		
	filterChain.doFilter(request, response);	
	}

}
