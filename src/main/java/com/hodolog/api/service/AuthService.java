package com.hodolog.api.service;

import com.hodolog.api.domain.Session;
import com.hodolog.api.domain.User;
import com.hodolog.api.exception.AlreadyExistsEmailException;
import com.hodolog.api.exception.InvalidRequest;
import com.hodolog.api.exception.InvalidSigningInformation;
import com.hodolog.api.repository.UserRepository;
import com.hodolog.api.request.Login;
import com.hodolog.api.request.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public Long signin(Login login) {
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigningInformation::new);

        return user.getId();
    }

    public void signup(Signup signup) {
        Optional<User> userOptional = userRepository.findbyEmail(signup.getEmail());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        var user = User.builder()
                .name(signup.getName())
                .password(signup.getPassword())
                .email(signup.getEmail())
                .build();

        userRepository.save(user);
    }
}
