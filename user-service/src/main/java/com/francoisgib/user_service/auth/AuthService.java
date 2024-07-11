package com.francoisgib.user_service.auth;

import com.francoisgib.user_service.MessageService;
import com.francoisgib.user_service.jwt.JwtService;
import com.francoisgib.user_service.users.UserResourceException;
import com.francoisgib.user_service.users.models.User;
import com.francoisgib.user_service.users.models.UserCreationForm;
import com.francoisgib.user_service.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
	private final UserService userService;

	private final JwtService jwtService;

	@Value("${jwt.cookieExpiry}")
	private Long cookieExpiry;

	private final PasswordEncoder passwordEncoder;

	private final MessageService messageService;

	public ResponseCookie login(AuthRequest authRequest) throws UserResourceException {
		User user = userService.getByUsername(authRequest.username());
		if (!passwordEncoder.matches(authRequest.password(), user.getPassword())) {
			messageService.sendLogMessage("Wrong credentials for user : " + authRequest.username());
			throw new UserResourceException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
		}
		String accessToken = jwtService.generateToken(authRequest.username(), user.getOrganizationId());
		ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
			.httpOnly(true)
			.secure(true)
			.path("/")
			.maxAge(cookieExpiry)
			.build();
		messageService.sendLogMessage("Logging in user : " + authRequest.username());
		return cookie;
	}

	public User register(UserCreationForm userCreationForm) {
		return userService.createUser(userCreationForm);
	}
}
