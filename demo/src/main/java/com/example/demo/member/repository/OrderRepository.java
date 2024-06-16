package com.example.demo.member.repository;

import com.example.demo.member.entity.OrderEntity;
import com.example.demo.member.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    boolean existsByMemberName(String memberName);
    ShopEntity findByMemberName(String memberName);
}
