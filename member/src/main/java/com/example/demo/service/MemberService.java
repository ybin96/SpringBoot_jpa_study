package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDto;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	public void save(MemberDto memberdto) {
		// 1. dto -> entity 변환 (spring jpa를 사용하기위해서 entity를 넘겨줘야한다)
		MemberEntity memberEntity = MemberEntity.toMemberEntity(memberdto);
		
		// 2. repository의 save 메소드 호출
		memberRepository.save(memberEntity);
	}

	public MemberDto login(MemberDto memberdto) {
		// 1. 회원이 입력한 이메일로 DB에서 조회
		Optional<MemberEntity> byMemberEmail = 
				memberRepository.findByMemberEmail(memberdto.getMemberEmail());
		if(byMemberEmail.isPresent()) {
			// 조회 결과가 있다
			MemberEntity memberEntity = byMemberEmail.get();
			if(memberEntity.getMemberPwd().equals(memberdto.getMemberPwd())) {
				// 비밀번호 일치
				// entity를 dto로 변환
				MemberDto dto = MemberDto.toMemberDTO(memberEntity);
				return dto;
			} else {
				// 비밀번호 불일치
				return null;
			}
		} else {
			// 조회 결과가 없다
			return null;
		}
	}

	public List<MemberDto> findAll() {
		// repository에서 제공해주는 메소드
		List<MemberEntity> memberEntityList = memberRepository.findAll();
		// controller로 넘겨주기위한 return 값 memberDtoList를 만든다
		List<MemberDto> memberDtoList = new ArrayList<>();
		
		for (MemberEntity memberEntity : memberEntityList) {
			// Entity를 Dto객체로 바꿔서 controller로 보내준다
			memberDtoList.add(MemberDto.toMemberDTO(memberEntity));
		}
		
		return memberDtoList;
	}

	public MemberDto findById(Long id) {
		Optional<MemberEntity> findById = memberRepository.findById(id);
		if(findById.isPresent()) {
			return MemberDto.toMemberDTO(findById.get());
		} else {
			return null;
		}
	}

	public MemberDto updateForm(String myEmail) {
		 Optional<MemberEntity> findByMemberEmail = memberRepository.findByMemberEmail(myEmail);
		 if(findByMemberEmail.isPresent()) {
			return MemberDto.toMemberDTO(findByMemberEmail.get());
		 } else {
			 return null;	
		 }
	}

	public void update(MemberDto dto) {
		memberRepository.save(MemberEntity.toUpdateMemberEntity(dto));
	}

	public void deleteById(Long id) {
		memberRepository.deleteById(id);
		
	}
}
