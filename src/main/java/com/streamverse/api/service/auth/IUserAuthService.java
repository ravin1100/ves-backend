package com.streamverse.api.service.auth;

import org.apache.coyote.BadRequestException;

import com.streamverse.api.dto.request.auth.LoginRequest;
import com.streamverse.api.dto.response.user.UserResponse;



public interface IUserAuthService {

	UserResponse authenticateUser(LoginRequest request) throws BadRequestException;
	
}
