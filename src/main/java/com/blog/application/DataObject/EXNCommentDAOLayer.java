package com.blog.application.DataObject;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.EXNEntity.EXNCommentEntity;

public interface EXNCommentDAOLayer extends JpaRepository<EXNCommentEntity, String>{

}
