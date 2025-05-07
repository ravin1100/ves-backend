package com.streamverse.api.model.user;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, updatable = false)
	private String uid = UUID.randomUUID().toString();
	
	private String fullName;
	
	@Column(unique = true)
	@Email(message = "Invalid Email Format")
	private String email;
	
	private String mobileNumber;
	
	private String password;
	
	private LocalDate dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isEmailVerified;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isMobileNumberVerified;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isEnabled;

	
}
