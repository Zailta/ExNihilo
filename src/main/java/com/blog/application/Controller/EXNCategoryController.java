package com.blog.application.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.Bean.EXNCategoryBean;
import com.blog.application.Service.EXNCategoryServiceLayer;
import com.blog.application.Utility.EXNAPIResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController()
@RequestMapping(value = "/api/categories")
@SecurityRequirement(name = "authBearer")
@Tag(
		name = "Categories",
		description = "	CATEGORY-API can be consumed to perform CRUD Operations on Blog Categories")
public class EXNCategoryController {
	
	@Autowired
	EXNCategoryServiceLayer categoryServiceLayer;
	
	@PostMapping(value = "/create")
	public ResponseEntity<EXNCategoryBean> createCategory(@RequestBody EXNCategoryBean categoryBean) {
		EXNCategoryBean createUser = categoryServiceLayer.createUser(categoryBean);
		return new ResponseEntity<EXNCategoryBean>(createUser, HttpStatus.OK);
	}
	@GetMapping(value = "/{categoryID}")
	public ResponseEntity<EXNCategoryBean> findById(@PathVariable String categoryID) {
		EXNCategoryBean findById = categoryServiceLayer.findById(categoryID);
		return new ResponseEntity<EXNCategoryBean>(findById, HttpStatus.OK);
	}
	@PutMapping(value = "/{categoryID}")
	public ResponseEntity<EXNCategoryBean> updateCategory(@PathVariable String categoryID, @RequestBody EXNCategoryBean categoryBean) {
		EXNCategoryBean updateUser = categoryServiceLayer.updateUser(categoryID, categoryBean);
		return new ResponseEntity<EXNCategoryBean>(updateUser, HttpStatus.OK);
	}
	@DeleteMapping(value = "/{categoryID}")
	public ResponseEntity<EXNAPIResponse> deleteCategoryString (@PathVariable String categoryID) {
		categoryServiceLayer.deleteUser(categoryID);
		return new ResponseEntity<EXNAPIResponse>(new EXNAPIResponse("The Category has been Deleted Successfully!!", true), HttpStatus.OK);
	}
	@GetMapping(value = "/")
	public ResponseEntity<List<EXNCategoryBean>>findAll(){
		List<EXNCategoryBean> findAll = categoryServiceLayer.findAll();
		return new ResponseEntity<List<EXNCategoryBean>>(findAll, HttpStatus.OK);
		
	}


}
