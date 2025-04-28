package com.streamverse.api.service.auth.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.streamverse.api.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String email;
	private String mobileNumber;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public static UserDetailsImpl build(User user, String userName) {
		return UserDetailsImpl.builder()
				.id(user.getId())
				.username(userName)
				.email(user.getEmail())
				.mobileNumber(user.getMobileNumber())
				.authorities(user.getUserRoles().stream().map(ur -> new SimpleGrantedAuthority(ur.getRole().name())).collect(Collectors.toList()))
				.build();
	};
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

}
