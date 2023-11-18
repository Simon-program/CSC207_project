package entity;

import java.time.LocalDateTime;

public interface UserFactoryInterface {
    /** Requires: password is valid. */
    User create(String name, String password, LocalDateTime ltd);
}
