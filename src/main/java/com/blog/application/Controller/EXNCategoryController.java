package com.blog.application.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.Bean.EXNCategoryBean;
import com.blog.application.Service.EXNCategoryServiceLayer;
import com.blog.application.Utility.EXNAPIResponse;

@RestController(value = "/api/categories")
public class EXNCategoryController {
	
	@Autowired
	EXNCategoryServiceLayer categoryServiceLayer;
	@PostMapping
	public ResponseEntity<EXNCategoryBean> createCategory(EXNCategoryBean categoryBean) {
		EXNCategoryBean createUser = categoryServiceLayer.createUser(categoryBean);
		return new ResponseEntity<EXNCategoryBean>(createUser, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<EXNCategoryBean> findById(String categoryID) {
		EXNCategoryBean findById = categoryServiceLayer.findById(categoryID);
		return new ResponseEntity<EXNCategoryBean>(findById, HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<EXNCategoryBean> updateCategory(String categoryID, EXNCategoryBean categoryBean) {
		EXNCategoryBean updateUser = categoryServiceLayer.updateUser(categoryID, categoryBean);
		return new ResponseEntity<EXNCategoryBean>(updateUser, HttpStatus.OK);
	}
	@DeleteMapping
	public ResponseEntity<EXNAPIResponse> deleteCategoryString (String categoryID) {
		categoryServiceLayer.deleteUser(categoryID);
		return new ResponseEntity<EXNAPIResponse>(new EXNAPIResponse("The Category has been Deleted Successfully!!", true), HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<EXNCategoryBean>>findAll(){
		List<EXNCategoryBean> findAll = categoryServiceLayer.findAll();
		return new ResponseEntity<List<EXNCategoryBean>>(findAll, HttpStatus.OK);
		
	}


}
