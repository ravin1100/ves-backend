package com.streamverse.api.controller.auth;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streamverse.api.common.IURLs;
import com.streamverse.api.dto.request.auth.LoginRequest;
import com.streamverse.api.dto.request.auth.SignUpRequest;
import com.streamverse.api.dto.response.user.JwtResponse;
import com.streamverse.api.service.auth.IUserAuthService;

import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = IURLs.USER_API_URL)
public class UserAuthController {
	
	private final IUserAuthService userAuthService;
	
	@PostMapping(value = "/sign-up")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest request) throws BadRequestException{
		userAuthService.signUp(request);
		return ResponseEntity.ok(new String(""));
	}

	@PostMapping(value = "/sign-in")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest request) throws BadRequestException{
		return ResponseEntity.ok(userAuthService.signIn(request));
	}
	
	
	
	
	
}
