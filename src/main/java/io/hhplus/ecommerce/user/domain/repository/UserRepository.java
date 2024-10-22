package io.hhplus.ecommerce.user.domain.repository;

import io.hhplus.ecommerce.user.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(long userSeq);
    User save(User user);
}
