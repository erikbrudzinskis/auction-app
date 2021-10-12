package com.evilkissyou.auctionapp.repository;

import com.evilkissyou.auctionapp.entity.Bid;
import com.evilkissyou.auctionapp.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {
    List<Bid> findByLotOrderByCreatedAtDesc(Lot lot);
}
