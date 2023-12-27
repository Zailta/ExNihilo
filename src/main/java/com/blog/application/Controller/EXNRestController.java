package com.blog.application.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.Bean.EXNUserBean;
import com.blog.application.Service.EXNUserServiceLayer;
import com.blog.application.Utility.EXNAPIResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
public class EXNRestController {
	@Autowired
	EXNUserServiceLayer exnUserServiceLayer;
	
	
	@PostMapping(value = "/create")
	public ResponseEntity<EXNUserBean> createUser(@Valid @RequestBody EXNUserBean bean){
		EXNUserBean createUser = this.exnUserServiceLayer.createUser(bean);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
		
	}
	
	@GetMapping(value = "/{userID}")
	public ResponseEntity<EXNUserBean> findUser(@PathVariable String userID){
		EXNUserBean createUser = this.exnUserServiceLayer.findById(userID.trim());
		return  ResponseEntity.ok(createUser);
		
		
	}
	@GetMapping(value = "/")
	public ResponseEntity<List<EXNUserBean>> findAllUser(){
		List<EXNUserBean> findAll = this.exnUserServiceLayer.findAll();
		return  new ResponseEntity<>(findAll, HttpStatus.OK);
		
		
	}
	
	@PutMapping(value = "/{userID}")
	public ResponseEntity<EXNUserBean> updateUser(@PathVariable String userID, @Valid @RequestBody EXNUserBean bean ){
		EXNUserBean updatedUser = this.exnUserServiceLayer.updateUser(userID.trim(), bean);
		return  ResponseEntity.ok(updatedUser);
		
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{userID}")
	public ResponseEntity<EXNAPIResponse> deleteUser(@PathVariable String userID ){
		 this.exnUserServiceLayer.deleteUser(userID.trim());
		return  new ResponseEntity<EXNAPIResponse>(new EXNAPIResponse("The user has been Deleted Successfully!!", true), HttpStatus.OK );
		
		
	}
	
	
}
