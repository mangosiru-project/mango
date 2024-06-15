package com.example.demo.member.dto;

import com.example.demo.member.entity.ShopEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor//기본생성자
@AllArgsConstructor//모든 필드를 매개변수로 하는 생성자
public class ShopDTO {
    private Long id;
    private String storename;

    //주소
    private String postCode;

    private String streetAdr;

    private String detailAdr;


    private String storeintro;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String shopPass;
    private String memberName;  // 작성자 ID 필드 추가


    public static ShopDTO toShopDTO(ShopEntity shopEntity){
        ShopDTO shopDTO =new ShopDTO();
        shopDTO.setId(shopEntity.getId());
        shopDTO.setStorename(shopEntity.getStorename());
        shopDTO.setStoreintro(shopEntity.getStoreintro());
        shopDTO.setStorename(shopEntity.getStorename());
        shopDTO.setPostCode(shopEntity.getPostCode());
        shopDTO.setStreetAdr(shopEntity.getStreetAdr());
        shopDTO.setDetailAdr(shopEntity.getDetailAdr());
        shopDTO.setCreatedTime(shopEntity.getCreatedTime());
        shopDTO.setUpdatedTime(shopEntity.getUpdatedTime());
        shopDTO.setShopPass(shopEntity.getShopPass());
        shopDTO.setMemberName(shopEntity.getMemberName());
        return shopDTO;
    }


}
