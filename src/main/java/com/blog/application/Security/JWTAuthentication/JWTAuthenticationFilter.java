package com.blog.application.Security.JWTAuthentication;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blog.application.Exception.CustomExceptions.EXNResourceNotFoundException;

import org.springframework.context.annotation.Lazy ;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component

public class JWTAuthenticationFilter extends OncePerRequestFilter{
	
	JWTTokenHelper jwtTokenHelper;
	UserDetailsService userDetailsService;
	
	@Autowired
    public JWTAuthenticationFilter(JWTTokenHelper jwtTokenHelper, @Lazy UserDetailsService userDetailsService) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
    }
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, EXNResourceNotFoundException {
		String token = null;
		String userName = null;
		String tokenheader = request.getHeader("Authorization");
		if(tokenheader!=null && tokenheader.startsWith("Bearer")) {
			
			
			
			 token = tokenheader.substring(7);			 
		}else {
			System.out.println("Token header is Not valid");
			
		}
		
		if(StringUtils.hasText(token) && jwtTokenHelper.validateToken(token)) {
			 userName = jwtTokenHelper.getUsername(token);
			 if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
				 UserDetails loadUserByUsername = this.userDetailsService.loadUserByUsername(userName);
				 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loadUserByUsername, null,loadUserByUsername.getAuthorities());
				 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			 }
			 else {
				 System.out.println("User is Null!");
			 }
			 }else {
				 System.out.println("Token validation Failed!");
			 }
		
	filterChain.doFilter(request, response);
	} 
	}
	
	


