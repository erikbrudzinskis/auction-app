package com.evilkissyou.auctionapp.service;

import com.evilkissyou.auctionapp.entity.Bid;
import com.evilkissyou.auctionapp.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    @Override
    public Bid findById(int id) {
        Optional<Bid> optionalBid = bidRepository.findById(id);
        Bid bid;
        if(optionalBid.isPresent()) {
            bid = optionalBid.get();
        } else {
            throw new RuntimeException("Bid " + id + " could not be found");
        }
        return bid;
    }

    @Override
    public void save(Bid bid) {
        bidRepository.save(bid);
    }

    @Override
    public void deleteById(int id) {
        bidRepository.deleteById(id);
    }
}
