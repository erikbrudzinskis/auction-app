package com.evilkissyou.auctionapp.service;

import com.evilkissyou.auctionapp.dto.UserDto;
import com.evilkissyou.auctionapp.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int id);
    void save(User user);
    void deleteById(int id);
    User findByEmail(String email);
    User registerNewUser(UserDto userDto);
}
