package com.blog.application.Bean;

import java.util.Date;

public class EXNPostsBean {
	private String postId;
	private String postContent;
	private EXNFileProcessingServiceBean image;
	private Date publishedDate;
	private EXNUserBean exnUserEntity;
	private EXNCategoryBean categoryEntity;
	
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public EXNFileProcessingServiceBean getImage() {
		return image;
	}
	public void setImage(EXNFileProcessingServiceBean image) {
		this.image = image;
	}
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public EXNUserBean getExnUserEntity() {
		return exnUserEntity;
	}
	public void setExnUserEntity(EXNUserBean exnUserEntity) {
		this.exnUserEntity = exnUserEntity;
	}
	public EXNCategoryBean getCategoryEntity() {
		return categoryEntity;
	}
	public void setCategoryEntity(EXNCategoryBean categoryEntity) {
		this.categoryEntity = categoryEntity;
	}
	

}
