package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.MemberDto;
import com.example.demo.entity.MemberEntity;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	// 생성자 주입
	private final MemberService memberService;
	
	// 회원가입 출력 요청
	@GetMapping("/member/save")
	public String saveForm() {
		return "save";
	}
	
	@PostMapping("/member/save")
	public String save(@ModelAttribute MemberDto memberdto) {
		System.out.println(memberdto);
		memberService.save(memberdto);
		return "login";
	}
	
	@GetMapping("/member/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/member/login")
	public String login(@ModelAttribute MemberDto memberdto, HttpSession session) {
		MemberDto loginResult =	memberService.login(memberdto);
		if(loginResult != null) {
			session.setAttribute("loginEmail", loginResult.getMemberEmail());
			return "main";
		}else {
			return "login";
		}
	}
	
}
