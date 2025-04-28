package com.streamverse.api.dto.request.auth;

public record LoginRequest (
		
		String email,
		String mobileNumber,
		String password,
		String otp) {

	}
