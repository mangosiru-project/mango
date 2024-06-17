package com.example.demo.member.controller;

import com.example.demo.member.dto.BoardDTO;
import com.example.demo.member.dto.ShopDTO;
import com.example.demo.member.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/save")
    public String boardSaveForm(){
        return "post";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "community";
    }

    private void addCommonAttributes(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        if (boardDTOList == null) {
            System.out.println("boardDTOList is null");
        } else {
            System.out.println("boardDTOList size = " + boardDTOList.size());
        }
        model.addAttribute("boardList", boardDTOList);
    }

    @GetMapping("/")
    public String findAll(Model model){
        addCommonAttributes(model);
        return "community";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "boardDetail";
    }

}


















