package com.streamverse.api.service.auth;

import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.streamverse.api.dto.request.auth.LoginRequest;
import com.streamverse.api.dto.response.user.UserResponse;
import com.streamverse.api.model.user.User;
import com.streamverse.api.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements IUserAuthService{
	
	private final IUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	

	@Override
	public UserResponse authenticateUser(LoginRequest request) throws BadRequestException {
		User user = userRepository.findByEmailOrMobileNumberAndIsDeletedFalse(request.email(), request.mobileNumber());
		if(user==null) {
			throw new BadRequestException("Incorrect Email or Mobile Number");
		}
		if(!user.isEnabled()) {
			throw new BadRequestException("Your access has been disabled");		
		}
		if(!user.getPassword().equals(passwordEncoder.encode(request.password()))) {
			throw new BadRequestException("Incorrect Password");
		}
		return new UserResponse(user.getMobileNumber(), user.getEmail(), false, false, null);
	}

}
