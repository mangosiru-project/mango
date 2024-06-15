package com.example.demo.member.repository;

import com.example.demo.member.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

    boolean existsByMemberName(String memberName);
    ShopEntity findByMemberName(String memberName);

}
