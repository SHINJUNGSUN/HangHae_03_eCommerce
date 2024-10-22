package io.hhplus.ecommerce.user.infrastructure.persistence.entity;

import io.hhplus.ecommerce.user.domain.model.User;
import io.hhplus.ecommerce.user.domain.model.UserPoint;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String password;

    private String userName;

    private Long point;

    public static UserEntity of(User user) {
        return UserEntity.builder()
                .id(user.getUserSeq())
                .userId(user.getUserId())
                .password(user.getPassword())
                .userName(user.getUserName())
                .point(user.getUserPoint().getPoint())
                .build();
    }

    public User toDomain() {
        UserPoint userPoint = UserPoint.builder()
                .point(this.point)
                .build();

        return User.builder()
                .userSeq(this.id)
                .userId(this.userId)
                .password(this.password)
                .userName(this.userName)
                .userPoint(userPoint)
                .build();
    }
}
