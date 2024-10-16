package io.hhplus.ecommerce.api.user.application;

import io.hhplus.ecommerce.api.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.api.user.application.dto.UserPointResponse;
import io.hhplus.ecommerce.api.user.domain.UserRepository;
import io.hhplus.ecommerce.common.enums.UserError;
import io.hhplus.ecommerce.common.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserUseCase {

    private final UserRepository userRepository;

    @Override
    public UserPointResponse point(long userId) {

        return UserPointResponse.from(
                userRepository.findByUserId(userId)
                        .orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND)));
    }

    @Override
    public UserPointResponse chargePoint(UserPointRequest request) {
        return null;
    }

    @Override
    public UserPointResponse userPoint(UserPointRequest request) {
        return null;
    }
}
