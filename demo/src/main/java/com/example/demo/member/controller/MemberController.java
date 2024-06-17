package com.example.demo.member.controller;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.dto.ShopDTO;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.service.ShopService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;
    private final ShopService shopService;

    //회원가입 페이지 출력요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO, Model model){
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        if (memberService.isMemberNameTaken(memberDTO.getMemberName())) {
            model.addAttribute("errorMessage", "이미 사용 중인 아이디입니다.");
            return "save";

        }
        memberService.save(memberDTO);
        return "redirect:/member/login";
    }
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
    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model){
        try {
            MemberDTO loginResult = memberService.login(memberDTO);
            session.setAttribute("loginName", loginResult.getMemberName());
            return "redirect:/shop"; // 로그인 성공 시 리다이렉트
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
    }
    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        String myName = (String)session.getAttribute("loginName");
        MemberDTO memberDTO= memberService.updateForm(myName);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "ListStore";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }

}
