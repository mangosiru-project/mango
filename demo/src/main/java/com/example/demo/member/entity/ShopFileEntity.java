package com.example.demo.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="shop_file_table")
public class ShopFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    public static ShopFileEntity toShopFileEntity(ShopEntity shopEntity, String originalFileName, String storedFileName)
    {
        ShopFileEntity shopFileEntity = new ShopFileEntity();
        shopFileEntity.setOriginalFileName(originalFileName);
        shopFileEntity.setStoredFileName(storedFileName);
        shopFileEntity.setShopEntity(shopEntity);
        return shopFileEntity;

    }
}
