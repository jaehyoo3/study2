package com.hodolog.api.controller;

import com.hodolog.api.config.AppConfig;
import com.hodolog.api.request.Login;
import com.hodolog.api.request.Signup;
import com.hodolog.api.response.SessionResponse;
import com.hodolog.api.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Base64;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final AppConfig appConfig;

    @PostMapping("/login")
    public SessionResponse login(@RequestBody Login login) {
        Long userID = authService.signin(login);

        SecretKey key = Keys.hmacShaKeyFor(appConfig.getJwtKey());

        String jws = Jwts.builder()
                .setSubject(String.valueOf(userID))
                .signWith(key)
                .compact();

        return new SessionResponse(jws);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody Signup signup) {
        authService.signup(signup);
    }
}
