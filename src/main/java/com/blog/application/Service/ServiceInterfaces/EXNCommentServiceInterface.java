package com.blog.application.Service.ServiceInterfaces;

import com.blog.application.Bean.EXNCommentBean;
import com.blog.application.Utility.EXNAPIResponse;

public interface EXNCommentServiceInterface {
	
	EXNCommentBean postComment(EXNCommentBean commentBean, String UserID, String postID);
	void deleteComment(String commentID);
}
