package com.example.demo.member.entity;

import com.example.demo.member.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//entity클래스가 일종의 테이블 케이스 역할을 함
@Entity
@Setter
@Getter
@Table(name="member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_incrememt
    private Long id;
    @Column(unique = true)//unique 제약조건 추가
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;
    @Column
    private String postCode;
    @Column// 우편 번호

    // 새롭게 추가된 코드
    private String streetAdr;		// 도로명 주소
    @Column
    // 새롭게 추가된 코드
    private String detailAdr;		// 상세 주소


    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity=new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        // member에 각 속성을 set하기 위해 memberFormDto에 추가해야할 내용
        memberEntity.setPostCode(memberDTO.getPostCode());
        memberEntity.setStreetAdr(memberDTO.getStreetAdr());
        memberEntity.setDetailAdr(memberDTO.getDetailAdr());

        return memberEntity;
    }
    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity=new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        // member에 각 속성을 set하기 위해 memberFormDto에 추가해야할 내용
        memberEntity.setPostCode(memberDTO.getPostCode());
        memberEntity.setStreetAdr(memberDTO.getStreetAdr());
        memberEntity.setDetailAdr(memberDTO.getDetailAdr());

        return memberEntity;
    }
}
