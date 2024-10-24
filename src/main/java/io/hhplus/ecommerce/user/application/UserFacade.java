package io.hhplus.ecommerce.user.application;

import io.hhplus.ecommerce.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.user.application.dto.UserPointResponse;
import io.hhplus.ecommerce.user.application.service.UserPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserPointService userPointService;

    @Transactional(readOnly = true)
    public UserPointResponse getPoint(long userSeq) {
        return UserPointResponse.from(userPointService.getPoint(userSeq));
    }

    @Transactional
    public UserPointResponse chargePoint(UserPointRequest request) {
        return UserPointResponse.from(userPointService.chargePoint(request.userSeq(), request.amount()));
    }
}
