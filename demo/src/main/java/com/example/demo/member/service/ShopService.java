package com.example.demo.member.service;
import com.example.demo.member.dto.ShopDTO;
import com.example.demo.member.entity.ShopEntity;
import com.example.demo.member.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    public void shopSave(ShopDTO shopDTO){
        ShopEntity shopEntity = ShopEntity.toShopSaveEntity(shopDTO);
        shopRepository.save(shopEntity);
    }


}