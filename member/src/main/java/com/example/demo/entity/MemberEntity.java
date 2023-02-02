package com.example.demo.entity;
import com.example.demo.dto.MemberDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name="member_table")
public class MemberEntity {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	// sequence랑 같은 종류
	
	@Column(unique = true) // unique 제약조건 추가
	private String memberEmail;
	
	@Column
	private String memberPwd;
	
	@Column
	private String memberName;
	
	// dto를 entity로 변환
	public static MemberEntity toMemberEntity(MemberDto memberDTO) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setMemberEmail(memberDTO.getMemberEmail());
		memberEntity.setMemberPwd(memberDTO.getMemberPwd());
		memberEntity.setMemberName(memberDTO.getMemberName());
		
		return memberEntity;
	}
	
	public static MemberEntity toUpdateMemberEntity(MemberDto memberDTO) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setId(memberDTO.getId());
		memberEntity.setMemberEmail(memberDTO.getMemberEmail());
		memberEntity.setMemberPwd(memberDTO.getMemberPwd());
		memberEntity.setMemberName(memberDTO.getMemberName());
		
		return memberEntity;
	}
	
}
