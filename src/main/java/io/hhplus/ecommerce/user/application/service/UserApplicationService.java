package io.hhplus.ecommerce.user.application.service;

import io.hhplus.ecommerce.common.exception.ExceptionMessage;
import io.hhplus.ecommerce.common.util.JwtUtil;
import io.hhplus.ecommerce.common.util.PasswordEncoder;
import io.hhplus.ecommerce.user.domain.model.User;
import io.hhplus.ecommerce.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public User signUp(String userId, String password, String userName) {

        if(userRepository.findByUserId(userId).isPresent()) {
            throw new IllegalStateException(ExceptionMessage.ALREADY_SIGNED_UP.getMessage());
        }

        return userRepository.save(User.signUp(userId, passwordEncoder.encode(password), userName));
    }

    @Override
    public String login(String userId, String password) {

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        if (!passwordEncoder.isMatch(password, user.getPassword())) {
            throw new IllegalStateException(ExceptionMessage.USER_NOT_FOUND.getMessage());
        }

        return jwtUtil.generateToken(user.getUserSeq());
    }
}
