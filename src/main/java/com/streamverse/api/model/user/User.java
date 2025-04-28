package com.streamverse.api.model.user;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, updatable = false)
	private String uid = UUID.randomUUID().toString();
	
	@Column(unique = true)
	private String mobileNumber;
	
	@Column(unique = true)
	@Email(message = "Invalid Email Format")
	private String email;
	
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserRole> userRoles;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isEmailVerified;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isMobileNumberVerified;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isEnabled;
	
}
