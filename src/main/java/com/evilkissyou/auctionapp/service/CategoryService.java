package com.evilkissyou.auctionapp.service;

import com.evilkissyou.auctionapp.entity.Bid;
import com.evilkissyou.auctionapp.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    void save(Category category);
    void deleteById(int id);
}
