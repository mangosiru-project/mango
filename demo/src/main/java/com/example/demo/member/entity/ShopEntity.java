package com.example.demo.member.entity;

//DB의 테이블 역할을 하는 클래스

import com.example.demo.member.dto.ShopDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="shop_table")
public class ShopEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_incremert
    private Long id;

    @Column(length=20, nullable=false)
    private String storename;
    @Column
    private String storeplace;
    @Column
    private String storeintro;
    @Column
    private LocalDateTime createdTime;
    @Column
    private LocalDateTime updatedTime;

    public static ShopEntity toShopSaveEntity(ShopDTO shopDTO){
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(shopDTO.getId());
        shopEntity.setStorename(shopDTO.getStorename());
        shopEntity.setStoreplace(shopDTO.getStoreplace());
        shopEntity.setStoreintro(shopDTO.getStoreintro());
        shopEntity.setCreatedTime(shopDTO.getCreatedTime());
        shopEntity.setUpdatedTime(shopDTO.getUpdatedTime());
        return shopEntity;
    }



}