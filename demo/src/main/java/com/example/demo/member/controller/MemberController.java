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
    public String save(@ModelAttribute MemberDTO memberDTO){
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);

        return "login";
    }
    private void addCommonAttributes(Model model) {
        List<ShopDTO> shopDTOList;
        shopDTOList = shopService.findAll();
        model.addAttribute("shopList", shopDTOList);
    }
    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model){
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult!=null){
            //login 성공
            session.setAttribute("loginName", loginResult.getMemberName());
            addCommonAttributes(model);
            return "ListStore";

        }
        else {
            //login 실패
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
