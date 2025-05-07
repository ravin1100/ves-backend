package com.streamverse.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamverse.api.model.user.User;


public interface IUserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.email = :userName OR u.mobileNumber = :userName AND isDeleted = false")
	Optional<User> findByEmailOrMobileAndIsDeletedFalse(@Param("userName") String userName);
	
	Optional<User> findByEmailAndIsEnabledTrueAndIsDeletedFalse(String email);

	
}
