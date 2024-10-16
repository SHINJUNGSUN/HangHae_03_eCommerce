package io.hhplus.ecommerce.api.user.domain;

public interface UserRepository {
    User findByUserId(long userId);
    User save(User user);
}
