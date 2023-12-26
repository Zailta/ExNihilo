package com.blog.application.DataObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.application.EXNEntity.EXNUserEntity;

@Repository
public interface EXNUserDAOLayer extends CrudRepository<EXNUserEntity, String>{
	EXNUserEntity findByuserName(String userName);
	
	
}
