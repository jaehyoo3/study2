package com.hodolog.api.controller;

import com.hodolog.api.domain.User;
import com.hodolog.api.exception.InvalidRequest;
import com.hodolog.api.exception.InvalidSigningInformation;
import com.hodolog.api.repository.UserRepository;
import com.hodolog.api.request.Login;
import com.hodolog.api.response.SessionResponse;
import com.hodolog.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login) {
        String accessToken = authService.signin(login);

        return new SessionResponse(accessToken);
    }
}
