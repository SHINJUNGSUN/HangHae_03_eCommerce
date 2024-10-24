package io.hhplus.ecommerce.user.application.service;

import io.hhplus.ecommerce.user.domain.model.User;

public interface UserService {
    User signUp(String userId, String password, String userName);
    String login(String userId, String password);
}
