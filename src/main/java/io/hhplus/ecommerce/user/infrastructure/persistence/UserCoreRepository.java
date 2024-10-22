package io.hhplus.ecommerce.user.infrastructure.persistence;

import io.hhplus.ecommerce.user.domain.model.PointHistory;
import io.hhplus.ecommerce.user.domain.model.User;
import io.hhplus.ecommerce.user.domain.repository.PointHistoryRepository;
import io.hhplus.ecommerce.user.domain.repository.UserRepository;
import io.hhplus.ecommerce.user.infrastructure.persistence.entity.PointHistoryEntity;
import io.hhplus.ecommerce.user.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCoreRepository implements UserRepository, PointHistoryRepository {

    private final UserJpaRepository userJpaRepository;
    private final PointHistoryJpaRepository pointHistoryJpaRepository;

    @Override
    public Optional<User> findById(long userSeq) {
        return userJpaRepository.findById(userSeq).map(UserEntity::toDomain);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(UserEntity.of(user)).toDomain();
    }

    @Override
    public void save(PointHistory pointHistory) {
        pointHistoryJpaRepository.save(PointHistoryEntity.of(pointHistory));
    }
}
