package io.hhplus.ecommerce.user.infrastructure.persistence;

import io.hhplus.ecommerce.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

}
