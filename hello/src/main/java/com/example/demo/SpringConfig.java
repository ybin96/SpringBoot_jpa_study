package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.JdbcMemberRepository;
import com.example.demo.repository.JdbcTemplateMemberRepository;
import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;

import jakarta.persistence.EntityManager;

@Configuration
public class SpringConfig {
	
	private final MemberRepository memberRepository;
	
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}

//	@Bean
//	public MemberRepository memberRepository() {
//		// return new MemoryMemberRepository();
//		// return new JdbcMemberRepository(dataSource);
//		// return new JdbcTemplateMemberRepository(dataSource);
//		// return new JpaMemberRepository(em);
//	}
}