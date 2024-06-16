package com.example.demo.member.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/community")
    public String community() {
        return "community";
    }
    @GetMapping("/story")
    public String story() {
        return "story";
    }
}