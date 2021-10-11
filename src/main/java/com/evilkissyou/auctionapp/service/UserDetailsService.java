package com.evilkissyou.auctionapp.service;

import com.evilkissyou.auctionapp.entity.User;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {
    User findByUserName(String userName);
    void save(User User);
}

