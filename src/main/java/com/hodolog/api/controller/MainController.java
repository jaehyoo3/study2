package com.hodolog.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String main() {
        return "메인입니다.";
    }
    @GetMapping("/user")
    public String user() {
        return "사용자페이지";
    }
    @GetMapping("/admin")
    public String admin() {
        return "어드민 페이지";
    }
}
