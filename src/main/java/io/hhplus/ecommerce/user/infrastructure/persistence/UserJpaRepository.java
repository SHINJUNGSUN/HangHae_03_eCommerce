package io.hhplus.ecommerce.user.infrastructure.persistence;

import io.hhplus.ecommerce.user.infrastructure.persistence.entity.UserEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT u
        FROM UserEntity u
         WHERE u.id = :id
    """)
    Optional<UserEntity> findByIdForUpdate(Long id);
    Optional<UserEntity> findByUserId(String userId);
}
