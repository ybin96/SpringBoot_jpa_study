package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	// 이메일로 회원 정보 조회 ( select * from member_table where memberEmail=?)
	
	Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
