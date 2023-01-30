package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.vo.Member;

class MemberServiceTest {
	
	MemberService memberService;
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("test1");
		
		// when
		Long saveId = memberService.join(member);

		// then
		Member findMember = memberService.findone(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName()); 
	}
	
	@Test
	void 중복검사() {
		// given
		Member member1 = new Member();
		member1.setName("test1");
		
		Member member2 = new Member();
		member2.setName("test1");
		
		// when
		memberService.join(member1); 
		assertThrows(IllegalStateException.class,() -> memberService.join(member2));		
		
		// then
	}

	@Test
	void testFindMembers() {
		
	}

	@Test
	void testFindone() {
		
	}

}
