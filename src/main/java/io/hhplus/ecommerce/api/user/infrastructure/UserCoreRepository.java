package io.hhplus.ecommerce.api.user.infrastructure;

import io.hhplus.ecommerce.api.user.domain.PointHistory;
import io.hhplus.ecommerce.api.user.domain.PointHistoryRepository;
import io.hhplus.ecommerce.api.user.domain.User;
import io.hhplus.ecommerce.api.user.domain.UserRepository;
import io.hhplus.ecommerce.common.enums.UserError;
import io.hhplus.ecommerce.common.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCoreRepository implements UserRepository, PointHistoryRepository {

    private final UserJpaRepository userJpaRepository;
    private final PointHistoryJpaRepository pointHistoryJpaRepository;

    @Override
    public User findByUserId(long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public void save(PointHistory pointHistory) {
        pointHistoryJpaRepository.save(pointHistory);
    }
}
