package com.example.demo.member.dto;

import com.example.demo.member.entity.OrderEntity;
import com.example.demo.member.entity.OrderFileEntity;
import com.example.demo.member.entity.ShopEntity;
import com.example.demo.member.entity.ShopFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor//기본생성자
@AllArgsConstructor//모든 필드를 매개변수로 하는 생성자
public class OrderDTO {
    private Long id;
    private String memberName;
    private String storename;
    private String pickupMethod;
    private String description;
    private String receiveDate;
    private String receiveTime;
    private String phoneNumber;
    private List<MultipartFile> orderFile; // 빈 리스트로 초기화;//가게저장.html-> conotroller 파일 담는 용도
    private List<String> originalFilename;//원본 파일 이름
    private List<String> storedFileName;//서버 저장용 파일 이름
    private int fileAttached;//파일 첨부여부(첨부1,미첨부 0)

    public static OrderDTO toOrderDTO(OrderEntity orderEntity){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setDescription(orderDTO.getDescription());
        orderDTO.setMemberName(orderDTO.getMemberName());
        orderDTO.setStorename(orderDTO.getStorename());
        orderDTO.setPickupMethod(orderDTO.getPickupMethod());
        orderDTO.setReceiveDate(orderDTO.getReceiveDate());
        orderDTO.setReceiveTime(orderDTO.getReceiveTime());
        orderDTO.setPhoneNumber(orderDTO.getPhoneNumber());
        if(orderEntity.getFileAttached()==0){
            orderDTO.setFileAttached(orderEntity.getFileAttached());//0
        }else {
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            orderDTO.setFileAttached(orderEntity.getFileAttached());//1
            //파일 이름을 가져와야함
            //originalFileNAme, storedFileName : board_file_table(BoardFileEntity)
            //join
            //select*from board_table b, board_file_table bf where bf where b. id = bf.board_id
            //and where b.id?
            for (OrderFileEntity orderFileEntity: orderEntity.getOrderFileEntityList()) {
                originalFileNameList.add(orderFileEntity.getOriginalFileName());
                storedFileNameList.add(orderFileEntity.getStoredFileName());
            }
            orderDTO.setOriginalFilename(originalFileNameList);
            orderDTO.setStoredFileName(storedFileNameList);
        }
        return orderDTO;
    }



}
