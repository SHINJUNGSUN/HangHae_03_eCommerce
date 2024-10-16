package io.hhplus.ecommerce.api.user.application;

import io.hhplus.ecommerce.api.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.api.user.application.dto.UserPointResponse;

public interface UserUseCase {
    UserPointResponse point(long userId);
    UserPointResponse chargePoint(UserPointRequest request);
    UserPointResponse usePoint(UserPointRequest request);
}
