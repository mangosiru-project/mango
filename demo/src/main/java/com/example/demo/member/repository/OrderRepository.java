package com.example.demo.member.repository;

import com.example.demo.member.entity.OrderEntity;
import com.example.demo.member.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    boolean existsByMemberName(String memberName);

    List<OrderEntity> findByMemberName(String memberName);
    List<OrderEntity> findByStorename(String storename);
}
