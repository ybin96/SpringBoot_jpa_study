package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;

import jakarta.transaction.Transactional;

@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
		
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// 회원가입 
	public long join(Member member) {
		
		validateMember(member);	// 회원 중복확인 검증(method)
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateMember(Member member) {
		// 저장하기 전 같은 이름 중복 X
		// Optional로 감싸고 있기때문에 ifPresent같은 메소드를 사용할수 있다.
		// Optional 안에 Member객체가 있다고 생각
		Optional<Member> result = memberRepository.findByName(member.getName());
		result.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	
	// 전체 회원 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findone(Long memberId){
		return memberRepository.findById(memberId);
	}
}
