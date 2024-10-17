package io.hhplus.ecommerce.api.user.domain.repository;

import io.hhplus.ecommerce.api.user.domain.model.PointHistory;

public interface PointHistoryRepository {
    void save(PointHistory pointHistory);
}
