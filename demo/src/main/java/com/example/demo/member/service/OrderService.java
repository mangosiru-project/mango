package com.example.demo.member.service;

import com.example.demo.member.dto.OrderDTO;
import com.example.demo.member.entity.OrderEntity;
import com.example.demo.member.entity.OrderFileEntity;
import com.example.demo.member.repository.OrderFileRepository;
import com.example.demo.member.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderFileRepository orderFileRepository;

    public OrderDTO orderSave(OrderDTO orderDTO) throws IOException {
        OrderEntity orderEntity = OrderEntity.toSaveFileEntity(orderDTO);
        if (orderDTO.getOrderFile().isEmpty()) {
            orderRepository.save(orderEntity);
        }
        else{
            Long saveId = orderRepository.save(orderEntity).getId();
            OrderEntity savedOrderEntity = orderRepository.findById(saveId).get();
            List<String> storedFileNames = orderDTO.getOrderFile().stream()
                    .map(orderFile -> {
                        String originalFilename = orderFile.getOriginalFilename();
                        String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                        String savePath = "C:/springboot_img/" + storedFileName;
                        try {
                            orderFile.transferTo(new File(savePath));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        OrderFileEntity orderFileEntity = OrderFileEntity.toOrderFileEntity(savedOrderEntity, originalFilename, storedFileName);
                        orderFileRepository.save(orderFileEntity);
                        return storedFileName;
                    })
                    .collect(Collectors.toList());
            savedOrderEntity.setFileAttached(1);
            orderDTO.setStoredFileName(storedFileNames);
        }
        return OrderEntity.toOrderDTO(orderEntity); // 저장된 주문 정보를 반환
    }

    public List<OrderDTO> findOrdersByMemberName(String memberName) {
        List<OrderEntity> orders = orderRepository.findByMemberName(memberName);
        return orders.stream()
                .map(OrderEntity::toOrderDTO)
                .collect(Collectors.toList());
    }
}
