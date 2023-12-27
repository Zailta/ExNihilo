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
    JWTAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
    public JWTAuthenticationFilter(JWTTokenHelper jwtTokenHelper, @Lazy UserDetailsService userDetailsService) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
    }
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, EXNResourceNotFoundException {
		try {
		String token = null;
		String userName = null;
		String tokenheader = request.getHeader("Authorization");
		if(tokenheader!=null && tokenheader.startsWith("Bearer")) {
			
			
			
			 token = tokenheader.substring(7);			 
		}else {
			throw new EXNResourceNotFoundException("Token", "value", "Null");
			
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
				 throw new EXNResourceNotFoundException("Token", "ID",token);
			 }
			 }else {
				 throw new EXNResourceNotFoundException("Token", "ID",token);
			 }
		
	filterChain.doFilter(request, response);
	} catch (EXNResourceNotFoundException ex) {
        // Handle the exception and send a proper JSON response
		String errorMessage = ex.getMessage();
	    int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
	    String timestamp = Instant.now().toString();
	    String uuid = UUID.randomUUID().toString();

	    response.setStatus(statusCode);
	    response.setContentType("application/json");

	    String jsonResponse = String.format("{\"message\": \"%s\", \"statusCode\": %d, \"timestamp\": \"%s\", \"tracker\": \"%s\"}",
	            errorMessage, statusCode, timestamp, uuid);

	    response.getWriter().write(jsonResponse);
    }
	}
	
	

}
