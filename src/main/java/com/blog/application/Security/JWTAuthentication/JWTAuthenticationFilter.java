package com.blog.application.Security.JWTAuthentication;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blog.application.Security.UserConfig.EXNUserDetailsService;
import com.blog.application.Service.EXNUserServiceLayer;
import com.blog.application.Service.ServiceInterfaces.EXNUserServieInterface;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	JWTTokenHelper jwtTokenHelper;
	
	EXNUserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = null;
		String userName = null;
		String tokenheader = request.getHeader("Authorization");
		if(tokenheader!=null && tokenheader.startsWith("Bearer")) {
			
			
			 token = tokenheader.substring(7);			 
		}else {
			System.out.println("Invalid Token Header");
			
		}
		
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
		
	filterChain.doFilter(request, response);	
	}

}
