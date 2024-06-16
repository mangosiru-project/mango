package com.example.demo.member.controller;

import com.example.demo.member.dto.OrderDTO;
import com.example.demo.member.dto.ShopDTO;
import com.example.demo.member.service.OrderService;
import com.example.demo.member.service.ShopService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final ShopService shopService;
    private void addCommonAttributes(Model model) {
        List<ShopDTO> shopDTOList;
        shopDTOList = shopService.findAll();
        if (shopDTOList == null) {
            System.out.println("shopDTOList is null");
        } else {
            System.out.println("shopDTOList size = " + shopDTOList.size());
        }
        model.addAttribute("shopList", shopDTOList);
    }


    @GetMapping("/save/new")
    public String orderSaveForm(@RequestParam("storename") String storename, Model model){
        model.addAttribute("storename", storename);
        return "order";
    }

    @PostMapping("/save")
    public String shopSave(@ModelAttribute OrderDTO orderDTO, HttpSession session, Model model) throws IOException {

        String memberName = (String) session.getAttribute("loginName");  // 로그인된 사용자의 ID 가져오기
        orderDTO.setMemberName(memberName);  // 작성자 ID 설정
        orderService.orderSave(orderDTO);
        addCommonAttributes(model);

        return "orderCheck";
    }

}
