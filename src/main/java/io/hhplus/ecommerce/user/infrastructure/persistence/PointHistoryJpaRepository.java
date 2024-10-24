package io.hhplus.ecommerce.user.infrastructure.persistence;

import io.hhplus.ecommerce.user.infrastructure.persistence.entity.PointHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistoryEntity, Long> {

}
