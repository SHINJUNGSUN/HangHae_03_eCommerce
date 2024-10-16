package io.hhplus.ecommerce.api.user.domain;

import io.hhplus.ecommerce.api.user.domain.PointHistory;

public interface PointHistoryRepository {
    PointHistory save(PointHistory pointHistory);
}
