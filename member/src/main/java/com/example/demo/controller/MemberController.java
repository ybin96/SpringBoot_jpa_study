package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/member/")
	public String findAll(Model model) {
		List<MemberDto> memberDTOList = memberService.findAll();
		
		model.addAttribute("memberList",memberDTOList);
		return "list";
	}
	
	@GetMapping("/member/{id}")
	public String findById(@PathVariable Long id, Model model) {
		MemberDto dto = memberService.findById(id);
		model.addAttribute("member",dto);
		return "detail";
	}
	
	@GetMapping("/member/update")
	public String updateForm(HttpSession session, Model model) {
		String myEmail = (String)session.getAttribute("loginEmail");
		MemberDto dto = memberService.updateForm(myEmail);
		model.addAttribute("updateMember",dto);
		return "update";
	}
	
	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDto dto ) {
		memberService.update(dto);
		return "redirect:/member/"+dto.getId();
	}
	
	@GetMapping("/member/delete/{id}")
	public String deleteById(@PathVariable Long id) {
		memberService.deleteById(id);
		return "redirect:/member/";
	}
	
	@GetMapping("/member/logout")
	public String logtout(HttpSession httpSession) {
		httpSession.invalidate();
		return "index";
	}
}












