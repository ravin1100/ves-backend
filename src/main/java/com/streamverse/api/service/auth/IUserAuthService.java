package com.streamverse.api.service.auth;

import org.apache.coyote.BadRequestException;

import com.streamverse.api.dto.request.auth.LoginRequest;
import com.streamverse.api.dto.request.auth.SignUpRequest;
import com.streamverse.api.dto.response.user.JwtResponse;



public interface IUserAuthService {
	
	void signUp(SignUpRequest request);

	JwtResponse signIn(LoginRequest request) throws BadRequestException;
	
}
