package com.blog.application.DataObject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.application.Bean.EXNPostsBean;
import com.blog.application.EXNEntity.EXNCategoryEntity;
import com.blog.application.EXNEntity.EXNPostsEntity;
import com.blog.application.EXNEntity.EXNUserEntity;


@Repository
public interface EXNPostsDAOLayer  extends CrudRepository<EXNPostsEntity, String>{
	
	  List<EXNPostsEntity> findByexnUserEntity(EXNUserEntity userID);
	  
	  List<EXNPostsEntity> findBycategoryEntity(EXNCategoryEntity categoryID);
	 
}
