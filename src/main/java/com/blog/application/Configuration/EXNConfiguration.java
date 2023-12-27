package com.blog.application.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.blog.application.EXNEntity.EXNPostsEntity;
import com.blog.application.Security.UserConfig.EXNUserDetailsService;

@Configuration
public class EXNConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public EXNPostsEntity geteXNPostsEntity() {
		return new EXNPostsEntity();
	}
	
	

}
