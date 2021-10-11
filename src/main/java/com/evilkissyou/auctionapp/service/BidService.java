package com.evilkissyou.auctionapp.service;

import com.evilkissyou.auctionapp.entity.Bid;

import java.util.List;

public interface BidService {
    List<Bid> findAll();
    Bid findById(int id);
    void save(Bid bid);
    void deleteById(int id);
}

