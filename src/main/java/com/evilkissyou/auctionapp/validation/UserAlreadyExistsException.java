package com.evilkissyou.auctionapp.validation;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistsException extends AuthenticationException {

    public UserAlreadyExistsException(final String msg) {
        super(msg);
    }

}
