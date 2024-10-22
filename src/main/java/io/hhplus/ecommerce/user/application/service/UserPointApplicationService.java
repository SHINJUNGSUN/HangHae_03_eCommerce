package io.hhplus.ecommerce.user.application.service;

import io.hhplus.ecommerce.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.user.application.dto.UserPointResponse;
import io.hhplus.ecommerce.user.domain.exception.UserException;
import io.hhplus.ecommerce.user.domain.exception.UserExceptionType;
import io.hhplus.ecommerce.user.domain.model.PointHistory;
import io.hhplus.ecommerce.user.domain.model.User;
import io.hhplus.ecommerce.user.domain.repository.PointHistoryRepository;
import io.hhplus.ecommerce.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPointApplicationService implements UserPointService {

    private final UserRepository userRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Override
    public UserPointResponse getPoint(long userSeq) {
        return UserPointResponse.from(
                userRepository.findById(userSeq)
                        .orElseThrow(() -> new UserException(UserExceptionType.USER_NOT_FOUND)));
    }

    @Override
    public UserPointResponse chargePoint(UserPointRequest request) {

        User user = userRepository.findById(request.userSeq())
                .orElseThrow(() -> new UserException(UserExceptionType.USER_NOT_FOUND));

        user.getUserPoint().usePoint(request.amount());

        pointHistoryRepository.save(PointHistory.createChargeHistory(request.userSeq(), request.amount()));

        return UserPointResponse.from(userRepository.save(user));
    }
}
