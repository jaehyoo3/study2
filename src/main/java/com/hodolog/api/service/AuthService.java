package com.hodolog.api.service;

import com.hodolog.api.crypto.PasswordEncoder;
import com.hodolog.api.domain.Session;
import com.hodolog.api.domain.User;
import com.hodolog.api.exception.AlreadyExistsEmailException;
import com.hodolog.api.exception.InvalidRequest;
import com.hodolog.api.exception.InvalidSigningInformation;
import com.hodolog.api.repository.UserRepository;
import com.hodolog.api.request.Login;
import com.hodolog.api.request.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public Long signin(Login login) {
        User user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(InvalidSigningInformation::new);

        PasswordEncoder encoder = new PasswordEncoder();

        if(!encoder.matches(login.getPassword(), user.getPassword())) {
            throw new InvalidSigningInformation();
        }

        return user.getId();
    }

    public void signup(Signup signup) {
        Optional<User> userOptional = userRepository.findByEmail(signup.getEmail());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        PasswordEncoder encoder = new PasswordEncoder();

        String encryptedPassword = encoder.encrpyt(signup.getPassword());

        var user = User.builder()
                .name(signup.getName())
                .password(encryptedPassword)
                .email(signup.getEmail())
                .build();

        userRepository.save(user);
    }
}
