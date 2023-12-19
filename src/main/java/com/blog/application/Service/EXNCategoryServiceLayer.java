package com.blog.application.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.application.Bean.EXNCategoryBean;
import com.blog.application.DataObject.EXNCategoryDAOlayer;
import com.blog.application.EXNEntity.EXNCategoryEntity;
import com.blog.application.Exception.CustomExceptions.EXNResourceNotFoundException;
import com.blog.application.Service.ServiceInterfaces.EXNCategoryServiceInterface;

@Service
public class EXNCategoryServiceLayer implements EXNCategoryServiceInterface{
	
	@Autowired
	EXNCategoryDAOlayer categoryDAOlayer;
	@Autowired
	private ModelMapper modelMapper;

	public EXNCategoryServiceLayer(){
		// TODO Auto-generated constructor stub
	}

	
	


	@Override
	public EXNCategoryBean createUser(EXNCategoryBean categoryBean) {
		
		return entityToBean(categoryDAOlayer.save(beanToEntity(categoryBean)));
	}


	@Override
	public EXNCategoryBean findById(String categoryID) {
		EXNCategoryEntity categoryEntity = categoryDAOlayer.findById(categoryID).orElseThrow(()->new EXNResourceNotFoundException("category", "ID", categoryID));
		return entityToBean(categoryEntity);
	}


	@Override
	public EXNCategoryBean updateUser(String categoryID, EXNCategoryBean categoryBean) {
		EXNCategoryEntity categoryEntity = categoryDAOlayer.findById(categoryID).orElseThrow(()->new EXNResourceNotFoundException("category", "ID", categoryID));
		EXNCategoryEntity updatedCategory = categoryDAOlayer.save(beanToEntity(categoryBean));
		
		return entityToBean(updatedCategory);
	}


	@Override
	public void deleteUser(String categoryID) {
		EXNCategoryEntity categoryEntity = categoryDAOlayer.findById(categoryID).orElseThrow(()->new EXNResourceNotFoundException("category", "ID", categoryID));
		categoryDAOlayer.delete(categoryEntity);		
	}


	@Override
	public List<EXNCategoryBean> findAll() {
		List<EXNCategoryEntity> findAll = (List<EXNCategoryEntity>)categoryDAOlayer.findAll();
		List<EXNCategoryBean> collect = findAll.stream().map(category->entityToBean(category)).collect(Collectors.toList());
		return collect;
	}
	//Utility Methods:
		//utility methods
		public EXNCategoryEntity beanToEntity(EXNCategoryBean bean) {
			EXNCategoryEntity entity= this.modelMapper.map(bean,EXNCategoryEntity.class);
			
			return entity;
		}
		public EXNCategoryBean entityToBean(EXNCategoryEntity entity) {
			EXNCategoryBean bean = this.modelMapper.map(entity, EXNCategoryBean.class);
			return bean;
		}
}
