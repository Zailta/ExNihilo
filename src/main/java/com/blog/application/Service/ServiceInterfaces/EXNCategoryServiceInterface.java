package com.blog.application.Service.ServiceInterfaces;

import java.util.List;

import com.blog.application.Bean.EXNCategoryBean;


public interface EXNCategoryServiceInterface {
	EXNCategoryBean createUser(EXNCategoryBean categoryBean);
	EXNCategoryBean findById(String categoryID);
	EXNCategoryBean updateUser(String categoryID, EXNCategoryBean categoryBean);
	void deleteUser(String categoryID);
	List<EXNCategoryBean> findAll();
}
