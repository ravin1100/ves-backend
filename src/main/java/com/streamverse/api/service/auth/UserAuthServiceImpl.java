package com.streamverse.api.service.auth;

import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.streamverse.api.dto.request.auth.LoginRequest;
import com.streamverse.api.dto.request.auth.SignUpRequest;
import com.streamverse.api.dto.response.user.JwtResponse;
import com.streamverse.api.model.user.User;
import com.streamverse.api.repository.IUserRepository;
import com.streamverse.api.utility.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements IUserAuthService{
	
	private final IUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenUtil jwtTokenUtil;
	

	@Override
	public JwtResponse signIn(LoginRequest request) throws BadRequestException {
		User user = userRepository.findByEmailOrMobileAndIsDeletedFalse(request.userName())
				.orElseThrow(() -> new BadRequestException("Incorrect Email or Mobile Number"));
		
		if(!user.isEnabled()) {
			throw new BadRequestException("Your access has been disabled");		
		}
		if(!user.getPassword().equals(passwordEncoder.encode(request.password()))) {
			throw new BadRequestException("Incorrect Password");
		}
		
		String role = user.getRole().name();
		String accessToken = jwtTokenUtil.generateToken(request.userName(), Map.ofEntries(Map.entry("role", role)));
		return new JwtResponse(user.getMobileNumber(), user.getEmail(), user.isEmailVerified(), user.isMobileNumberVerified(), role, accessToken);
	
	}


	@Override
	public void signUp(SignUpRequest request) {
	}
	
//	private String generateRefreshToken() {
//		
//	}

}
