package com.example.demo.member.dto;

import com.example.demo.member.entity.ShopEntity;
import com.example.demo.member.entity.ShopFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor//기본생성자
@AllArgsConstructor//모든 필드를 매개변수로 하는 생성자
public class ShopDTO {
    private Long id;
    private String storename;

    //주소
    private String postCode;

    private String streetAdr;

    private String detailAdr;


    private String storeintro;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String shopPass;
    private String memberName;  // 작성자 ID 필드 추가
    private List<MultipartFile> shopFile;//가게저장.html-> conotroller 파일 담는 용도
    private List<String> originalFilename;//원본 파일 이름
    private List<String> storedFileName;//서버 저장용 파일 이름
    private int fileAttached;//파일 첨부여부(첨부1,미첨부 0)
    public static ShopDTO toShopDTO(ShopEntity shopEntity){
        ShopDTO shopDTO =new ShopDTO();
        shopDTO.setId(shopEntity.getId());
        shopDTO.setStorename(shopEntity.getStorename());
        shopDTO.setStoreintro(shopEntity.getStoreintro());
        shopDTO.setStorename(shopEntity.getStorename());
        shopDTO.setPostCode(shopEntity.getPostCode());
        shopDTO.setStreetAdr(shopEntity.getStreetAdr());
        shopDTO.setDetailAdr(shopEntity.getDetailAdr());
        shopDTO.setCreatedTime(shopEntity.getCreatedTime());
        shopDTO.setUpdatedTime(shopEntity.getUpdatedTime());
        shopDTO.setShopPass(shopEntity.getShopPass());
        shopDTO.setMemberName(shopEntity.getMemberName());
        if(shopEntity.getFileAttached()==0){
            shopDTO.setFileAttached(shopEntity.getFileAttached());//0
        }else {
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            shopDTO.setFileAttached(shopEntity.getFileAttached());//1
            //파일 이름을 가져와야함
            //originalFileNAme, storedFileName : board_file_table(BoardFileEntity)
            //join
            //select*from board_table b, board_file_table bf where bf where b. id = bf.board_id
            //and where b.id?
            for (ShopFileEntity shopFileEntity: shopEntity.getShopFileEntityList()) {
                originalFileNameList.add(shopFileEntity.getOriginalFileName());
                storedFileNameList.add(shopFileEntity.getStoredFileName());
                 }
            shopDTO.setOriginalFilename(originalFileNameList);
            shopDTO.setStoredFileName(storedFileNameList);
        }
        return shopDTO;
    }




}
