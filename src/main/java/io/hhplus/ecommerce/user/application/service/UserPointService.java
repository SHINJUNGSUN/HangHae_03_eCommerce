package io.hhplus.ecommerce.user.application.service;

import io.hhplus.ecommerce.user.domain.model.User;

public interface UserPointService {
    User getPoint(long userSeq);
    User chargePoint(long userSeq, long amount);
    User usePoint(long userSeq, long amount);
}
