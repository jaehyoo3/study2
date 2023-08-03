package com.hodolog.api.request;

import lombok.Data;

@Data
public class Signup {
    private String name;
    private String password;
    private String email;

}
