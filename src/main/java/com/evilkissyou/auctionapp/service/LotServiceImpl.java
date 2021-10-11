package com.evilkissyou.auctionapp.service;

import com.evilkissyou.auctionapp.entity.Lot;
import com.evilkissyou.auctionapp.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotServiceImpl implements LotService {

    private final LotRepository lotRepository;

    @Autowired
    public LotServiceImpl(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    @Override
    public List<Lot> findAll() {
        return lotRepository.findAll();
    }

    @Override
    public List<Lot> findByCategoryIdOrderByEndDateAsc(int id) {
        return lotRepository.findByCategoryIdOrderByEndDateAsc(id);
    }

    @Override
    public Lot findById(int id) {
        Optional<Lot> optionalLot = lotRepository.findById(id);
        Lot lot;
        if(optionalLot.isPresent()) {
            lot = optionalLot.get();
        } else {
            throw new RuntimeException("Lot " + id + " could not be found");
        }
        return lot;
    }

    @Override
    public void save(Lot lot) {
        lotRepository.save(lot);
    }

    @Override
    public void deleteById(int id) {
        lotRepository.deleteById(id);
    }
}
