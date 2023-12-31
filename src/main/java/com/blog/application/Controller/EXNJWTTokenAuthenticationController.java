package com.blog.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.Bean.EXNUserAuthenticationBean;
import com.blog.application.Security.JWTAuthentication.JWTAuthenticationResponse;
import com.blog.application.Security.JWTAuthentication.JWTTokenHelper;
import com.blog.application.Service.EXNUserServiceLayer;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/token")
@Tag(
		name = "Authentication Token Generator",
		description = "	TOKEN-API can be consumed to Generate Tokens for Authentication")
public class EXNJWTTokenAuthenticationController {
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value = "/generateToken")
	public ResponseEntity<JWTAuthenticationResponse> AuthenticateUserandGenerateToken(@RequestBody EXNUserAuthenticationBean authenticationBean){
		Authentication authenticate = authenticationManager.
				authenticate(new UsernamePasswordAuthenticationToken(authenticationBean.getUserName(), authenticationBean.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		String token  = jwtTokenHelper.generateToken(authenticate);
		JWTAuthenticationResponse authenticationResponse = new JWTAuthenticationResponse();
		authenticationResponse.setToken(token);
		
		return new ResponseEntity<JWTAuthenticationResponse>(authenticationResponse, HttpStatus.OK);
	}
	
	

}
