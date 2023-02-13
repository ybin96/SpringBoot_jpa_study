package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;


@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Transactional
	public void 글쓰기(Board board,User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패:아이디를 찾을수 없습니다.");
				});
		
	}

	@Transactional
	public void 글삭제하기(int id, PrincipalDetail detail) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("해당 글이 존재하지 않습니다");
				});
		
		if(board.getUser().getId() != detail.getUser().getId()) {
			throw new IllegalArgumentException("해당 글을 삭제할 권한이 없습니다");
		}
		
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Board requestboard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException();
				}); // 영속화
		
		board.setTitle(requestboard.getTitle());
		board.setContent(requestboard.getContent());
		// 해당 함수 종료시 트랙잭션이 종료된다. 이때 더티체킹 - 자동 업데이트
		
	}

	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply requestReply) {
		// 현재 reply에는 content밖에 없다.
		requestReply.setUser(user);
		
		Board board = boardRepository.findById(boardId).orElseThrow(()->{
			return new IllegalArgumentException("댓글쓰기 실패:게시글 id를 찾을 수 없다.");
		});
		requestReply.setBoard(board);
		
		replyRepository.save(requestReply);
			
	}

	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
		
	}
	
}
