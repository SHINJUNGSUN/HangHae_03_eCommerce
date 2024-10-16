package io.hhplus.ecommerce.api.user.infrastructure;

import io.hhplus.ecommerce.api.user.domain.User;
import io.hhplus.ecommerce.api.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserCoreRepository implements UserRepository {

    @Override
    public Optional<User> findByUserId(long userId) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }
}
