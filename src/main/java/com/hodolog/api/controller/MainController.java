package com.hodolog.api.controller;

import com.hodolog.api.config.UserPrincpal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String main() {
        return "메인입니다.";
    }
    @GetMapping("/user")
    public String user(@AuthenticationPrincipal UserPrincpal userPrincpal) {
        return "사용자페이지" + userPrincpal.getUserId();
    }
    @GetMapping("/admin")
    public String admin() {
        return "어드민 페이지";
    }
}
