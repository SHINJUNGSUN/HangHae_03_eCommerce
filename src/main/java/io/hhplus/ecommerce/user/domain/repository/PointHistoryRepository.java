package io.hhplus.ecommerce.user.domain.repository;

import io.hhplus.ecommerce.user.domain.model.PointHistory;

public interface PointHistoryRepository {
    void save(PointHistory pointHistory);
}
