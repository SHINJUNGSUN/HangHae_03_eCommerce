package io.hhplus.ecommerce.api.user.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserId(String userId);
}
