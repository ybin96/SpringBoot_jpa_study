package com.example.demo.dto;

import com.example.demo.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {

	private Long id;
	private String memberEmail;
	private String memberPwd;
	private String memberName;
	
	public static MemberDto toMemberDTO(MemberEntity memberEntity) {
		MemberDto memberDto = new MemberDto();
		memberDto.setId(memberEntity.getId());
		memberDto.setMemberEmail(memberEntity.getMemberEmail());
		memberDto.setMemberName(memberEntity.getMemberName());
		memberDto.setMemberPwd(memberEntity.getMemberPwd());
		return memberDto;
	}
	
}
