package io.hhplus.ecommerce.user.infrastructure.persistence;

import io.hhplus.ecommerce.user.infrastructure.persistence.entity.UserEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByUserId(String userId);
}
