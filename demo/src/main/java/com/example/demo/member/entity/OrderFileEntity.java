package com.example.demo.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="order_file_table")
public class OrderFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public static OrderFileEntity toOrderFileEntity(OrderEntity orderEntity, String originalFileName, String storedFileName)
    {
        OrderFileEntity orderFileEntity = new OrderFileEntity();
        orderFileEntity.setOriginalFileName(originalFileName);
        orderFileEntity.setStoredFileName(storedFileName);
        orderFileEntity.setOrderEntity(orderEntity);
        return orderFileEntity;

    }


}
