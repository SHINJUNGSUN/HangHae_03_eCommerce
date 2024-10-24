package io.hhplus.ecommerce.common.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    public String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isMatch(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
