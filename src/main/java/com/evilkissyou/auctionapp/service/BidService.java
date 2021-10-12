package com.evilkissyou.auctionapp.service;

import com.evilkissyou.auctionapp.entity.Bid;
import com.evilkissyou.auctionapp.entity.Lot;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BidService {
    List<Bid> findAll();
    Bid findById(int id);
    void save(Bid bid);
    void deleteById(int id);
    void addNewBid(Bid bid, String email);
    List<Bid> findByLotOrderByCreatedAtDesc(Lot lot);
}

