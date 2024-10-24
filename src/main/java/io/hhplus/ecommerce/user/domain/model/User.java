package io.hhplus.ecommerce.user.domain.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private Long userSeq;

    private String userId;

    private String password;

    private String userName;

    private UserPoint userPoint;

    public static User signUp(String userId, String encodedPassword, String userName) {
        return User.builder()
                .userId(userId)
                .password(encodedPassword)
                .userName(userName)
                .userPoint(UserPoint.of(0L))
                .build();
    }
}
