package com.example.demo.member.service;

import com.example.demo.member.dto.OrderDTO;
import com.example.demo.member.entity.OrderEntity;
import com.example.demo.member.entity.OrderFileEntity;
import com.example.demo.member.entity.ShopEntity;
import com.example.demo.member.entity.ShopFileEntity;
import com.example.demo.member.repository.OrderFileRepository;
import com.example.demo.member.repository.OrderRepository;
import com.example.demo.member.repository.ShopFileRepository;
import com.example.demo.member.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderFileRepository orderFileRepository;

    public void orderSave(OrderDTO orderDTO) throws IOException {

        //파일첨부 여부에 따라 로직 분리
        if(orderDTO.getOrderFile().isEmpty()){
            OrderEntity orderEntity = OrderEntity.toOrderSaveEntity(orderDTO);
            orderRepository.save(orderEntity);
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
            OrderEntity orderEntity =OrderEntity.toSaveFileEntity(orderDTO);
            Long saveId = orderRepository.save(orderEntity).getId();
            OrderEntity order = orderRepository.findById(saveId).get();
            for(MultipartFile orderFile: orderDTO.getOrderFile()) {
                //MultipartFile shopFile=shopDTO.getShopFile();//1.
                String originalFilename = orderFile.getOriginalFilename();//2
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                String savePath = "C:/springboot_img/" + storedFileName; // 4. C:/springboot_img/0897967_내사진
                orderFile.transferTo(new File(savePath));//5.

                OrderFileEntity orderFileEntity = OrderFileEntity.toOrderFileEntity(order, originalFilename, storedFileName);
                orderFileRepository.save(orderFileEntity);
            }
        }
    }
}
