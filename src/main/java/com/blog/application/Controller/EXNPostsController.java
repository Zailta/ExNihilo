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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.Bean.EXNPostsBean;
import com.blog.application.Service.EXNPostsServiceLayer;
import com.blog.application.Utility.EXNAPIResponse;

@RestController
@RequestMapping(value = "/api/posts")
public class EXNPostsController {
	@Autowired
	EXNPostsServiceLayer exnPostsServiceLayer;
	@PostMapping(value = "/create/{userID}/{categoryID}")
	public ResponseEntity<EXNPostsBean> createPost(@PathVariable String userID, @PathVariable String categoryID, @RequestBody EXNPostsBean bean){
		EXNPostsBean createPost = exnPostsServiceLayer.createPost(bean, userID, categoryID);		
		return new ResponseEntity<>(createPost, HttpStatus.OK);
		
	}

	@GetMapping(value = "/{postID}")
	public ResponseEntity<EXNPostsBean> getPost(@PathVariable String postID){
		EXNPostsBean findByID = exnPostsServiceLayer.findById(postID);		
		return ResponseEntity.ok(findByID);
		
	}
	
	@PutMapping(value = "/update/{postID}/{userID}/{categoryID}")
	public ResponseEntity<EXNPostsBean> getPost(@PathVariable String postID,@PathVariable String userID, @PathVariable String categoryID, @RequestBody EXNPostsBean bean){
		EXNPostsBean updatePost = exnPostsServiceLayer.updatePost(postID, bean, userID, categoryID);	
		
		return ResponseEntity.ok(updatePost);
		
	}
	
	@DeleteMapping(value = "/delete/{postID}")
	public ResponseEntity<EXNAPIResponse> deletePost(@PathVariable String postID){
		exnPostsServiceLayer.deletePost(postID)	;
		
		return new ResponseEntity<EXNAPIResponse>(new EXNAPIResponse("The post has been deleted successfully!!", true), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<EXNPostsBean>> getPost(@RequestParam(value = "pageNumber",defaultValue = "0", required = false) Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = "2", required = false)Integer pageSize){
		List<EXNPostsBean> findAll = exnPostsServiceLayer.findAll(pageNumber,pageSize);		
		return ResponseEntity.ok(findAll);
		
	}
	
	@GetMapping(value = "/findbyuser/{userID}")
	public ResponseEntity<List<EXNPostsBean>> getPostByUserID( @PathVariable String userID){
		List<EXNPostsBean> findAll = exnPostsServiceLayer.getPostByUserID(userID);	
		return ResponseEntity.ok(findAll);
		
	}
	@GetMapping(value = "/findbycategory/{categoryID}")
	public ResponseEntity<List<EXNPostsBean>> getPostCategoryID(@PathVariable String categoryID){
		List<EXNPostsBean> findAll = exnPostsServiceLayer.getPostByCategoryId(categoryID);	
		return ResponseEntity.ok(findAll);
		
	}
	
	
}
