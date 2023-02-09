package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌때 username,pwd 2개를 가로채는데
	// pwd는 알아서해줘서 username만 DB에 있는지 확인하면 된다
	// 로그인이 진행될때 username을 찾아주고 없으면 예외로 넘어간다
	// 그작업을 이 메소드에서 진행한다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다:"+username);
				});
		
		return new PrincipalDetail(principal);	// 시큐리티의 세션에 유저 정보가 저장이 된다.
	}

}
