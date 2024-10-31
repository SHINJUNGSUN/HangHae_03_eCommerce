package io.hhplus.ecommerce.user.application.service;

import io.hhplus.ecommerce.common.exception.ExceptionMessage;
import io.hhplus.ecommerce.user.domain.model.PointHistory;
import io.hhplus.ecommerce.user.domain.model.User;
import io.hhplus.ecommerce.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPointApplicationService implements UserPointService {

    private final UserRepository userRepository;

    @Override
    public User getPoint(long userSeq) {
        return userRepository.findById(userSeq)
                        .orElseThrow(() -> new IllegalStateException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    @Override
    @Transactional
    @Retryable(
            retryFor = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 500)
    )
    public User chargePoint(long userSeq, long amount) {

        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new IllegalStateException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        user.getUserPoint().chargePoint(amount);

        userRepository.save(PointHistory.createChargeHistory(userSeq, amount));

        return userRepository.save(user);
    }

    @Override
    @Transactional
    @Retryable(
            retryFor = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 500)
    )
    public User usePoint(long userSeq, long amount) {

        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new IllegalStateException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        user.getUserPoint().usePoint(amount);

        userRepository.save(PointHistory.createUseHistory(userSeq, amount));

        return userRepository.save(user);
    }
}
