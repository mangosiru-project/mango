package com.example.demo.member.service;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.MemberEntity;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean isMemberNameTaken(String memberName) {
        return memberRepository.findByMemberName(memberName).isPresent();
    }
    public void save(MemberDTO memberDTO) {
        //1.dto->entity변환
        //2.repository의 save메서드 호출
        MemberEntity memberEntity=MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //repository의 save메서드 호출(조건. entity객체를 넘겨줘야함)
    }

    public MemberDTO login(MemberDTO memberDTO) throws Exception {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<MemberEntity> byMemberName = memberRepository.findByMemberName(memberDTO.getMemberName());
        if(byMemberName.isPresent()){
            //조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            MemberEntity memberEntity = byMemberName.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                //비밀번호 일치
                //entity -> dto변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }
            else{
                throw new Exception("잘못된 비밀번호입니다.");
            }

        }
        else{
            //조회 결과가 없다(해당 이메일을 가진 회원이 없다
            throw new Exception("존재하지 않는 아이디입니다.");
        }
    }
    public MemberDTO updateForm(String myName){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberName(myName);
        if(optionalMemberEntity.isPresent()){
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());

        }
        else{
            return null;
        }
    }
    public void update(MemberDTO memberDTO){
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }
}
