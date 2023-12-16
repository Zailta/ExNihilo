package com.blog.application.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.Bean.EXNUserBean;
import com.blog.application.Service.EXNUserServiceLayer;

@RestController
@RequestMapping(value = "/api/users")
public class EXNRestController {
	@Autowired
	EXNUserServiceLayer exnUserServiceLayer;
	
	
	@PostMapping(value = "/")
	public ResponseEntity<EXNUserBean> createUser(@RequestBody EXNUserBean bean){
		EXNUserBean createUser = this.exnUserServiceLayer.createUser(bean);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
		
	}
	
}
