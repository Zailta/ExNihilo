package com.blog.application.Security.UserConfig;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.blog.application.EXNEntity.EXNUserEntity;

public class EXNUserDetails implements  UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EXNUserEntity entity;
	public EXNUserDetails(EXNUserEntity entity) {
		super();
		this.entity = entity;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(entity.getRole());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		
		return entity.getPassword();
	}

	@Override
	public String getUsername() {
		
		return entity.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	

}
