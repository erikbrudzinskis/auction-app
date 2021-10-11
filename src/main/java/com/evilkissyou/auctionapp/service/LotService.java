package com.evilkissyou.auctionapp.service;

import com.evilkissyou.auctionapp.entity.Lot;

import java.util.List;

public interface LotService {
    List<Lot> findAll();
    List<Lot> findByCategoryIdOrderByEndDateAsc(int id);
    Lot findById(int id);
    void save(Lot lot);
    void deleteById(int id);
}
