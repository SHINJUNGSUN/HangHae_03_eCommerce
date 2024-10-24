package io.hhplus.ecommerce.user.application;

import io.hhplus.ecommerce.user.application.dto.*;
import io.hhplus.ecommerce.user.application.service.UserPointService;
import io.hhplus.ecommerce.user.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final UserPointService userPointService;

    public UserSignUpResponse signUp(UserSignUpRequest request) {
        return UserSignUpResponse.from(userService.signUp(request.userId(), request.password(), request.userName()));
    }

    public UserLoginResponse login(UserLoginRequest request) {
        return UserLoginResponse.of(userService.login(request.userId(), request.password()));
    }

    public UserPointResponse getPoint(long userSeq) {
        return UserPointResponse.from(userPointService.getPoint(userSeq));
    }

    public UserPointResponse chargePoint(long userSeq, UserPointRequest request) {
        return UserPointResponse.from(userPointService.chargePoint(userSeq, request.amount()));
    }
}
