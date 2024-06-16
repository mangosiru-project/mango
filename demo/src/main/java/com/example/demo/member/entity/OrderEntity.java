package com.example.demo.member.entity;

import com.example.demo.member.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "order_table")


public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String storename;

    @Column(length=50, nullable=false)
    private String memberName;  // 작성자 ID 필드 추가

    @Column(nullable = false)
    private String pickupMethod;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String receiveDate;

    @Column(nullable = false)
    private String receiveTime;

    @Column
    private int fileAttached;//1 or 0

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderFileEntity> orderFileEntityList = new ArrayList<>();  // 필드 이름을 일치시킴



    public static OrderEntity toOrderSaveEntity(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDTO.getId());
        orderEntity.setStorename(orderDTO.getStorename());
        orderEntity.setMemberName(orderDTO.getMemberName());
        orderEntity.setPickupMethod(orderDTO.getPickupMethod());
        orderEntity.setDescription(orderDTO.getDescription());
        orderEntity.setReceiveDate(orderDTO.getReceiveDate());
        orderEntity.setReceiveTime(orderDTO.getReceiveTime());
        orderEntity.setFileAttached(0);
        return orderEntity;
    }
    public static OrderDTO toOrderDTO(OrderEntity orderEntity){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setMemberName(orderEntity.getMemberName());
        orderDTO.setDescription(orderEntity.getDescription());
        orderDTO.setPickupMethod(orderEntity.getPickupMethod());
        orderDTO.setStorename(orderEntity.getStorename());
        orderDTO.setReceiveTime(orderEntity.getReceiveTime());
        orderDTO.setReceiveDate(orderEntity.getReceiveDate());
        orderDTO.setFileAttached(orderEntity.getFileAttached());

        return orderDTO;

    }
    public static OrderEntity toSaveFileEntity(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDTO.getId());
        orderEntity.setMemberName(orderDTO.getMemberName());
        orderEntity.setPickupMethod(orderDTO.getPickupMethod());
        orderEntity.setDescription(orderDTO.getDescription());
        orderEntity.setReceiveDate(orderDTO.getReceiveDate());
        orderEntity.setReceiveTime(orderDTO.getReceiveTime());
        orderEntity.setStorename(orderDTO.getStorename());


        orderEntity.setFileAttached(1);//파일있음.
        return orderEntity;
    }



}
