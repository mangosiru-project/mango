package com.example.demo.member.service;
import com.example.demo.member.dto.ShopDTO;
import com.example.demo.member.entity.ShopEntity;
import com.example.demo.member.entity.ShopFileEntity;
import com.example.demo.member.repository.ShopFileRepository;
import com.example.demo.member.repository.ShopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ShopService {
    private final ShopRepository shopRepository;
    private final ShopFileRepository shopFileRepository;

    @Transactional
    public ShopDTO update(ShopDTO shopDTO) {
        ShopEntity shopEntity=ShopEntity.toupdateEntity(shopDTO);
        shopRepository.save(shopEntity);
        return findById(shopDTO.getId());
    }


    public void shopSave(ShopDTO shopDTO) throws IOException {
        //파일첨부 여부에 따라 로직 분리
        if(shopDTO.getShopFile().isEmpty()){
            ShopEntity shopEntity = ShopEntity.toShopSaveEntity(shopDTO);
            shopRepository.save(shopEntity);
        }
        else{
            //첨부파일 있음
            /*
             1. DTO에 담긴 파일을 꺼냄
             2. 파일의 이름 가져옴
             3.서버 저장용 이름을 만듦
             //내사진.jpg=>87836769_내사진.jpg
             4.저장경로 설정
             5. 해당 경로에 파일 저장
             6. shop_table에 해당데이터 save처리
             7. shop_file_table에 해당 데이터 save처리
             */
            ShopEntity shopEntity=ShopEntity.toSaveFileEntity(shopDTO);
            Long saveId = shopRepository.save(shopEntity).getId();
            ShopEntity shop = shopRepository.findById(saveId).get();
            for(MultipartFile shopFile: shopDTO.getShopFile()) {
                //MultipartFile shopFile=shopDTO.getShopFile();//1.
                String originalFilename = shopFile.getOriginalFilename();//2
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                String savePath = "C:/springboot_img/" + storedFileName; // 4. C:/springboot_img/0897967_내사진
                shopFile.transferTo(new File(savePath));//5.

                ShopFileEntity shopFileEntity = ShopFileEntity.toShopFileEntity(shop, originalFilename, storedFileName);
                shopFileRepository.save(shopFileEntity);
            }
        }

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
    public boolean hasShop(String memberName) {
        return shopRepository.existsByMemberName(memberName);
    }

    public ShopDTO findByMemberName(String memberName) {
        ShopEntity shopEntity = shopRepository.findByMemberName(memberName);
        return shopEntity != null ? ShopEntity.toShopDTO(shopEntity) : null;
    }

    @Transactional
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