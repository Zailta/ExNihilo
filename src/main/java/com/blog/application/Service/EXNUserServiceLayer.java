package com.blog.application.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.blog.application.Bean.EXNUserBean;
import com.blog.application.DataObject.EXNUserDAOLayer;
import com.blog.application.EXNEntity.EXNUserEntity;
import com.blog.application.Exception.CustomExceptions.EXNResourceNotFoundException;
import com.blog.application.Service.ServiceInterfaces.EXNUserServieInterface;



@Service
public class EXNUserServiceLayer implements EXNUserServieInterface{
@Autowired
private EXNUserDAOLayer exnUserDAOLayer;
@Autowired
private ModelMapper modelMapper;

@Override
public EXNUserBean createUser(EXNUserBean userBean) {
	EXNUserEntity entity = beanToEntity(userBean);
	EXNUserEntity savedUser = exnUserDAOLayer.save(entity);
	return this.entityToBean(savedUser);
}

@Override
public EXNUserBean findById(String userID) {
	
	EXNUserEntity userEntity = exnUserDAOLayer.findById(userID).orElseThrow(()->new EXNResourceNotFoundException("User", "ID", userID));
	return entityToBean(userEntity);
}

@Override
public EXNUserBean updateUser(String userID) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public EXNUserBean deleteUser(String userID) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<EXNUserBean> findAll() {
	// TODO Auto-generated method stub
	return null;
}

//utility methods
public EXNUserEntity beanToEntity(EXNUserBean bean) {
	EXNUserEntity entity= this.modelMapper.map(bean,EXNUserEntity.class);
	
	return entity;
}
public EXNUserBean entityToBean(EXNUserEntity entity) {
	EXNUserBean bean = this.modelMapper.map(entity, EXNUserBean.class);
	return bean;
}
}
