package com.blog.application.Service.ServiceInterfaces;
import java.util.*;

import com.blog.application.Bean.EXNUserBean;
public interface EXNUserServieInterface {
	EXNUserBean createUser(EXNUserBean userBean);
	EXNUserBean findById(String userID);
	EXNUserBean updateUser(String userID);
	EXNUserBean deleteUser(String userID);
	List<EXNUserBean> findAll();
	
}
