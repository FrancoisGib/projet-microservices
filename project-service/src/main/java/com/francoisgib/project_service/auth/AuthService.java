package com.francoisgib.project_service.auth;

import com.francoisgib.common.UserPrincipal;
import com.francoisgib.project_service.MessageService;
import com.francoisgib.project_service.jwt.JwtService;
import com.francoisgib.project_service.users.UserResourceException;
import com.francoisgib.project_service.users.models.User;
import com.francoisgib.project_service.users.models.UserCreationForm;
import com.francoisgib.project_service.users.services.UserService;
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

    public LoginResponse login(AuthRequest authRequest) throws UserResourceException {
        User user = userService.findByUsername(authRequest.username());
        if (!passwordEncoder.matches(authRequest.password(), user.getPassword())) {
            messageService.sendLogMessage("Wrong credentials for user : " + authRequest.username());
            throw new UserResourceException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        UserPrincipal principal = new UserPrincipal(
                user.getId(),
                user.getOrganization().getId(),
                user.getOrganization().getName());

        String accessToken = jwtService.generateToken(user.getUsername(), principal);
        ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(cookieExpiry)
                .build();
        messageService.sendLogMessage("Logging in user : " + authRequest.username());
        AuthResponse authResponse = new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getOrganization().getId(),
                user.getOrganization().getName());
        return new LoginResponse(cookie, authResponse);
    }

    public User register(UserCreationForm userCreationForm) {
        return userService.createUser(userCreationForm);
    }
}