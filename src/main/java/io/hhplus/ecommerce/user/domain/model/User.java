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

    public static User create() {
        return User.builder().build();
    }
}
