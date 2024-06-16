package com.example.demo.member.repository;

import com.example.demo.member.entity.OrderFileEntity;
import com.example.demo.member.entity.ShopFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFileRepository extends JpaRepository<OrderFileEntity, Long> {
}
