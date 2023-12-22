package com.blog.application.EXNEntity;

import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EXNCommentEntity {
	@Id
	@UuidGenerator
	private String commentId;
	private String comment;
	private Date commentDate;
	@ManyToOne
	private EXNPostsEntity post;
	@ManyToOne
	private EXNUserEntity user;
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public EXNPostsEntity getPost() {
		return post;
	}
	public void setPost(EXNPostsEntity post) {
		this.post = post;
	}
	public EXNUserEntity getUser() {
		return user;
	}
	public void setUser(EXNUserEntity user) {
		this.user = user;
	}
	
	
	
	
	
}
