package ru.kpfu.itis.exception;

import org.springframework.security.authentication.AccountStatusException;

public class UserIsNotActivate  extends AccountStatusException {
    public UserIsNotActivate(String msg) {
        super(msg);
    }
}
