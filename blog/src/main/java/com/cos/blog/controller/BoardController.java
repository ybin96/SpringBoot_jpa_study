package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	// 세션에 접근할때 @AuthenticationPrincipal로 접근한다.
	// PrincipalDetail 클래스에 해당 정보를 저장해놓았기 때문이다
	@GetMapping("/")
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("사용자 로그인 아이디 : "+principal.getUsername());
		return "index";
	}
}
