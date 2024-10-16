package io.hhplus.ecommerce.api.user.domain;

import io.hhplus.ecommerce.api.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserId(long userId);
    User save(User user);
}
