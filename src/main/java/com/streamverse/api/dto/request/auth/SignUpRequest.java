package com.streamverse.api.dto.request.auth;

import lombok.Data;

@Data
public class SignUpRequest {
	
	private String fullName;
	private String email;
	private String mobileNumber;
	private String dateOfBirth;
	

}
