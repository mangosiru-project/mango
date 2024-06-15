package com.example.demo.member.entity;

//DB의 테이블 역할을 하는 클래스

import com.example.demo.member.dto.ShopDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String shopPass;
    @Column
    //주소
    private String postCode;
    @Column
    private String streetAdr;
    @Column
    private String detailAdr;
    @Column
    private String storeintro;
    @Column
    private LocalDateTime createdTime;
    @Column
    private LocalDateTime updatedTime;
    @Column(length=50, nullable=false)
    private String memberName;  // 작성자 ID 필드 추가
    @Column
    private int fileAttached;//1 or 0

    @OneToMany(mappedBy = "shopEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ShopFileEntity> shopFileEntityList = new ArrayList<>();  // 필드 이름을 일치시킴


    public static ShopEntity toShopSaveEntity(ShopDTO shopDTO){
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(shopDTO.getId());
        shopEntity.setStorename(shopDTO.getStorename());
        shopEntity.setPostCode(shopDTO.getPostCode());
        shopEntity.setDetailAdr(shopDTO.getDetailAdr());
        shopEntity.setStreetAdr(shopDTO.getStreetAdr());
        shopEntity.setStoreintro(shopDTO.getStoreintro());
        shopEntity.setCreatedTime(shopDTO.getCreatedTime());
        shopEntity.setUpdatedTime(shopDTO.getUpdatedTime());
        shopEntity.setShopPass(shopDTO.getShopPass());
        shopEntity.setMemberName(shopDTO.getMemberName());  // 작성자 ID 설정
        shopEntity.setFileAttached(0);//파일없음.
        return shopEntity;
    }


    public static ShopEntity toupdateEntity(ShopDTO shopDTO) {
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(shopDTO.getId());
        shopEntity.setId(shopDTO.getId());
        shopEntity.setStorename(shopDTO.getStorename());
        shopEntity.setPostCode(shopDTO.getPostCode());
        shopEntity.setDetailAdr(shopDTO.getDetailAdr());
        shopEntity.setStreetAdr(shopDTO.getStreetAdr());
        shopEntity.setStoreintro(shopDTO.getStoreintro());
        shopEntity.setCreatedTime(shopDTO.getCreatedTime());
        shopEntity.setUpdatedTime(shopDTO.getUpdatedTime());
        shopEntity.setShopPass(shopDTO.getShopPass());
        shopEntity.setMemberName(shopDTO.getMemberName());  // 작성자 ID 설정

        return shopEntity;
    }
    public static ShopDTO toShopDTO(ShopEntity shopEntity) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shopEntity.getId());
        shopDTO.setStorename(shopEntity.getStorename());
        shopDTO.setPostCode(shopEntity.getPostCode());
        shopDTO.setDetailAdr(shopEntity.getDetailAdr());
        shopDTO.setStreetAdr(shopEntity.getStreetAdr());
        shopDTO.setStoreintro(shopEntity.getStoreintro());
        shopDTO.setCreatedTime(shopEntity.getCreatedTime());
        shopDTO.setUpdatedTime(shopEntity.getUpdatedTime());
        shopDTO.setShopPass(shopEntity.getShopPass());
        shopDTO.setMemberName(shopEntity.getMemberName());  // 작성자 ID 설정

        return shopDTO;
    }

    public static ShopEntity toSaveFileEntity(ShopDTO shopDTO) {
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(shopDTO.getId());
        shopEntity.setStorename(shopDTO.getStorename());
        shopEntity.setPostCode(shopDTO.getPostCode());
        shopEntity.setDetailAdr(shopDTO.getDetailAdr());
        shopEntity.setStreetAdr(shopDTO.getStreetAdr());
        shopEntity.setStoreintro(shopDTO.getStoreintro());
        shopEntity.setCreatedTime(shopDTO.getCreatedTime());
        shopEntity.setUpdatedTime(shopDTO.getUpdatedTime());
        shopEntity.setShopPass(shopDTO.getShopPass());
        shopEntity.setMemberName(shopDTO.getMemberName());  // 작성자 ID 설정
        shopEntity.setFileAttached(1);//파일있음.
        return shopEntity;
    }
}