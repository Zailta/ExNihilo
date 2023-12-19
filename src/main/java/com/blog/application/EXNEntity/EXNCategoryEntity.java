package com.blog.application.EXNEntity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class EXNCategoryEntity {
	@Id
	@UuidGenerator
	private String categoryId;
	private String category;
	private String categoryDescription;
	
	@OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<EXNPostsEntity> exnPosts = new ArrayList<EXNPostsEntity>();
	
	public EXNCategoryEntity() {
		
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public List<EXNPostsEntity> getExnPosts() {
		return exnPosts;
	}
	public void setExnPosts(List<EXNPostsEntity> exnPosts) {
		this.exnPosts = exnPosts;
	}
	
	

}
