package com.streamverse.api.utility;

import org.springframework.beans.factory.annotation.Value;

public class JwtTokenUtil {
	
	@Value("${app.jwt.expirationInMs}")
	private int jwtExpirationInMS;
	
	
}
