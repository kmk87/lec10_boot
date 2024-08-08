package com.gn.spring.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gn.spring.board.domain.Board;
import com.gn.spring.board.domain.BoardDto;
import com.gn.spring.board.repository.BoardRepository;

@Service
public class BoardService {
	
	// 의존성 주입
	private final BoardRepository boardRepository;
	
	// 생성자 주입
	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public List<BoardDto> selectBoardList(){
		
		List<Board> boardList = boardRepository.findAll();
		
		List<BoardDto> boardDtoList = new ArrayList<BoardDto>();
		// Board(Entity)를 BoardDto로 바꿔주기
		for(Board board : boardList) {
			BoardDto boardDto = new BoardDto().toDto(board);
			boardDtoList.add(boardDto);
		}
		
		return boardDtoList;
		
		
		
		
	}
}
