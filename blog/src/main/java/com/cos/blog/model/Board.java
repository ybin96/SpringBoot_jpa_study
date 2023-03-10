package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 사용 (html태그가 섞여서 디자인이 된다.
	
	
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER) // 연관관계 설정 (자동 fk설정)	 Many = Board, User = One --> 하나의 유저는 많은 게시글을 작성할수 있다.
	@JoinColumn(name="userId") // 필드 ID = userId
	private User user; // DB는 오브젝트를 저장할수 없지만, 자바는 오브젝트를 저장할수 있다
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) 
	// mappedBy 연관관계의 주인x (FX x, DB에 칼럼이 만들어지지 않는다)
	private List<Reply> reply;
	
	@CreationTimestamp // 자동으로 현재시간이 저장된다
	private Timestamp createDate;
}
