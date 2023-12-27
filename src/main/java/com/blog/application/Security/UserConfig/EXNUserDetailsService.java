package com.blog.application.Security.UserConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blog.application.DataObject.EXNUserDAOLayer;
import com.blog.application.EXNEntity.EXNUserEntity;


public class EXNUserDetailsService implements UserDetailsService{

	@Autowired
	EXNUserDAOLayer exnUserDaoLayer;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 EXNUserEntity findByuserName = exnUserDaoLayer.findByuserName(username);
		if (findByuserName == null) {

			throw new UsernameNotFoundException("The User does not Exist in the DB");
		}
		EXNUserDetails exnUserDetails = new EXNUserDetails(findByuserName);
		
		return exnUserDetails;
	}

}
