package com.blog.application.Service.ServiceInterfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blog.application.Bean.EXNPostsBean;
import com.blog.application.Utility.EXNPostResponse;

public interface EXNPostsServiceInterface {
	
	EXNPostsBean createPost(String postContent, String userID, String categoryID, MultipartFile file) throws IOException;
	EXNPostsBean findById(String postID);
	EXNPostsBean updatePost(String postID, String postContent, String userID, String categoryID,MultipartFile file) throws IOException;
	void deletePost(String postID);
	EXNPostResponse findAll(Integer pageNumber, Integer PageSize, String sortBY);
	List<EXNPostsBean> getPostByUserID(String userID);
	List<EXNPostsBean> getPostByCategoryId(String categoryID);
	List<EXNPostsBean> searchPost(String keyword);
	

}
