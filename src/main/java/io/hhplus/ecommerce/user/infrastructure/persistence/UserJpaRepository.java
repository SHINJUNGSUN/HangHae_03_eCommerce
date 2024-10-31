package io.hhplus.ecommerce.user.infrastructure.persistence;

import io.hhplus.ecommerce.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByUserId(String userId);
}
