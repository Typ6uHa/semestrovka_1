package ru.kpfu.itis.exception;

import org.springframework.security.authentication.AccountStatusException;


public class AccountAlreadyExistExeption extends AccountStatusException {


    public AccountAlreadyExistExeption(String msg) {
        super(msg);
    }
}
