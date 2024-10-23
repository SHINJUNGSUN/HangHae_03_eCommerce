package io.hhplus.ecommerce.user.application.service;

import io.hhplus.ecommerce.user.domain.exception.UserException;
import io.hhplus.ecommerce.user.domain.exception.UserExceptionType;
import io.hhplus.ecommerce.user.domain.model.PointHistory;
import io.hhplus.ecommerce.user.domain.model.User;
import io.hhplus.ecommerce.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPointApplicationService implements UserPointService {

    private final UserRepository userRepository;

    @Override
    public User getPoint(long userSeq) {
        return userRepository.findById(userSeq)
                        .orElseThrow(() -> new UserException(UserExceptionType.USER_NOT_FOUND));
    }

    @Override
    public User chargePoint(long userSeq, long amount) {

        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserException(UserExceptionType.USER_NOT_FOUND));

        user.getUserPoint().chargePoint(amount);

        userRepository.save(PointHistory.createChargeHistory(userSeq, amount));

        return userRepository.save(user);
    }

    @Override
    public User usePoint(long userSeq, long amount) {

        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserException(UserExceptionType.USER_NOT_FOUND));

        user.getUserPoint().usePoint(amount);

        userRepository.save(PointHistory.createUseHistory(userSeq, amount));

        return userRepository.save(user);
    }
}
