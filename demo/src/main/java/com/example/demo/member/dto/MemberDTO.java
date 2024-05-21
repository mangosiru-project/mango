package com.example.demo.member.dto;

import com.example.demo.member.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    //필드 선언, 멤버와 스트링 이름이 같으면 스프링이 알아서 전달해줌
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    //주소
    private String postCode;

    private String streetAdr;

    private String detailAdr;


    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setPostCode(memberEntity.getPostCode());
        memberDTO.setStreetAdr(memberEntity.getStreetAdr());
        memberDTO.setDetailAdr(memberEntity.getDetailAdr());
        return memberDTO;
    }
}
