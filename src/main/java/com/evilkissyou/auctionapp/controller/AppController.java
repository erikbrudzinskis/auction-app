package com.evilkissyou.auctionapp.controller;

import com.evilkissyou.auctionapp.entity.Lot;
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
import java.security.Principal;
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
        model.addAttribute("lots", lots);
        model.addAttribute("title", "Main page");
        return "index";
    }

    @GetMapping("/category")
    public String showCategory(@RequestParam int id, Model model) {
        List<Lot> lots = lotService.findByCategoryIdOrderByEndDateAsc(id);
        model.addAttribute("lots", lots);
        model.addAttribute("title", "Category " + id);
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
    public String saveAddLot(@ModelAttribute Lot lot, @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        if(!image.getOriginalFilename().equals("")) {
            UUID uuid = UUID.randomUUID();
            String path = "/uploads/";
            Path fileNameAndPath = Paths.get(path + uuid + "." + image.getOriginalFilename().split("\\.")[1]);
            Files.write(fileNameAndPath, image.getBytes());
            lot.setImageUrl(uuid + "." + image.getOriginalFilename().split("\\.")[1]);
        } else {
            lot.setImageUrl("/img/no_image.jpg");
        }
        lotService.save(lot);
        return "redirect:";
    }

    @GetMapping("/lot")
    public String showLot(@RequestParam("id") int id, Model model) {
        Lot lot = lotService.findById( id);
        model.addAttribute("lot", lot);
        return "lot";
    }
}
