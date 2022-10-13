package ua.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.my.model.dto.UserDto;
import ua.my.security.jwt.JwtTokenProvider;
import ua.my.service.UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/login")
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody UserDto userDto) {
        try {
            String username = userDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userDto.getPassword()));
            UserDto user = userService.findUserByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found.");
            }

            String token = jwtTokenProvider.createToken(username, Collections.singletonList(user.getUserRole()));

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
