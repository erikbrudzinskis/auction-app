package com.evilkissyou.auctionapp.service;

import com.evilkissyou.auctionapp.entity.Bid;
import com.evilkissyou.auctionapp.entity.Lot;
import com.evilkissyou.auctionapp.entity.User;
import com.evilkissyou.auctionapp.repository.BidRepository;
import com.evilkissyou.auctionapp.repository.LotRepository;
import com.evilkissyou.auctionapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final UserRepository userRepository;
    private final LotRepository lotRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, UserRepository userRepository, LotRepository lotRepository) {
        this.bidRepository = bidRepository;
        this.userRepository = userRepository;
        this.lotRepository = lotRepository;
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

    @Override
    public void addNewBid(Bid bid, String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user;
        Lot lot;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            bid.setUser(user);
            Optional<Lot> optionalLot = lotRepository.findById(bid.getLot().getId());
            if (optionalLot.isPresent()) {
                lot = optionalLot.get();
                if (bid.getAmount().compareTo(lot.getStartPrice().add(lot.getLotStep())) < 0) {
                    throw new RuntimeException("Could not add a new bid: Bid amount is less than price plus lot step");
                }
                lot.setStartPrice(bid.getAmount());
                lotRepository.save(lot);
                bidRepository.save(bid);
            } else {
                throw new RuntimeException("Could not add a new bid: Could not find the lot");
            }
        } else {
            throw new RuntimeException("Could not add a new bid: Could not find the user");
        }
    }

    @Override
    public List<Bid> findByLotOrderByCreatedAtDesc(Lot lot) {
        return bidRepository.findByLotOrderByCreatedAtDesc(lot);
    }


}
