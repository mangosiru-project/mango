package com.example.demo.member.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor//기본생성자
@AllArgsConstructor//모든 필드를 매개변수로 하는 생성자
public class ShopDTO {
    private String storename;
    private String storeplace;

    private String storeintro;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;


}
