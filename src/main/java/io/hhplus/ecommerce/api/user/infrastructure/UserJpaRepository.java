package io.hhplus.ecommerce.api.user.infrastructure;

import io.hhplus.ecommerce.api.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
