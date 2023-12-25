package com.blog.application.Service.ServiceInterfaces;
import java.util.*;

import com.blog.application.Bean.EXNUserBean;
import com.blog.application.EXNEntity.EXNUserEntity;
public interface EXNUserServieInterface {
	EXNUserBean createUser(EXNUserBean userBean);
	EXNUserBean findById(String userID);
	EXNUserBean updateUser(String userID, EXNUserBean exnUserBean);
	void deleteUser(String userID);
	List<EXNUserBean> findAll();
	EXNUserBean loadUserByUserName(String userName);
	
}
