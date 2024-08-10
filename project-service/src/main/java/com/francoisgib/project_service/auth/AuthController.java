package com.francoisgib.project_service.auth;

import com.francoisgib.project_service.jwt.JwtResponse;
import com.francoisgib.project_service.users.UserMapper;
import com.francoisgib.project_service.users.UserResourceException;
import com.francoisgib.project_service.users.models.UserCreationForm;
import com.francoisgib.project_service.users.models.UserDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class AuthController {
	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) throws UserResourceException {
		ResponseCookie cookie = authService.login(authRequest);
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
		return new ResponseEntity<>(new JwtResponse(cookie.getValue()), HttpStatus.OK);
	}

	@PutMapping("/register")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserCreationForm userCreationForm) {
		return new ResponseEntity<>(UserMapper.INSTANCE.toDTO(authService.register(userCreationForm)), HttpStatus.CREATED);
	}
}
