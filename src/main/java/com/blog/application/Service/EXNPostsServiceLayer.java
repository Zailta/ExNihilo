package com.blog.application.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.blog.application.Bean.EXNFileProcessingServiceBean;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.application.Bean.EXNPostsBean;
import com.blog.application.DataObject.EXNCategoryDAOlayer;
import com.blog.application.DataObject.EXNPostsDAOLayer;
import com.blog.application.DataObject.EXNUserDAOLayer;
import com.blog.application.EXNEntity.EXNCategoryEntity;
import com.blog.application.EXNEntity.EXNPostsEntity;
import com.blog.application.EXNEntity.EXNUserEntity;
import com.blog.application.Exception.CustomExceptions.EXNResourceNotFoundException;
import com.blog.application.Service.ServiceInterfaces.EXNPostsServiceInterface;
import com.blog.application.Utility.EXNPostResponse;

import org.springframework.util.StringUtils;

@Service
public class EXNPostsServiceLayer implements EXNPostsServiceInterface{

	@Autowired
	EXNPostsDAOLayer  exnPostsDAOLayer;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	EXNCategoryDAOlayer categoryDAOlayer;
	@Autowired
	private EXNUserDAOLayer exnUserDAOLayer;
	@Autowired
	private EXNPostsEntity createPost;
	@Override
	public EXNPostsBean createPost(String postContent, String userID, String categoryID, MultipartFile file) throws IOException {
		EXNUserEntity userEntity = exnUserDAOLayer.findById(userID).orElseThrow(()->new EXNResourceNotFoundException("User", "ID", userID));
		EXNCategoryEntity categoryEntity = categoryDAOlayer.findById(categoryID).orElseThrow(()->new EXNResourceNotFoundException("category", "ID", categoryID));
		createPost.setPostContent(postContent);
		createPost.setPublishedDate(new Date());
		createPost.setExnUserEntity(userEntity);
		createPost.setCategoryEntity(categoryEntity);
		if(!file.isEmpty()) {
		EXNFileProcessingServiceBean fileprocessingBean = new EXNFileProcessingServiceBean();
		fileprocessingBean.setFileContent(file.getBytes());
		fileprocessingBean.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
		fileprocessingBean.setFilteType(file.getContentType());
		fileprocessingBean.setFileSize(file.getSize());
		createPost.setImage(fileprocessingBean);
		}
		
		EXNPostsEntity savedPost = this.exnPostsDAOLayer.save(createPost);
		EXNPostsBean userBean = entityToBean(savedPost);
		/*
		 * userBean.setExnUserBean(this.modelMapper.map(userEntity, EXNUserBean.class));
		 * userBean.setCategoryBean(this.modelMapper.map(categoryEntity,
		 * EXNCategoryBean.class));
		 */
		
		return userBean;
	}

	@Override
	public EXNPostsBean findById(String postID) {
		EXNPostsEntity findByID = exnPostsDAOLayer.findById(postID).orElseThrow(()->new EXNResourceNotFoundException("Post", "ID", postID));
		return entityToBean(findByID);
	}

	@Override
	public EXNPostsBean updatePost(String postID, String postContent, String userID, String categoryID, MultipartFile file) throws IOException {
		EXNUserEntity userEntity = exnUserDAOLayer.findById(userID).orElseThrow(()->new EXNResourceNotFoundException("User", "ID", userID));
		EXNCategoryEntity categoryEntity = categoryDAOlayer.findById(categoryID).orElseThrow(()->new EXNResourceNotFoundException("category", "ID", categoryID));
		EXNPostsEntity findByID = exnPostsDAOLayer.findById(postID).orElseThrow(()->new EXNResourceNotFoundException("Post", "ID", postID));
		findByID.setCategoryEntity(categoryEntity);
		findByID.setExnUserEntity(userEntity);
		findByID.setPostContent(postContent);
		findByID.setPublishedDate(new Date());
		if(!file.isEmpty()) {
		EXNFileProcessingServiceBean fileprocessingBean = new EXNFileProcessingServiceBean();
		fileprocessingBean.setFileContent(file.getBytes());
		fileprocessingBean.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
		fileprocessingBean.setFilteType(file.getContentType());
		fileprocessingBean.setFileSize(file.getSize());
		findByID.setImage(fileprocessingBean);
		}
		EXNPostsEntity updatedPost = this.exnPostsDAOLayer.save(findByID);
		EXNPostsBean postbeanConverted = entityToBean(updatedPost);;
		/*
		 * postbeanConverted.setExnUserBean(this.modelMapper.map(userEntity,
		 * EXNUserBean.class));
		 * postbeanConverted.setCategoryBean(this.modelMapper.map(categoryEntity,
		 * EXNCategoryBean.class));
		 */
		return postbeanConverted;
		
	}

	@Override
	public void deletePost(String postID) {
		EXNPostsEntity findByID = exnPostsDAOLayer.findById(postID).orElseThrow(()->new EXNResourceNotFoundException("Post", "ID", postID));
		this.exnPostsDAOLayer.delete(findByID);	
	}

	@Override
	public EXNPostResponse findAll(Integer pageNumber, Integer PageSize, String sortBY) {
		 PageRequest pagination = PageRequest.of(pageNumber, PageSize, Sort.by(sortBY));
		 Page<EXNPostsEntity> page = this.exnPostsDAOLayer.findAll(pagination);
		 List<EXNPostsEntity> findAll = page.getContent();
		 
		List<EXNPostsBean> allPosts = findAll.stream().map(post->entityToBean(post)).collect(Collectors.toList());
		
		EXNPostResponse exnPostResponse = new EXNPostResponse();
		exnPostResponse.setPostList(allPosts);
		exnPostResponse.setPageNumber(page.getNumber());
		exnPostResponse.setPageSize(page.getSize());
		exnPostResponse.setTotalElements(page.getTotalElements());
		exnPostResponse.setTotalPages(page.getTotalPages());
		exnPostResponse.setLastPage(page.isLast());
		
		return exnPostResponse ;
	}

	@Override
	public List<EXNPostsBean> getPostByUserID(String userID) {
		EXNUserEntity userEntity = exnUserDAOLayer.findById(userID).orElseThrow(()->new EXNResourceNotFoundException("User", "ID", userID));
		List<EXNPostsEntity> findByexnUserEntity = exnPostsDAOLayer.findByexnUserEntity(userEntity);
		List<EXNPostsBean> postByuserID = findByexnUserEntity.stream().map(post->entityToBean(post)).collect(Collectors.toList());
		return postByuserID;
	}

	@Override
	public List<EXNPostsBean> getPostByCategoryId(String categoryID) {
		EXNCategoryEntity categoryEntity = categoryDAOlayer.findById(categoryID).orElseThrow(()->new EXNResourceNotFoundException("category", "ID", categoryID));
		List<EXNPostsEntity> findBycategoryEntity = exnPostsDAOLayer.findBycategoryEntity(categoryEntity);
		List<EXNPostsBean> postByCategoryID = findBycategoryEntity.stream().map(post->entityToBean(post)).collect(Collectors.toList());
		return postByCategoryID;
	}
	
	@Override
	public List<EXNPostsBean> searchPost(String keyword) {
		List<EXNPostsEntity> findBypostContentContaining = exnPostsDAOLayer.findBypostContentContaining(keyword);
		List<EXNPostsBean> searchedPost = findBypostContentContaining.stream().map(post->entityToBean(post)).collect(Collectors.toList());
		return searchedPost;
	}
	
	//Utility Methods:
			//utility methods
			public EXNPostsEntity beanToEntity(EXNPostsBean bean) {
				EXNPostsEntity entity= this.modelMapper.map(bean,EXNPostsEntity.class);
				
				return entity;
			}
			public EXNPostsBean entityToBean(EXNPostsEntity entity) {
				EXNPostsBean bean = this.modelMapper.map(entity, EXNPostsBean.class);
				return bean;
			}

			



}
