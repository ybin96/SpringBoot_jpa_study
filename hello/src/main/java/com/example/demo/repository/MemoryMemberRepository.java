package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.vo.Member;

@Repository
public class MemoryMemberRepository implements MemberRepository {

	// Memory Class 이기 때문에 어딘가에 저장할 공간이 필요하다 
	private static Map<Long, Member> store = new HashMap<>();
	// sequence는 key값을 설정해준다
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		// 우선 ID를 세팅한후에 store에 key,value를 저장한다
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// optional은 id가 null이라도 반환될수 있다
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		// 루프를 돌면서 store에 저장된 name과 parameter name이 같으면 반환하고
		// null이면 null을 반환한다
		// findAny ==> 하나라도 찾는함수
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
	}

	@Override
	public List<Member> findAll() {
		// store에 values는 member(회원정보)
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
	

}
