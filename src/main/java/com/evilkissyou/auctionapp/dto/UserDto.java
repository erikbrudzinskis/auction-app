package com.evilkissyou.auctionapp.dto;

import com.evilkissyou.auctionapp.validation.FieldMatch;
import com.evilkissyou.auctionapp.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@FieldMatch.List({@FieldMatch(first = "password", second = "matchingPassword", message = "The passwords must match")})
public class UserDto {
    @NotNull(message = "Please enter your name")
    @NotEmpty(message = "Please enter your name")
    private String name;

    @NotNull(message = "Please enter your email")
    @NotEmpty(message = "Please enter your email")
    @ValidEmail
    private String email;

    @NotNull(message = "Please enter your password")
    @NotEmpty(message = "Please enter your password")
    private String password;
    private String matchingPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
