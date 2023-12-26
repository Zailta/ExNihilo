package com.blog.application.EXNEntity;

import org.hibernate.annotations.UuidGenerator;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class EXNUserEntity {

	@Id
	@UuidGenerator
	private String Id;	
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String about;
	@Column
	private String role;
	@OneToMany(mappedBy = "exnUserEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<EXNPostsEntity> exnPosts = new ArrayList<>();
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<EXNCommentEntity>commentEntity =  new HashSet<EXNCommentEntity>();
	
	public EXNUserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public List<EXNPostsEntity> getExnPosts() {
		return exnPosts;
	}
	public void setExnPosts(List<EXNPostsEntity> exnPosts) {
		this.exnPosts = exnPosts;
	}
	public Set<EXNCommentEntity> getCommentEntity() {
		return commentEntity;
	}
	public void setCommentEntity(Set<EXNCommentEntity> commentEntity) {
		this.commentEntity = commentEntity;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
