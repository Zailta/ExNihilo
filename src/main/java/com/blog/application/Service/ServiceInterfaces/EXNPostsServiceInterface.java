package com.blog.application.Service.ServiceInterfaces;

import java.util.List;
import com.blog.application.Bean.EXNPostsBean;

public interface EXNPostsServiceInterface {
	
	EXNPostsBean createPost(EXNPostsBean postBean, String userID, String categoryID );
	EXNPostsBean findById(String postID);
	EXNPostsBean updatePost(String postID, EXNPostsBean postBean, String userID, String categoryID);
	void deletePost(String postID);
	List<EXNPostsBean> findAll(Integer pageNumber, Integer PageSize);
	List<EXNPostsBean> getPostByUserID(String userID);
	List<EXNPostsBean> getPostByCategoryId(String categoryID);
	

}
