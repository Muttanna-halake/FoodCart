package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository; // your repo

@Controller
public class RegisterController {

    private final UserRepository userRepo;

    public RegisterController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("userRegistration", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("userRegistration") User user, Model model) {

        userRepo.save(user); // save the data to database

        return "redirect:/login"; // after success, go to login page
    }
}
