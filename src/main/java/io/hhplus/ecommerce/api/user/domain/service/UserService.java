package io.hhplus.ecommerce.api.user.domain.service;

import io.hhplus.ecommerce.api.user.domain.model.User;

public interface UserService {
    User getPoint(long userId);
    User chargePoint(long userId, long amount);
    User usePoint(long userId, long amount);
}
