package com.example.demo.member.controller;

import com.example.demo.member.dto.ShopDTO;
import com.example.demo.member.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;
    @GetMapping("/manage")
    public String manageForm(){
        return "BeforeRegistration";
    }
    @GetMapping("/save")
    public String shopSaveForm(){
        return "Registration";
    }
    @PostMapping("/save")
    public String shopSave(@ModelAttribute ShopDTO shopDTO){
        System.out.println("shopDTO = " + shopDTO);
        shopService.shopSave(shopDTO);

        return "ListStore";
    }

}
