package io.hhplus.ecommerce.api.user.application;

import io.hhplus.ecommerce.api.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.api.user.application.dto.UserPointResponse;
import io.hhplus.ecommerce.api.user.domain.PointHistory;
import io.hhplus.ecommerce.api.user.domain.PointHistoryRepository;
import io.hhplus.ecommerce.api.user.domain.User;
import io.hhplus.ecommerce.api.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserUseCase {

    private final UserRepository userRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Override
    public UserPointResponse point(long userId) {

        return UserPointResponse.from(userRepository.findByUserId(userId));
    }

    @Override
    public UserPointResponse chargePoint(UserPointRequest request) {

        User user = userRepository.findByUserId(request.userId());

        user.chargePoint(request.amount());

        pointHistoryRepository.save(PointHistory.createChargeHistory(request.userId(), request.amount()));

        return UserPointResponse.from(userRepository.save(user));
    }

    @Override
    public UserPointResponse usePoint(UserPointRequest request) {

        User user = userRepository.findByUserId(request.userId());

        user.usePoint(request.amount());

        pointHistoryRepository.save(PointHistory.createUseHistory(request.userId(), request.amount()));

        return UserPointResponse.from(userRepository.save(user));
    }
}
