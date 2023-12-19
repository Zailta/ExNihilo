package com.blog.application.DataObject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.application.EXNEntity.EXNCategoryEntity;

@Repository
public interface EXNCategoryDAOlayer extends CrudRepository<EXNCategoryEntity, String>{


}
