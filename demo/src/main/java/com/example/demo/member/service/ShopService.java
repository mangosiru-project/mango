package com.example.demo.member.service;
import com.example.demo.member.dto.ShopDTO;
import com.example.demo.member.entity.ShopEntity;
import com.example.demo.member.repository.ShopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ShopService {
    private final ShopRepository shopRepository;

    public ShopDTO update(ShopDTO shopDTO) {
        ShopEntity shopEntity=ShopEntity.toupdateEntity(shopDTO);
        shopRepository.save(shopEntity);
        return findById(shopDTO.getId());
    }


    public void shopSave(ShopDTO shopDTO){
        ShopEntity shopEntity = ShopEntity.toShopSaveEntity(shopDTO);
        shopRepository.save(shopEntity);
    }
    @Transactional
    public List<ShopDTO> findAll() {
        List<ShopEntity> shopEntityList = shopRepository.findAll();
        List<ShopDTO> shopDTOList = new ArrayList<>();
        for(ShopEntity shopEntity: shopEntityList){
            shopDTOList.add(ShopDTO.toShopDTO(shopEntity));
        }
        return shopDTOList;

    }



    public ShopDTO findById(Long id) {
        Optional<ShopEntity> optionalShopEntity=shopRepository.findById(id);
        if(optionalShopEntity.isPresent()){
            ShopEntity shopEntity=optionalShopEntity.get();
            ShopDTO shopDTO=ShopDTO.toShopDTO(shopEntity);
            return shopDTO;
        }else{
            return null;
        }

    }

}