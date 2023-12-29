package com.blog.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.Bean.EXNCommentBean;
import com.blog.application.Service.EXNCommentServiceLayer;
import com.blog.application.Utility.EXNAPIResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/comments")
@SecurityRequirement(name = "authBearer")
@Tag(
		name = "Comments",
		description = "	COMMENT-API can be consumed to perform CRUD Operations on Blog comments")
public class EXNCommentController {
	
	@Autowired
	EXNCommentServiceLayer commentServiceLayer;
	
	@PostMapping(value = "/comment/{userID}/{postID}")
	public ResponseEntity<EXNCommentBean> createComment(@RequestBody EXNCommentBean bean, @PathVariable String userID,
			@PathVariable String postID) {
		EXNCommentBean postComment = commentServiceLayer.postComment(bean, userID, postID);
		return ResponseEntity.ok(postComment);
	}
	
	@DeleteMapping(value = "/{commentID}")
	public ResponseEntity<EXNAPIResponse> createComment(@PathVariable String commentID) {
		 commentServiceLayer.deleteComment(commentID);
		return new ResponseEntity<EXNAPIResponse>(new EXNAPIResponse("Comment has been Deleted Successfully", true), HttpStatus.OK);
	}

}
