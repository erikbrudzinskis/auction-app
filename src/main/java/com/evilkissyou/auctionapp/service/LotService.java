package com.evilkissyou.auctionapp.service;

import com.evilkissyou.auctionapp.entity.Lot;
import com.evilkissyou.auctionapp.entity.User;

import java.util.List;

public interface LotService {
    List<Lot> findAll();
    List<Lot> findByCategoryIdOrderByEndDateAsc(int id);
    List<Lot> findAllByUser(User user);
    Lot findById(int id);
    void save(Lot lot);
    void deleteById(int id);
    List<Lot> findByKeyword(String keyword);
}
