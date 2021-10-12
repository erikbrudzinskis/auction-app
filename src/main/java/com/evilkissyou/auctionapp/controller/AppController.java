package com.evilkissyou.auctionapp.controller;

import com.evilkissyou.auctionapp.entity.Bid;
import com.evilkissyou.auctionapp.entity.Lot;
import com.evilkissyou.auctionapp.entity.User;
import com.evilkissyou.auctionapp.service.BidService;
import com.evilkissyou.auctionapp.service.CategoryService;
import com.evilkissyou.auctionapp.service.LotService;
import com.evilkissyou.auctionapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Controller
public class AppController {
    private final UserService userService;
    private final BidService bidService;
    private final LotService lotService;
    private final CategoryService categoryService;

    @Autowired
    public AppController(UserService userService, BidService bidService, LotService lotService, CategoryService categoryService) {
        this.userService = userService;
        this.bidService = bidService;
        this.lotService = lotService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/")
    public String showIndex(Model model) {
        List<Lot> lots = lotService.findAll();
        lots.removeIf(Lot::isFinished);
        Comparator<Lot> byTillEnds =
                Comparator.comparingLong((Lot lot) -> Duration.between(LocalDateTime.now(), lot.getEndDate()).toSeconds());
        lots.sort(byTillEnds);
        model.addAttribute("lots", lots);
        model.addAttribute("title", "Main page");
        return "index";
    }

    @GetMapping("/category")
    public String showCategory(@RequestParam int id, Model model) {
        List<Lot> lots = lotService.findByCategoryIdOrderByEndDateAsc(id);
        model.addAttribute("lots", lots);
        model.addAttribute("title", "Category " + id);
        model.addAttribute("title", categoryService.findById(id).getName());
        return "index";
    }

    @GetMapping("/add-lot")
    public String showAddLot(Model model, Authentication authentication) {
        Lot lot = new Lot();
        model.addAttribute("userId", userService.findByEmail(authentication.getName()).getId());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("lot", lot);
        model.addAttribute("title", "Add lot");
        return "add-lot";
    }

    @PostMapping("/add-lot")
    public String saveAddLot(@ModelAttribute Lot lot, @RequestParam(value = "image", required = false) MultipartFile image, Authentication authentication) throws IOException {
        if(!image.getOriginalFilename().equals("")) {
            UUID uuid = UUID.randomUUID();
            String path = "/uploads/";
            Path fileNameAndPath = Paths.get(path + uuid + "." + image.getOriginalFilename().split("\\.")[1]);
            Files.write(fileNameAndPath, image.getBytes());
            lot.setImageUrl(uuid + "." + image.getOriginalFilename().split("\\.")[1]);
        } else {
            lot.setImageUrl("/img/no_image.jpg");
        }
        User user = userService.findByEmail(authentication.getName());
        lot.setUser(user);
        lotService.save(lot);
        return "redirect:/lot?id=" + lot.getId();
    }

    @GetMapping("/lot")
    public String showLot(@RequestParam("id") int id, Model model) {
        // Find the lot
        Lot lot = lotService.findById( id);
        model.addAttribute("lot", lot);
        // Find all bids for the lot
        List<Bid> bids = bidService.findByLotOrderByCreatedAtDesc(lot);
        model.addAttribute("bids", bids);
        // Create new bid so that the user can bid on the lot
        Bid bid = new Bid();
        model.addAttribute("bid", bid);
        model.addAttribute("title", "Lot number: " + lot.getId());
        return "lot";
    }

    @PostMapping("/add-bid")
    public String addBid(@ModelAttribute Bid bid, Authentication authentication) {
        bidService.addNewBid(bid, authentication.getName());
        return "redirect:/lot?id=" + bid.getLot().getId();
    }

    @RequestMapping("/search")
    public String search(Model model, String keyword) {
        List<Lot> lots = lotService.findByKeyword(keyword);
        lots.removeIf(Lot::isFinished);
        Comparator<Lot> byTillEnds =
                Comparator.comparingLong((Lot lot) -> Duration.between(LocalDateTime.now(), lot.getEndDate()).toSeconds());
        lots.sort(byTillEnds);
        model.addAttribute("lots", lots);
        model.addAttribute("title", "Search Result");
        return "index";
    }
}
