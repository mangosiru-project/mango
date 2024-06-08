package com.example.demo.member.controller;

import com.example.demo.member.dto.ShopDTO;
import com.example.demo.member.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    public String shopSave(@ModelAttribute ShopDTO shopDTO, Model model){
        System.out.println("shopDTO = " + shopDTO);
        shopService.shopSave(shopDTO);
        addCommonAttributes(model);

        return "ListStore";
    }
    private void addCommonAttributes(Model model) {
        List<ShopDTO> shopDTOList = shopService.findAll();
        model.addAttribute("shopList", shopDTOList);
    }
    @GetMapping("/")
    public String findAll(Model model){
        //DB에서 전체 가게 데이터를 가져와서 list.html에 보여준다.
        addCommonAttributes(model);
        return "ListStore";
    }

}
