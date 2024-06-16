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
    @GetMapping("/orderStatus")
    public String ordersStatus(HttpSession session, Model model) {
        String memberName = (String) session.getAttribute("loginName");
        ShopDTO shopDTO = shopService.findByMemberName(memberName);
        if (shopDTO != null) {
            String storename = shopDTO.getStorename();
            List<OrderDTO> orders = orderService.findOrdersByStorename(storename);
            orders.forEach(order -> System.out.println("Order: " + order.getStorename() + ", File Attached: " + order.getFileAttached()));
            model.addAttribute("orders", orders);
        }
        return "OrderStatus"; // 주문 현황을 보여줄 HTML 파일명
    }

    @GetMapping("/save/new")
    public String orderSaveForm(@RequestParam("storename") String storename, Model model){
        model.addAttribute("storename", storename);
        return "order";
    }

    @PostMapping("/save")
    public String shopSave(@ModelAttribute OrderDTO orderDTO, HttpSession session, Model model) throws IOException {

        String memberName = (String) session.getAttribute("loginName");
        orderDTO.setMemberName(memberName);
        OrderDTO savedOrder = orderService.orderSave(orderDTO); // 저장된 주문 정보 반환
        session.setAttribute("recentOrder", savedOrder); // 최근 주문 저장
        return "redirect:/order/orderCheck";
    }
    @GetMapping("/orderCheck")
    public String orderCheck(Model model, HttpSession session) {
        String memberName = (String) session.getAttribute("loginName");
        List<OrderDTO> orders = orderService.findOrdersByMemberName(memberName);

        OrderDTO recentOrder = (OrderDTO) session.getAttribute("recentOrder");
        model.addAttribute("recentOrder", recentOrder);

        model.addAttribute("orders", orders);



        return "orderCheck";
    }


}
