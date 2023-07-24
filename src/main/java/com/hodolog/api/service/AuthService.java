package com.hodolog.api.service;

import com.hodolog.api.domain.Session;
import com.hodolog.api.domain.User;
import com.hodolog.api.exception.InvalidSigningInformation;
import com.hodolog.api.repository.UserRepository;
import com.hodolog.api.request.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public String signin(Login login) {
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigningInformation::new);
        Session session = user.addSession();

        return session.getAccessToken();
    }

}
