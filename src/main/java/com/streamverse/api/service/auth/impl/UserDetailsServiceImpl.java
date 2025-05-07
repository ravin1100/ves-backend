package com.streamverse.api.service.auth.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.streamverse.api.model.user.User;
import com.streamverse.api.repository.IUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmailAndIsEnabledTrueAndIsDeletedFalse(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		return CustomUserDetails.build(user);
	}

}
