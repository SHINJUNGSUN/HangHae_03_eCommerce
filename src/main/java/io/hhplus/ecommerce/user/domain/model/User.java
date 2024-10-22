package io.hhplus.ecommerce.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
