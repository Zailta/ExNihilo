package com.blog.application.DataObject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.application.EXNEntity.EXNCommentEntity;

@Repository
public interface EXNCommentDAOLayer extends JpaRepository<EXNCommentEntity, String>{

}
