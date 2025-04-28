package com.streamverse.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamverse.api.model.user.User;


public interface IUserRepository extends JpaRepository<User, Long> {

	User findByEmailOrMobileNumberAndIsDeletedFalse(String email, String mobileNumber);
	
}
