package io.hhplus.ecommerce.api.user.domain.service;

import io.hhplus.ecommerce.api.user.domain.model.PointHistory;
import io.hhplus.ecommerce.api.user.domain.model.User;
import io.hhplus.ecommerce.api.user.domain.repository.PointHistoryRepository;
import io.hhplus.ecommerce.api.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainService implements UserService {

    private final UserRepository userRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Override
    public User getPoint(long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User chargePoint(long userId, long amount) {

        User user = userRepository.findByUserId(userId);

        user.chargePoint(amount);

        pointHistoryRepository.save(PointHistory.createChargeHistory(userId, amount));

        return userRepository.save(user);
    }

    @Override
    public User usePoint(long userId, long amount) {

        User user = userRepository.findByUserId(userId);

        user.usePoint(amount);

        pointHistoryRepository.save(PointHistory.createUseHistory(userId, amount));

        return userRepository.save(user);
    }
}
