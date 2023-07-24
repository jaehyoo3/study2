package com.hodolog.api.controller;

import com.hodolog.api.domain.User;
import com.hodolog.api.exception.InvalidRequest;
import com.hodolog.api.exception.InvalidSigningInformation;
import com.hodolog.api.repository.UserRepository;
import com.hodolog.api.request.Login;
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

    private final UserRepository userRepository;
    @PostMapping("/auth/login")
    public void login(@RequestBody Login login) {
        log.info(">>> loging {}", login);
        //json 아이디/비번
        //db 조회
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigningInformation::new);

        //토큰 응답
    }
}
