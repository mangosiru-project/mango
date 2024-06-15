package com.example.demo.member.controller;

import com.example.demo.member.dto.ShopDTO;
import com.example.demo.member.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if (shopService == null) {
            System.out.println("shopService is null");
        }
        shopService.shopSave(shopDTO);
        addCommonAttributes(model);

        return "ListStore";
    }
    private void addCommonAttributes(Model model) {
        List<ShopDTO> shopDTOList = shopService.findAll();
        if (shopDTOList == null) {
            System.out.println("shopDTOList is null");
        } else {
            System.out.println("shopDTOList size = " + shopDTOList.size());
        }
        model.addAttribute("shopList", shopDTOList);
    }
    @GetMapping("/")
    public String findAll(Model model){
        //DB에서 전체 가게 데이터를 가져와서 list.html에 보여준다.
        addCommonAttributes(model);
        return "ListStore";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
         */
        System.out.println("findById called with id = " + id);
        ShopDTO shopDTO = shopService.findById(id);
        if (shopDTO == null) {
            System.out.println("shopDTO is null");
            return "error";  // 에러 페이지로 리다이렉트하거나 적절한 처리
        }
        System.out.println("shopDTO: " + shopDTO);

        model.addAttribute("shop", shopDTO);  // 모델에 shopDTO 추가

        return "ShopDetail";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        ShopDTO shopDTO = shopService.findById(id);
        model.addAttribute("shopUpdate",shopDTO);
        return "ShopUpdate";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute ShopDTO shopDTO, Model model){
        ShopDTO shop = shopService.update(shopDTO);
        model.addAttribute("shop",shop);
        return "ShopDetail";

    }
}
