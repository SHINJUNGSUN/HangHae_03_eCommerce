package io.hhplus.ecommerce.api.user.infrastructure;

import io.hhplus.ecommerce.api.user.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistory, Long> {
}
