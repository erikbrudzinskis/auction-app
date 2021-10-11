package com.evilkissyou.auctionapp.controller;

import com.evilkissyou.auctionapp.service.BidService;
import com.evilkissyou.auctionapp.service.LotService;
import com.evilkissyou.auctionapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final UserService userService;
    private final BidService bidService;
    private final LotService lotService;

    @Autowired
    public AdminController(UserService userService, BidService bidService, LotService lotService) {
        this.userService = userService;
        this.bidService = bidService;
        this.lotService = lotService;
    }

    @GetMapping("/admin")
    public String showAdmin() {
        return "admin";
    }
}
