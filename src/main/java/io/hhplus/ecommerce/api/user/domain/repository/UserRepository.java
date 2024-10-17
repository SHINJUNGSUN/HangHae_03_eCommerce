package io.hhplus.ecommerce.api.user.domain.repository;

import io.hhplus.ecommerce.api.user.domain.model.User;

public interface UserRepository {
    User findByUserId(long userId);
    User save(User user);
}
