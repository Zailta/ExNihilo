package com.blog.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.Bean.EXNUserAuthenticationBean;
import com.blog.application.Security.JWTAuthentication.JWTAuthenticationResponse;
import com.blog.application.Service.EXNUserServiceLayer;

@RestController
@RequestMapping("/api")
public class EXNJWTTokenAuthenticationController {
	
	@Autowired
	private EXNUserServiceLayer exnUserServiceLayer;
	
	@PostMapping(value = "/generateToken")
	public ResponseEntity<JWTAuthenticationResponse> AuthenticateUserandGenerateToken(@RequestBody EXNUserAuthenticationBean authenticationBean){
		String token = exnUserServiceLayer.generateToken(authenticationBean);
		JWTAuthenticationResponse authenticationResponse = new JWTAuthenticationResponse();
		authenticationResponse.setToken(token);
		
		return new ResponseEntity<JWTAuthenticationResponse>(authenticationResponse, HttpStatus.OK);
	}
	
	

}
