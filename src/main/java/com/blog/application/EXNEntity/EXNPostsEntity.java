package com.blog.application.EXNEntity;

import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import com.blog.application.Bean.EXNFileProcessingServiceBean;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
@Entity
public class EXNPostsEntity {
	
	@Id
	@UuidGenerator 
	private String postId;
	private String postContent;
	@Embedded
	private EXNFileProcessingServiceBean image;
	private Date publishedDate;
	
	@ManyToOne
	private EXNUserEntity exnUserEntity;
	@ManyToOne
	@JoinColumn(name = "exnCategory_Id")
	private EXNCategoryEntity categoryEntity;
	
	public EXNPostsEntity() {
		// TODO Auto-generated constructor stub
	}

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

	public EXNUserEntity getExnUserEntity() {
		return exnUserEntity;
	}

	public void setExnUserEntity(EXNUserEntity exnUserEntity) {
		this.exnUserEntity = exnUserEntity;
	}

	public EXNCategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public void setCategoryEntity(EXNCategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}
	
	

}
