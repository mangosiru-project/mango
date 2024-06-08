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
    private String storeplace;

    private String storeintro;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public static ShopDTO toShopDTO(ShopEntity shopEntity){
        ShopDTO shopDTO =new ShopDTO();
        shopDTO.setId(shopEntity.getId());
        shopDTO.setStorename(shopEntity.getStorename());
        shopDTO.setStoreintro(shopEntity.getStoreintro());
        shopDTO.setStorename(shopEntity.getStorename());
        shopDTO.setStoreplace(shopEntity.getStoreplace());
        shopDTO.setCreatedTime(shopEntity.getCreatedTime());
        shopDTO.setUpdatedTime(shopEntity.getUpdatedTime());
        return shopDTO;
    }


}
