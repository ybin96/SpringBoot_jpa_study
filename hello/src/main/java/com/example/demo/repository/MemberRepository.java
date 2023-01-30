package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.vo.Member;

public interface MemberRepository {
	// DAO(저장소)에 회원정보를 저장한다
	Member save(Member member);
	
	// id와 name을 찾아온다
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	
	// 모든 회원 list를 가져온다
	List<Member> findAll();	
}
