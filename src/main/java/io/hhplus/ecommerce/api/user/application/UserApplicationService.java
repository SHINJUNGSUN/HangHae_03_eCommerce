package io.hhplus.ecommerce.api.user.application;

import io.hhplus.ecommerce.api.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.api.user.application.dto.UserPointResponse;
import io.hhplus.ecommerce.api.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserService userService;

    @Transactional(readOnly = true)
    public UserPointResponse getPoint(long userId) {
        return UserPointResponse.from(userService.getPoint(userId));
    }

    @Transactional
    public UserPointResponse chargePoint(UserPointRequest request) {
        return UserPointResponse.from(userService.chargePoint(request.userId(), request.amount()));
    }
}
