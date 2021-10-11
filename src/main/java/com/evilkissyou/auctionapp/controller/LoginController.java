package com.evilkissyou.auctionapp.controller;

import com.evilkissyou.auctionapp.dto.UserDto;
import com.evilkissyou.auctionapp.entity.User;
import com.evilkissyou.auctionapp.service.UserService;
import com.evilkissyou.auctionapp.validation.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
    @GetMapping("/sign-up")
    public String showSignUp(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "sign-up";
    }
    @PostMapping("/sign-up")
    public String registerNewAccount(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("error", result.getFieldError());
            return "sign-up";
        }
        try {
            User registered = userService.registerNewUser(user);
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("message", "An account for that username/email already exists.");
        }
        return "redirect:/login";
    }
}
