package com.streamverse.api.dto.request.auth;

public record LoginRequest (
		
		String userName,
		String password,
		String otp) {

	}
