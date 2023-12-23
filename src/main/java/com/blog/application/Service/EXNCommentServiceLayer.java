package com.blog.application.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.application.Bean.EXNCommentBean;
import com.blog.application.DataObject.EXNCommentDAOLayer;
import com.blog.application.DataObject.EXNPostsDAOLayer;
import com.blog.application.DataObject.EXNUserDAOLayer;
import com.blog.application.EXNEntity.EXNCommentEntity;
import com.blog.application.EXNEntity.EXNPostsEntity;
import com.blog.application.EXNEntity.EXNUserEntity;
import com.blog.application.Exception.CustomExceptions.EXNResourceNotFoundException;
import com.blog.application.Service.ServiceInterfaces.EXNCommentServiceInterface;
import com.blog.application.Utility.EXNAPIResponse;

@Service
public class EXNCommentServiceLayer implements EXNCommentServiceInterface{

	@Autowired
	EXNUserDAOLayer exnUserDAOLayer;
	@Autowired
	EXNPostsDAOLayer exnPostsDAOLayer;
	@Autowired
	EXNCommentDAOLayer commentDAOLayer;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public EXNCommentBean postComment(EXNCommentBean commentBean, String UserID, String postID) {
		EXNUserEntity userEntity = exnUserDAOLayer.findById(UserID).orElseThrow(()->new EXNResourceNotFoundException("User", "ID", UserID));
		EXNPostsEntity postEntity = exnPostsDAOLayer.findById(postID).orElseThrow(()->new EXNResourceNotFoundException("Post", "ID", UserID));
		EXNCommentEntity commentEntity = modelMapper.map(commentBean, EXNCommentEntity.class);
		commentEntity.setCommentDate(new Date());
		commentEntity.setUser(userEntity);
		commentEntity.setPost(postEntity);
		/*
		 * Set<EXNCommentEntity>commentsSet = new HashSet<EXNCommentEntity>();
		 * commentsSet.add(commentEntity); postEntity.setCommentEntity(commentsSet);
		 * userEntity.setCommentEntity(commentsSet);
		 */
		EXNCommentEntity savedComment = commentDAOLayer.save(commentEntity);
		
		return modelMapper.map(savedComment, EXNCommentBean.class);
	}

	@Override
	public void deleteComment(String commentID) {
		commentDAOLayer.findById(commentID).orElseThrow(()->new EXNResourceNotFoundException("Comment", "ID", commentID));
		commentDAOLayer.deleteById(commentID);
		
	}

}
