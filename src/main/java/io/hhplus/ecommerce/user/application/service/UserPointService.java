package io.hhplus.ecommerce.user.application.service;

import io.hhplus.ecommerce.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.user.application.dto.UserPointResponse;

public interface UserPointService {
    UserPointResponse getPoint(long userSeq);
    UserPointResponse chargePoint(UserPointRequest request);
}
