package com.example.demo.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.demo.vo.Member;

class MemoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	// test끝날때 reset시켜주기위해 만든 메소드(MemoryMemberRepository에 clearStore메소드 생성함)
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		// 테스트를 위해 임의의 이름을 넣는다
		Member member = new Member();
		member.setName("ybin");
		// 이름을 member에 저장한다
		repository.save(member);
		// 같은지 확인을위해 member에서 id를 가져와서 member와 비교한다
		Member result = repository.findById(member.getId()).get();
		//Member result2 = repository.findByName(member.getName()).get();
		Assertions.assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findAll() {
		Member member = new Member();
		member.setName("good");
		repository.save(member);
		
		Member member1 = new Member();
		member1.setName("bad");
		repository.save(member1);
		
		List<Member> result =  repository.findAll();
		Assertions.assertThat(result.size()).isEqualTo(2);
	}
	
}
